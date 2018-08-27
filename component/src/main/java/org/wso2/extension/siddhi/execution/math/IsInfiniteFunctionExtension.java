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
 * is_infinite(value);
 * Returns true if 'value' is infinite; Returns false otherwise.
 * Accept Type(s): FLOAT,DOUBLE
 * Return Type(s): BOOLEAN
 */
@Extension(
        name = "isInfinite",
        namespace = "math",
        description = "Wraps the `java.lang.Float.isInfinite()` and `java.lang.Double.isInfinite()` " +
                "functions and returns `true` if `p1` is infinitely large in magnitude and returns `false` if " +
                "otherwise.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value that should be checked if infinite",
                        type = {DataType.FLOAT, DataType.DOUBLE})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns true of input parameter is infinitely large. Returns false otherwise",
                type = {DataType.BOOL}),
        examples = @Example(
                description = "isInfinite(java.lang.Double.POSITIVE_INFINITY) returns true.",
                syntax = "define stream InValueStream (inValue1 double,inValue2 int); \n" +
                        "from InValueStream \n" +
                        "select math:isInfinite(inValue1) as isInfinite \n" +
                        "insert into OutMediationStream;")
)
public class IsInfiniteFunctionExtension extends FunctionExecutor {
    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:is_infinite() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.FLOAT
                && attributeExpressionExecutors[0].getReturnType() != Attribute.Type.DOUBLE) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of " +
                    "math:is_infinite() function, " + "required " + Attribute.Type.FLOAT + " or " +
                    Attribute.Type.DOUBLE + ", but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    protected Object execute(Object data) {
        if (data != null) {
            //type-conversion
            if (data instanceof Float) {
                return Float.isInfinite((Float) data);
            } else {
                return Double.isInfinite((Double) data);
            }
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:is_infinite() function cannot be null");
        }
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.BOOL;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}
