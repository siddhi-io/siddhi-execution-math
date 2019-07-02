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
 * bin(a)
 * This class wraps the java.lang.Integer.toBinaryString and java.lang.Long.toBinaryString methods,
 * which return a string representation of the integer/long argument as an unsigned integer in base 2.
 * Accept Type(s):INT, LONG
 * Return Type(s): STRING
 */
@Extension(
        name = "bin",
        namespace = "math",
        description = "This function returns a string representation of the p1 argument, that is of" +
                " either 'integer' or 'long' data type, as an unsigned integer in base 2. It wraps the" +
                " `java.lang.Integer.toBinaryString` and java.lang.Long.toBinaryString` methods.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value in either 'integer' or 'long', that should be converted into " +
                                "an unsigned integer of base 2.",
                        type = {DataType.INT, DataType.LONG},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"p1"}),
        },
        returnAttributes = @ReturnAttribute(
                description = "A string representation of the p1 parameter as an unsigned integer in " +
                        "base 2.",
                type = {DataType.STRING}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue long); \n" +
                        "from InValueStream \n" +
                        "select math:bin(inValue) as binValue \n" +
                        "insert into OutMediationStream;",
                description = "If the 'inValue' in the input stream is given, the function " +
                        "converts it into an unsigned integer in base 2 and directs the output to the " +
                        "output stream, OutMediationStream. For example, bin(9) returns '1001'.")
)
public class BinaryFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
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
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    protected Object execute(Object data, State state) {
        if (data != null) {
            if (data instanceof Integer) {
                return Integer.toBinaryString((Integer) data);
            } else {
                return Long.toBinaryString((Long) data);
            }
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.STRING;
    }

}
