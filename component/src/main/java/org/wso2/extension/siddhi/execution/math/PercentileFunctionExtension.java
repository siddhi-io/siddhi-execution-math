/*
 * Copyright (c)  2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.extension.siddhi.execution.math;

import org.wso2.extension.siddhi.execution.math.util.ValueParser;
import org.wso2.siddhi.annotation.Example;
import org.wso2.siddhi.annotation.Extension;
import org.wso2.siddhi.annotation.Parameter;
import org.wso2.siddhi.annotation.ReturnAttribute;
import org.wso2.siddhi.annotation.util.DataType;
import org.wso2.siddhi.core.config.SiddhiAppContext;
import org.wso2.siddhi.core.exception.OperationNotSupportedException;
import org.wso2.siddhi.core.executor.ConstantExpressionExecutor;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.query.selector.attribute.aggregator.AttributeAggregator;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * AttributeAggregator which implements the following function.
 * <code>percentile(value, p)</code>
 * Returns an estimate for the pth percentile of the stored values.
 * Accept Type(s): value: FLOAT,INT,LONG,DOUBLE / p: DOUBLE
 * Return Type: DOUBLE
 */
@Extension(
        name = "percentile",
        namespace = "math",
        description = "Returns the pth percentile value of the arg values.",
        parameters = {
                @Parameter(
                        name = "arg",
                        description = "The values of which the percentile should be found",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE}),
                @Parameter(
                        name = "p",
                        description = "Estimate of which percentile to be found (pth percentile) " +
                                "where p is any number greater than 0 or less than or equal to 100",
                        type = {DataType.DOUBLE})
        },
        returnAttributes = @ReturnAttribute(
                description = "Estimate of the 'p'th percentile value of the 'arg' values",
                type = {DataType.DOUBLE}),
        examples = @Example(
                description = "math:percentile(temperature, 97.0)",
                syntax = "define stream InValueStream (sensorId int, temperature double); \n" +
                        "from InValueStream \n" +
                        "select math:percentile(temperature, 97.0) as percentile \n" +
                        "insert into OutMediationStream;")
)
public class PercentileFunctionExtension extends AttributeAggregator {

