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
 * round(a);
 * Returns the closest value to the argument, with ties rounding up. For example, the round value of 3.35 is
 * 3.
 * Accept Type(s):DOUBLE/FLOAT/
 * Return Type(s): LONG/INT
 */
@Extension(
        name = "round",
        namespace = "math",
        description = "This function returns the value of the input argument rounded off to the closest integer/long" +
                " value.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value that should be rounded off to the closest integer/long value.",
                        type = {DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"p1"})
        },
        returnAttributes = @ReturnAttribute(
                description = "The closest integer/long value to the input parameter.",
                type = {DataType.INT, DataType.LONG}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue double); \n" +
                        "from InValueStream \n" +
                        "select math:round(inValue) as roundValue \n" +
                        "insert into OutMediationStream;",
                description = "The function rounds off 'inValue1' to the closest int/long value and directs" +
                        " the output to the output stream, " +
                        "'OutMediationStream'. For example, round(3252.353) returns 3252.")
)
public class RoundFunctionExtension extends FunctionExecutor {

    private Attribute.Type returnType;

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:round() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE) || (attributeType == Attribute.Type.FLOAT))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:round() " +
                    "function, required " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }

        if (attributeType == Attribute.Type.FLOAT) {
            returnType = Attribute.Type.INT;
        } else {
            returnType = Attribute.Type.LONG;
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
            //type-conversion
            if (data instanceof Float) {
                float inputValue = (Float) data;
                return Math.round(inputValue);
            } else if (data instanceof Double) {
                double inputValue = (Double) data;
                return Math.round(inputValue);
            }
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

}
