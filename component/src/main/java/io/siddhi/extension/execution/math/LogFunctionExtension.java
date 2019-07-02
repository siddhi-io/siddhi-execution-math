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
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import static io.siddhi.extension.execution.math.util.MathUtil.convertToDouble;

/**
 * log(number,base);
 * Returns the logarithm (base='base') of the given 'number'.
 * number - Accept Type(s):DOUBLE/INT/FLOAT/LONG
 * base - Accept Type(s):DOUBLE/INT/FLOAT/LONG
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "log",
        namespace = "math",
        description = "This function returns the logarithm of the received `number` as per the given `base`.",
        parameters = {
                @Parameter(
                        name = "number",
                        description = "The value of the parameter whose base should be changed.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true),
                @Parameter(
                        name = "base",
                        description = "The base value of the ouput.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"number", "base"})
        },
        returnAttributes = @ReturnAttribute(
                description = "The logarithm value of the 'number' parameter to the base, 'base' parameter.",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (number double, base double); \n" +
                        "from InValueStream \n" +
                        "select math:log(number, base) as logValue \n" +
                        "insert into OutMediationStream;",
                description = "If the number and the base to which it has to be converted into is given in the " +
                        "input stream, the function calculates the number to the base specified and directs the " +
                        "result to the output stream, OutMediationStream. " +
                        "For example, log(34, 2f) returns 5.08746284125034.")
)
public class LogFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:log() function, " +
                    "required 2, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "math:log() function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
        attributeType = attributeExpressionExecutors[1].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "math:log() function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        if (data[0] != null && data[1] != null) {
            double number = convertToDouble(data[0]);
            double base = convertToDouble(data[1]);
            if (base == 1) {
                throw new SiddhiAppRuntimeException("The base argument supplied to the math:log() function "
                        + "is equal to zero. Since the logarithms to the base 1 is undefined, "
                        + "the result of math:log(" + number + "," + base + ") is undefined");
            }
            return Math.log(number) / Math.log(base);
        }
        return null;
    }

    @Override
    protected Object execute(Object data, State state) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

}
