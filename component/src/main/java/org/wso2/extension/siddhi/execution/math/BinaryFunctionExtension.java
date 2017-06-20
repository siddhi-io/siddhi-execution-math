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
 * bin(a)
 * This class wraps the java.lang.Integer.toBinaryString and java.lang.Long.toBinaryString methods,
 * which return a string representation of the integer/long argument as an unsigned integer in base 2.
 * Accept Type(s):INT, LONG
 * Return Type(s): STRING
 */
@Extension(
        name = "bin",
        namespace = "math",
        description = "Returns a string representation of the integer/long p1 argument as an unsigned integer in " +
                "base 2. This function wraps the java.lang.Integer.toBinaryString and " +
                "java.lang.Long.toBinaryString methods.",
        parameters = {@Parameter(name = "p1", description = "TBD", type = {DataType.INT, DataType.LONG})},
        returnAttributes = @ReturnAttribute(description = "TBD", type = {DataType.STRING}),
        examples = @Example(description = "bin(9) returns \"1001\".", syntax = "TBD")
)
public class BinaryFunctionExtension extends FunctionExecutor {

    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:bin() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (attributeType != Attribute.Type.INT && attributeType != Attribute.Type.LONG) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:bin() " +
                    "function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    ", but found " + attributeType.toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    protected Object execute(Object data) {
        if (data != null) {
            if (data instanceof Integer) {
                return Integer.toBinaryString((Integer) data);
            } else {
                return Long.toBinaryString((Long) data);
            }
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:bin() function cannot be null");
        }
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
        return Attribute.Type.STRING;
    }


    @Override
    public Map<String, Object> currentState() {
        return null;
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}
