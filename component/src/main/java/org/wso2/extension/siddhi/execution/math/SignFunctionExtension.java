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

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
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

/**
 * signum(a);
 * Returns the sign of a as '1.0' (if a is positive) or '-1.0' (if a is
 * negative), '0.0' otherwise.
 * Accept Type(s) :DOUBLE/INT/FLOAT/LONG
 * Return Type(s): INT
 * Returning an INT rather than a DOUBLE because an INT is *sufficient* to represent -1,0 and 1
 */
@Extension(
        name = "signum",
        namespace = "math",
        description = "This returns +1, 0, or -1 for the given positive, zero and negative values respectively. This " +
                "function wraps the `java.lang.Math.signum()` function.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value that should be checked to be positive, negative or zero.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE})},
        returnAttributes = @ReturnAttribute(
                description = "Returns 1.0 if input parameter is positive. Returns -1.0 if the same is negative. " +
                        "Returns 0.0 otherwise",
                type = {DataType.INT}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue double); \n" +
                        "from InValueStream \n" +
                        "select math:signum(inValue) as sign \n" +
                        "insert into OutMediationStream;",
                description = "The function evaluates the 'inValue' given to be positive, " +
                        "negative or zero and directs the result to the output stream, 'OutMediationStream'. " +
                        "For example, signum(-6.32d) returns -1.")
)
public class SignFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:signum() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:signum() " +
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
        double inputValue = 0d;
        double returnValue = 0d;

        if (data != null) {
            //type-conversion
            if (data instanceof Integer) {
                int inputInt = (Integer) data;
                return (int) Math.signum((double) inputInt);
            } else if (data instanceof Long) {
                long inputLong = (Long) data;
                return (int) Math.signum((double) inputLong);
            } else if (data instanceof Float) {
                float inputFloat = (Float) data;
                return (int) Math.signum((double) inputFloat);
            } else if (data instanceof Double) {
                return (int) Math.signum((Double) data);
            }
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:signum() function cannot be null");
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.INT;
    }

}
