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
 * is_nan(value);
 * Returns true if 'value' is NaN; Returns false otherwise.
 * Accept Type(s): FLOAT,DOUBLE
 * Return Type(s): BOOLEAN
 */
@Extension(
        name = "isNan",
        namespace = "math",
        description = "This function wraps the `java.lang.Float.isNaN()` and `java.lang.Double.isNaN()` " +
                "functions and returns `true` if `p1` is NaN (Not-a-Number), and returns `false` if otherwise.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value of the parameter which the function determines to be either NaN " +
                                "or a number.",
                        type = {DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"p1"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns true, if the input parameter is NaN (Not-a-Number), and false otherwise.",
                type = {DataType.BOOL}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue1 double,inValue2 int); \n" +
                        "from InValueStream \n" +
                        "select math:isNan(inValue1) as isNaN \n" +
                        "insert into OutMediationStream;",
                description = "If the 'inValue1' in the input stream has a value that is undefined, then the function" +
                        " considers it as an 'NaN' value and directs 'True' to the output stream, " +
                        "OutMediationStream. For example, isNan(java.lang.Math.log(-12d)) returns true.")
)
public class IsNanFunctionExtension extends FunctionExecutor {
    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:is_nan() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.FLOAT
                && attributeExpressionExecutors[0].getReturnType() != Attribute.Type.DOUBLE) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:is_nan() " +
                    "function, required " + Attribute.Type.FLOAT + " or " +
                    Attribute.Type.DOUBLE + ", but found " +
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
            //type-conversion
            if (data instanceof Float) {
                return Float.isNaN((Float) data);
            } else {
                return Double.isNaN((Double) data);
            }
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.BOOL;
    }

}
