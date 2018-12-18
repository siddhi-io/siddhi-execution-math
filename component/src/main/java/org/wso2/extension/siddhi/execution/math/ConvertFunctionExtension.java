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
 * conv(a,fromBase,toBase)
 * convert the value from one base to other base.
 * Accept Type(s): STRING, INT, INT
 * Return Type(s): STRING
 */
@Extension(
        name = "conv",
        namespace = "math",
        description = "This function converts `a` from the `fromBase` base to the `toBase` base.",
        parameters = {
                @Parameter(
                        name = "a",
                        description = "The value whose base should be changed. Input should be given as a 'String'.",
                        type = {DataType.STRING}),
                @Parameter(
                        name = "from.base",
                        description = "The source base of the input parameter 'a'.",
                        type = {DataType.INT}),
                @Parameter(
                        name = "to.base",
                        description = "The target base that the input parameter 'a' should be converted into.",
                        type = {DataType.INT})
        },
        returnAttributes = @ReturnAttribute(
                description = "The value of the parameter 'a' when converted from the source base to the target base." +
                        " Output is obtained as a String variable.",
                type = {DataType.STRING}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue string,fromBase int,toBase int); \n" +
                        "from InValueStream \n" +
                        "select math:conv(inValue,fromBase,toBase) as convertedValue \n" +
                        "insert into OutMediationStream;",
                description = "If the 'inValue' in the input stream is given, and the base in which it " +
                              "currently resides in and the base to which it should be " +
                              "converted to is specified, the function converts it into a string in the target base " +
                              "and directs it to the output stream, OutMediationStream. " +
                              "For example, conv(\"7f\", 16, 10) returns \"127\".")
)
public class ConvertFunctionExtension extends FunctionExecutor {

    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 3) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:conv() function, " +
                    "required 3, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "math:conv() function, required " + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
        }
        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.INT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "math:conv() function, required " + Attribute.Type.INT + ", but found " +
                    attributeExpressionExecutors[1].getReturnType().toString());
        }
        if (attributeExpressionExecutors[2].getReturnType() != Attribute.Type.INT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the third argument of " +
                    "math:conv() function, required " + Attribute.Type.INT + ", but found " +
                    attributeExpressionExecutors[2].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        if (data[0] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to math:conv() function. " +
                    "First argument cannot be null");
        }
        if (data[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to math:conv() function. " +
                    "Second argument cannot be null");
        }
        if (data[2] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to math:conv() function. " +
                    "Third argument cannot be null");
        }
        String nValue = (String) data[0];
        int fromBase = (Integer) data[1];
        int toBase = (Integer) data[2];
        return Integer.toString(
                Integer.parseInt(nValue, fromBase), toBase);
    }

    @Override
    protected Object execute(Object data) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.STRING;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}
