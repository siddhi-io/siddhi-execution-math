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

package io.siddhi.extension.execution.math;

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
import io.siddhi.annotation.ParameterOverload;
import io.siddhi.annotation.ReturnAttribute;
import io.siddhi.annotation.util.DataType;
import io.siddhi.core.config.SiddhiQueryContext;
import io.siddhi.core.exception.OperationNotSupportedException;
import io.siddhi.core.executor.ConstantExpressionExecutor;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.query.processor.ProcessingMode;
import io.siddhi.core.query.selector.attribute.aggregator.AttributeAggregatorExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.extension.execution.math.util.PercentileAttributeState;
import io.siddhi.query.api.definition.Attribute;

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
        description = "This functions returns the pth percentile value of a given argument.",
        parameters = {
                @Parameter(
                        name = "arg",
                        description = "The value of the parameter whose percentile should be found.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true),
                @Parameter(
                        name = "p",
                        description = "Estimate of the percentile to be found (pth percentile) " +
                                "where p is any number greater than 0 or lesser than or equal to 100.",
                        type = {DataType.DOUBLE},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"arg", "p"})
        },
        returnAttributes = @ReturnAttribute(
                description = "Estimate of the 'p'th percentile value of the 'arg' values.",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (sensorId int, temperature double); \n" +
                        "from InValueStream \n" +
                        "select math:percentile(temperature, 97.0) as percentile \n" +
                        "insert into OutMediationStream;",
                description = "This function returns the percentile value based on the argument given." +
                        " For example, math:percentile(temperature, 97.0) returns the 97th percentile " +
                        "value of all the temperature events."
        )
)
public class PercentileFunctionExtension extends AttributeAggregatorExecutor<PercentileAttributeState> {

    private static final String VALUES_LIST = "VALUES_LIST";
    private PercentileAttributeState percentileAttributeState;
    private double percentileValue;
    private List<Double> valuesList;

    @Override
    protected StateFactory<PercentileAttributeState> init(ExpressionExecutor[] expressionExecutors,
                                                          ProcessingMode processingMode, boolean b,
                                ConfigReader configReader, SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new OperationNotSupportedException("Percentile function has to have exactly 2 parameter, currently "
                                                             + attributeExpressionExecutors.length +
                                                             " parameters provided.");
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
        valuesList = new ArrayList<>();
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();

        // This approach is used to avoid per event type check as it has a negative performance impact.
        return () -> {
            switch (attributeType) {
                case FLOAT:
                    return new FloatPercentileAttributeState();
                case INT:
                    return new IntPercentileAttributeState();
                case LONG:
                    return new LongPercentileAttributeState();
                case DOUBLE:
                    return new DoublePercentileAttributeState();
                default:
                    throw new OperationNotSupportedException("Percentile not supported for " + attributeType);
            }
        };
    }

    @Override
    public Object processAdd(Object data, PercentileAttributeState state) {
        // will not occur
        return new IllegalStateException("Percentile need multiple input, but found " + data);
    }

    @Override public Object processAdd(Object[] data, PercentileAttributeState state) {
        if (data == null) {
            return state.currentValue();
        }
        return state.processAdd(data[0]);

    }

    @Override public Object processRemove(Object data, PercentileAttributeState state) {
        // will not occur
        return new IllegalStateException("Percentile need multiple input, but found " + data);
    }

    @Override public Object processRemove(Object[] data, PercentileAttributeState state) {
        if (data == null) {
            return state.currentValue();
        }
        return state.processRemove(data[0]);
    }

    @Override public Object reset(PercentileAttributeState state) {
        return state.reset();
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
        arrayList.remove(removeIndex);
    }

    @Override public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

    private class DoublePercentileAttributeState extends PercentileAttributeState {

        @Override public Object processAdd(Object data) {
            double value = (Double) data;
            sortedArrayListAdd(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object processRemove(Object obj) {
            double value = (Double) obj;
            sortedArrayListRemove(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object reset() {
            valuesList.clear();
            return 0.0;
        }

        @Override public Object currentValue() {
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public boolean canDestroy() {
            return valuesList.isEmpty();
        }

        @Override public Map<String, Object> snapshot() {
            return Collections.singletonMap(VALUES_LIST, valuesList);
        }

        @Override public void restore(Map<String, Object> map) {
            valuesList = (List<Double>) map.get(VALUES_LIST);
        }
    }

    private class FloatPercentileAttributeState extends PercentileAttributeState {

        @Override public Object processAdd(Object data) {
            float value = (Float) data;
            sortedArrayListAdd(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object processRemove(Object obj) {
            double value = (Float) obj;
            sortedArrayListRemove(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object reset() {
            valuesList.clear();
            return 0.0;
        }

        @Override public Object currentValue() {
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public boolean canDestroy() {
            return valuesList.isEmpty();
        }

        @Override public Map<String, Object> snapshot() {
            return Collections.singletonMap(VALUES_LIST, valuesList);
        }

        @Override public void restore(Map<String, Object> map) {
            valuesList = (List<Double>) map.get(VALUES_LIST);
        }
    }

    private class IntPercentileAttributeState extends PercentileAttributeState {

        @Override public Object processAdd(Object data) {
            float value = (Integer) data;
            sortedArrayListAdd(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object processRemove(Object obj) {
            double value = (Integer) obj;
            sortedArrayListRemove(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object reset() {
            valuesList.clear();
            return 0.0;
        }

        @Override public Object currentValue() {
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public boolean canDestroy() {
            return valuesList.isEmpty();
        }

        @Override public Map<String, Object> snapshot() {
            return Collections.singletonMap(VALUES_LIST, valuesList);
        }

        @Override public void restore(Map<String, Object> map) {
            valuesList = (List<Double>) map.get(VALUES_LIST);
        }
    }

    private class LongPercentileAttributeState extends PercentileAttributeState {

        @Override public Object processAdd(Object data) {
            float value = (Long) data;
            sortedArrayListAdd(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object processRemove(Object obj) {
            double value = (Long) obj;
            sortedArrayListRemove(valuesList, value);
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public Object reset() {
            valuesList.clear();
            return 0.0;
        }

        @Override public Object currentValue() {
            return getPercentileValue(valuesList, percentileValue);
        }

        @Override public boolean canDestroy() {
            return valuesList.isEmpty();
        }

        @Override public Map<String, Object> snapshot() {
            return Collections.singletonMap(VALUES_LIST, valuesList);
        }

        @Override public void restore(Map<String, Object> map) {
            valuesList = (List<Double>) map.get(VALUES_LIST);
        }
    }

}