    private static final String VALUES_LIST = "VALUES_LIST";
    private ValueParser valueParser;
    private double percentileValue;
    private List<Double> valuesList;

    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {

        if (attributeExpressionExecutors.length != 2) {
            throw new OperationNotSupportedException("Percentile function has to have exactly 2 parameter, currently "
                    + attributeExpressionExecutors.length + " parameters provided.");
        }

        if (!(attributeExpressionExecutors[1] instanceof ConstantExpressionExecutor)) {
            throw new OperationNotSupportedException("Percentile value has to be a constant.");
        }

        Object percentileValueObject = attributeExpressionExecutors[1].execute(null);
        if (percentileValueObject instanceof Double) {
            percentileValue = ((Double) percentileValueObject);
        } else {
            throw new OperationNotSupportedException("Percentile value should be of type double. But found "
                    + attributeExpressionExecutors[1].getReturnType());
        }

        if (percentileValue <= 0 || percentileValue > 100) {
            throw new OperationNotSupportedException(
                    "Percentile value should be in 0 < p <= 100 range. But found " + percentileValue);
        }

        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();

        // This approach is used to avoid per event type check as it has a negative performance impact.
        switch (attributeType) {
            case FLOAT:
                valueParser = new FloatValueParser();
                break;
            case INT:
                valueParser = new IntValueParser();
                break;
            case LONG:
                valueParser = new LongValueParser();
                break;
            case DOUBLE:
                valueParser = new DoubleValueParser();
                break;
            default:
                throw new OperationNotSupportedException("Percentile not supported for " + attributeType);
        }

        valuesList = new ArrayList<Double>();
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

    @Override
    public Object processAdd(Object data) {
        // will not occur
        return new IllegalStateException("Percentile cannot process a single argument, but found " + data.toString());
    }

    @Override
    public Object processAdd(Object[] data) {
        double value = valueParser.parseValue(data[0]);
        sortedArrayListAdd(valuesList, value);
        return getPercentileValue(valuesList, percentileValue);
    }

    @Override
    public Object processRemove(Object data) {
        // will not occur
        return new IllegalStateException("Percentile cannot process a single argument, but found " + data.toString());

    }

    @Override
    public Object processRemove(Object[] data) {
        double value = valueParser.parseValue(data[0]);
        sortedArrayListRemove(valuesList, value);
        return getPercentileValue(valuesList, percentileValue);
    }

    @Override
    public boolean canDestroy() {
        return valuesList.size() == 0;
    }

    @Override
    public Object reset() {
        valuesList.clear();
        return 0.0;
    }

    @Override
    public Map<String, Object> currentState() {
        return Collections.singletonMap(VALUES_LIST, valuesList);
    }

    @Override
    public void restoreState(Map<String, Object> map) {
        valuesList = (List<Double>) map.get(VALUES_LIST);
    }

    /**
     * Percentile calculation method.
     * <p>
     * To calculate the pth percentile (where p is any number greater than 0 or less than or equal to 100), do the
     * following steps:
     * 1. Order all the values in the data set from smallest to largest.
     * 2. Multiply p percent by the total number of values, n. This number is called the index.
     * 3. If the index obtained in Step 2 is not a whole number, round it up to the nearest whole number and go to Step
     * 4a. If the index obtained in Step 2 is a whole number, go to Step 4b.
     * 4a. Count the values in your data set from left to right (from the smallest to the largest value) until you reach
     * the number indicated by Step 3. The corresponding value in your data set is the pth percentile.
     * 4b. Count the values in your data set from left to right until you reach the number indicated by Step 2.
     * The pth percentile is the average of that corresponding value in your data set and the value that directly
     * follows it.
     *
     * @param valuesList values list
     * @param percentile percentile (p)
     * @return pth percentile value
     */
    private double getPercentileValue(List<Double> valuesList, double percentile) {
        if (valuesList.isEmpty()) {
                return 0.0;
        }
        double percentileIndexTemp;
        int percentileIndex;

        // calculating percentile index
        percentileIndexTemp = percentile * valuesList.size() / 100;

        if (percentileIndexTemp % 1 == 0) {
            percentileIndex = (int) percentileIndexTemp;
            if (percentileIndex == valuesList.size()) {
                return valuesList.get(percentileIndex - 1);
            } else {
                return (valuesList.get(percentileIndex - 1) + valuesList.get(percentileIndex)) / 2;
            }
        } else {
            percentileIndex = (int) Math.round(percentileIndexTemp);
            if (percentileIndex == 0) {
                return valuesList.get(percentileIndex);
            } else {
                return valuesList.get(percentileIndex - 1);
            }
        }
    }

    /**
     * Adding values to the sorted ArrayList.
     *
     * @param arrayList sorted ArrayList
     * @param value     new value
     */
    private void sortedArrayListAdd(List<Double> arrayList, double value) {

        int insertIndex = Collections.binarySearch(arrayList, value);
        if (insertIndex < 0) {
            arrayList.add(-insertIndex - 1, value);
        } else {
            arrayList.add(insertIndex + 1, value);
        }
    }

    /**
     * Removing values from the sorted ArrayList.
     *
     * @param arrayList Sorted ArrayList
     * @param value     expired value
     */
    private void sortedArrayListRemove(List<Double> arrayList, double value) {
            
        int removeIndex = Collections.binarySearch(arrayList, value);
        if (removeIndex >= 0) {
            arrayList.remove(removeIndex);
        }
    }

    private class DoubleValueParser implements ValueParser {

        @Override
        public double parseValue(Object valueObject) {
            return (Double) valueObject;
        }
    }

    private class FloatValueParser implements ValueParser {

        @Override
        public double parseValue(Object valueObject) {
            return (Float) valueObject;
        }
    }

    private class IntValueParser implements ValueParser {

        @Override
        public double parseValue(Object valueObject) {
            return (Integer) valueObject;
        }
    }

    private class LongValueParser implements ValueParser {

        @Override
        public double parseValue(Object valueObject) {
            return (Long) valueObject;
        }
    }

}
