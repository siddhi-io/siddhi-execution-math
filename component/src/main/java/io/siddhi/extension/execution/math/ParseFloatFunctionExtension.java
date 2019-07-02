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
 * parseFloat(string);
 * Returns the 'string' as a FLOAT
 * Accept Type(s): STRING
 * Return Type(s): FLOAT
 */
@Extension(
        name = "parseFloat",
        namespace = "math",
        description = "This function returns the float value of the received string.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value that should be converted into a float value.",
                        type = {DataType.STRING},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"p1"})
        },
        returnAttributes = @ReturnAttribute(
                description = "The float value of the input parameter.",
                type = {DataType.FLOAT}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue string); \n" +
                        "from InValueStream \n" +
                        "select math:parseFloat(inValue) as output \n" +
                        "insert into OutMediationStream;",
                description = "The function converts the " +
                        "input value given in 'inValue',into its corresponding float value and directs the " +
                        "result into the output stream, OutMediationStream. For example, " +
                        "parseFloat(\"123\") returns 123.0.")
)
public class ParseFloatFunctionExtension extends FunctionExecutor {
    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:parseFloat() function," +
                    " required 1, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of " +
                    "math:parseFloat() function, " +
                    "required " + Attribute.Type.STRING + " but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
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
            return Float.parseFloat((String) data);
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.FLOAT;
    }

}
