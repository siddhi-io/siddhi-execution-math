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
 * acos(a);
 * Returns the arc-cosine(inverse cosine) of 'a' if -1&lt;=a&lt;=1 or NULL otherwise. The return value is in radian
 * scale.
 * Accept Type(s) :FLOAT/DOUBLE
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "acos",
        namespace = "math",
        description = "If -1 <= p1 <= 1, returns the arc-cosine (inverse cosine) of p1. " +
                "If not, it returns NULL. The returned value is in radian scale. This function wraps the " +
                "java.lang.Math.acos()function.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value of which the arc-cosine (inverse cosine) should be found",
                        type = {DataType.FLOAT, DataType.DOUBLE})
        },
        returnAttributes = @ReturnAttribute(
                description = "The arc-cosine (inverse cosine) value of the input parameter, output in radian scale",
                type = {DataType.DOUBLE}),
        examples = @Example(
                description = "acos(0.5) returns 1.0471975511965979.",
                syntax = "define stream InValueStream (inValue double); \n" +
                        "from InValueStream \n" +
                        "select math:acos(inValue) as acosValue \n" +
                        "insert into OutMediationStream;")
)
public class AcosFunctionExtension extends FunctionExecutor {

    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:acos() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.DOUBLE))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:acos() " +
                    "function, required " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    " but found " + attributeType.toString());
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
                float inputFloat = (Float) data;
                return Math.acos((double) inputFloat);
            } else if (data instanceof Double) {
                double inputValue = (Double) data;
                return Math.acos(inputValue);
            }
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:acos() function cannot be null");
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}
