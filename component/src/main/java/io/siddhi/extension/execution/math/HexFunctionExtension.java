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
 * hex(a)
 * Converts 'a' to hex
 * Accept Type(s):INT,LONG,FLOAT,DOUBLE
 * Return Type(s): STRING
 */
@Extension(
        name = "hex",
        namespace = "math",
        description = "This function wraps the `java.lang.Double.toHexString() function. It returns a hexadecimal " +
                "string representation of the input, `p1`.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value of the parameter whose hexadecimal value should be found.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"p1"})
        },
        returnAttributes = @ReturnAttribute(
                description = "The hexadecimal conversion of the input parameter given.",
                type = {DataType.STRING}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue int); \n" +
                        "from InValueStream \n" +
                        "select math:hex(inValue) as hexString \n" +
                        "insert into OutMediationStream;",
                description = "If the 'inValue' in the input stream is provided, the function converts this " +
                        "into its corresponding hexadecimal format and directs the output to the output stream, " +
                        "OutMediationStream. For example, hex(200) returns \"c8\".")
)
public class HexFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:hex() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:hex() " +
                    "function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
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
                return Integer.toHexString((Integer) data);
            } else if (data instanceof Long) {
                return Long.toHexString((Long) data);
            } else if (data instanceof Float) {
                return Float.toHexString((Float) data);
            } else if (data instanceof Double) {
                return Double.toHexString((Double) data);
            }
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.STRING;
    }

}
