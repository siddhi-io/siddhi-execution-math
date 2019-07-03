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

import static io.siddhi.extension.execution.math.util.MathUtil.convertToDouble;

/**
 * atan(a); or atan(a,b);
 * Returns the arc-tangent(inverse tangent). The return value is in radian scale.
 * Accept Type(s) :DOUBLE/INT/FLOAT/LONG
 * Return Type(s): DOUBLE
 */
@Extension(//TODO: How The Parameter Descriptions should be given
        name = "atan",
        namespace = "math",
        description = "1. If a single `p1` is received, this function returns the arc-tangent (inverse tangent) " +
                "value of `p1`. \n" +
                "2. If `p1` is received along with an optional `p1`, it considers them as x and y coordinates and " +
                "returns the arc-tangent (inverse tangent) value. \n" +
                "The returned value is in radian scale. This function wraps the `java.lang.Math.atan()` function.",
        parameters = {
                @Parameter(
                        name = "p1",
                        description = "The value of the parameter whose arc-tangent (inverse tangent) is found. " +
                                "If the optional second parameter is given this represents the x coordinate of the " +
                                "(x,y) coordinate pair.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true),
                @Parameter(
                        name = "p2",
                        description = "This optional parameter represents the y coordinate of the (x,y)" +
                                " coordinate pair.",
                        dynamic = true,
                        optional = true,
                        defaultValue = "0D",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE})
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"p1"}),
                @ParameterOverload(parameterNames = {"p1", "p2"})
        },
        returnAttributes = @ReturnAttribute(
                description = "The arc-tangent (inverse tangent) value of the input. The output is in radian scale.",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue1 double, inValue2 double); \n" +
                        "from InValueStream \n" +
                        "select math:atan(inValue1, inValue2) as convertedValue \n" +
                        "insert into OutMediationStream;",
                 description = "If the 'inValue1' in the input stream is given, the function calculates " +
                         "the arc-tangent value of it and returns the arc-tangent value to the output stream, " +
                         "OutMediationStream. If both the 'inValue1' and 'inValue2' are given, then the function " +
                         "considers them to be x and y coordinates respectively and returns the calculated " +
                         "arc-tangent value to the output stream, OutMediationStream. " +
                         "For example, atan(12d, 5d) returns 1.1760052070951352.")
)
public class AtanFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length < 1 || attributeExpressionExecutors.length > 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:atan() function, " +
                    "required 1 or 2, but found " + attributeExpressionExecutors.length);
        }
        int attributeIndex = 0;
        for (ExpressionExecutor expressionExecutor : attributeExpressionExecutors) {
            Attribute.Type attributeType = expressionExecutor.getReturnType();
            if (!((attributeType == Attribute.Type.DOUBLE)
                    || (attributeType == Attribute.Type.INT)
                    || (attributeType == Attribute.Type.FLOAT)
                    || (attributeType == Attribute.Type.LONG))) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the argument at index " +
                        attributeIndex + " of math:atan() function," +
                        "required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                        " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                        ", but found " + attributeType.toString());
            }
            attributeIndex++;
        }
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        if (data[0] != null && data[1] != null) {
            return Math.atan2(convertToDouble(data[0]), convertToDouble(data[1]));
        }
        return null;
    }

    @Override
    protected Object execute(Object data, State state) {
        if (data != null) {
            return Math.atan(convertToDouble(data));
        }
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

}
