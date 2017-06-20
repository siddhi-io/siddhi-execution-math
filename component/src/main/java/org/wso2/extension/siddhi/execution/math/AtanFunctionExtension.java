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

import org.wso2.siddhi.annotation.Example;
import org.wso2.siddhi.annotation.Extension;
import org.wso2.siddhi.annotation.Parameter;
import org.wso2.siddhi.annotation.ReturnAttribute;
import org.wso2.siddhi.annotation.util.DataType;
import org.wso2.siddhi.core.config.SiddhiAppContext;
import org.wso2.siddhi.core.exception.SiddhiAppRuntimeException;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Map;

/**
 * atan(a); or atan(a,b);
 * Returns the arc-tangent(inverse tangent). The return value is in radian scale.
 * Accept Type(s) :DOUBLE/INT/FLOAT/LONG
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "atan",
        namespace = "math",
        description = "1. math:atan(p1) Returns the arc-tangent (inverse tangent) of p1. " +
                "The return value is in radian scale. This function wraps the java.lang.Math.atan() function." +
                "\n" +
                "2. Returns the arc-tangent (inverse tangent) of  p1 and p2 coordinates. The return value is in " +
                "radian scale. This function wraps the java.lang.Math.atan2() function.",
        parameters = {
                @Parameter(name = "p1", description = "TBD", type = {DataType.INT, DataType.LONG, DataType.FLOAT,
                        DataType.DOUBLE}),
                @Parameter(name = "p1", description = "TBD", optional = true, defaultValue = "0D",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT,
                                DataType.DOUBLE})},
        returnAttributes = @ReturnAttribute(description = "TBD", type = {DataType.DOUBLE}),
        examples = @Example(description = "atan(12d, 5d) returns 1.1760052070951352.", syntax = "TBD")
)
public class AtanFunctionExtension extends FunctionExecutor {

    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length < 1 || attributeExpressionExecutors.length > 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:atan() function, " +
                    "required 1 or 2, but found " + attributeExpressionExecutors.length);
        }
        int attributeIndex = 0;
        for (ExpressionExecutor expressionExecutor : attributeExpressionExecutors) {
            Attribute.Type attributeType = expressionExecutor.getReturnType();
            if (!((attributeType == Attribute.Type.DOUBLE)
                    || (attributeType == Attribute.Type.INT)
                    || (attributeType == Attribute.Type.FLOAT)
                    || (attributeType == Attribute.Type.LONG))) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the argument at index " +
                        attributeIndex + " of math:atan() function," +
                        "required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                        " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                        ", but found " + attributeType.toString());
            }
            attributeIndex++;
        }
    }

    @Override
    protected Object execute(Object[] data) {
        double inputVal1 = 0d;
        double inputVal2 = 0d;

        if (data[0] != null) {
            //type-conversion
            if (data[0] instanceof Integer) {
                int inputInt = (Integer) data[0];
                inputVal1 = (double) inputInt;
            } else if (data[0] instanceof Long) {
                long inputLong = (Long) data[0];
                inputVal1 = (double) inputLong;
            } else if (data[0] instanceof Float) {
                float inputLong = (Float) data[0];
                inputVal1 = (double) inputLong;
            } else if (data[0] instanceof Double) {
                inputVal1 = (Double) data[0];
            }
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:atan() function cannot be null");
        }

        if (data[1] != null) {
            //type-conversion
            if (data[1] instanceof Integer) {
                int inputInt = (Integer) data[1];
                inputVal2 = (double) inputInt;
            } else if (data[1] instanceof Long) {
                long inputLong = (Long) data[1];
                inputVal2 = (double) inputLong;
            } else if (data[1] instanceof Float) {
                float inputLong = (Float) data[1];
                inputVal2 = (double) inputLong;
            } else if (data[1] instanceof Double) {
                inputVal2 = (Double) data[1];
            }
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:atan() function cannot be null");
        }
        return Math.atan2(inputVal1, inputVal2);
    }

    @Override
    protected Object execute(Object data) {
        if (data != null) {
            //type-conversion
            if (data instanceof Integer) {
                int inputInt = (Integer) data;
                return Math.atan((double) inputInt);
            } else if (data instanceof Long) {
                long inputLong = (Long) data;
                return Math.atan((double) inputLong);
            } else if (data instanceof Float) {
                float inputFloat = (Float) data;
                return Math.atan((double) inputFloat);
            } else if (data instanceof Double) {
                return Math.atan((Double) data);
            }
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:atan() function cannot be null");
        }
        return null;
    }

    @Override
    public void start() {
        //Nothing to start.
    }

    @Override
    public void stop() {
        //Nothing to stop.
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}
