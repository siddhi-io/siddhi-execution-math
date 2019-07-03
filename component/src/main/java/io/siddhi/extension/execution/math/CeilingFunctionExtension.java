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
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

/**
 * ceil(a)
 * Wraps java.lang.Math.ceil()
 * Returns the smallest (closest to negative infinity) double value that is greater
 * than or equal to the argument and is equal to a mathematical integer.
 * Accept Type(s): FLOAT/DOUBLE
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "ceil",
        namespace = "math",
        description = "This function returns the smallest double value, i.e., the closest to the negative infinity, " +
                "that is greater than or equal to the `p1` argument, and is equal to a mathematical integer. " +
                "It wraps the `java.lang.Math.ceil()` method.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value of the parameter whose ceiling value is found.",
                        type = {DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"p1"})
        },
        returnAttributes = @ReturnAttribute(
                description = "The smallest double value, which is closest to the negative infinity that is greater " +
                        "than or equal to the p1 argument, and is equal to a mathematical integer.",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue double); \n" +
                        "from InValueStream \n" +
                        "select math:ceil(inValue) as ceilingValue \n" +
                        "insert into OutMediationStream;",
                description = "This function calculates the ceiling value of the given 'inValue' and " +
                        "directs the result to 'OutMediationStream' output stream. For example, " +
                        "ceil(423.187d) returns 424.0.")
)
public class CeilingFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:ceil() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (attributeType != Attribute.Type.DOUBLE && attributeType != Attribute.Type.FLOAT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:ceil() " +
                    "function, required " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        return null;    // This method won't get called. Hence, unimplemented.
    }


    @Override
    protected Object execute(Object data, State state) {
        if (data != null) {
            if (data instanceof Float) {
                return Math.ceil((Float) data);
            } else {
                return Math.ceil((Double) data);
            }
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }
}
