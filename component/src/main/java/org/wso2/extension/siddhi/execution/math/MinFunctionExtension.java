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
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Map;

import static org.wso2.extension.siddhi.execution.math.util.MathUtil.convertToDouble;

/**
 * min(a,b);
 * Returns the minimum of 'a' and 'b'.
 * Accept Type(s): INT,LONG,FLOAT,DOUBLE
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "min",
        namespace = "math",
        description = "This function returns the smaller value of `p1` and `p2`.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "One of the input values that are to be compared in order to find " +
                                "the smaller value.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE}),
                @Parameter(
                        name = "p2",
                        description = "The input value that is to be compared with 'p1' in order " +
                                "to find the smaller value.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the smaller value of the two input parameters.",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue1 double,inValue2 int); \n" +
                        "from InValueStream \n" +
                        "select math:min(inValue1,inValue2) as minValue \n" +
                        "insert into OutMediationStream;",
                description = "If two input values, 'inValue1' and 'inValue2' are given, " +
                        "the function compares them and directs the smaller value of the two to the output stream, " +
                        "OutMediationStream. For example, min(123.67d, 91) returns 91.")
)
public class MinFunctionExtension extends FunctionExecutor {
    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:min() function, " +
                    "required 2, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "math:min() function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
        attributeType = attributeExpressionExecutors[1].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "math:min() function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        if (data[0] != null && data[1] != null) {
            return Math.min(convertToDouble(data[0]), convertToDouble(data[1]));
        }
        return null;
    }

    @Override
    protected Object execute(Object data) {
        return null;    // This method won't get called. Hence, unimplemented.
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
