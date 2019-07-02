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
 * power(value,toPower);
 * A Class which is used to calculate power of a value.
 * value - Accept Type(s):DOUBLE/INT/FLOAT/LONG
 * toPower - Accept Type(s):DOUBLE/INT/FLOAT/LONG
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "power",
        namespace = "math",
        description = "This function raises the given value to a given power.",
        parameters = {
                @Parameter(
                        name = "value",
                        description = "The value that should be raised to the power of 'to.power' input parameter.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true),
                @Parameter(
                        name = "to.power",
                        description = "The power to which the 'value' input parameter should be raised.",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"value", "to.power"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the  'value' input parameter raised to the power of 'to.power' " +
                        "input parameter.",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue1 double, inValue2 double); \n" +
                        "from InValueStream \n" +
                        "select math:power(inValue1,inValue2) as powerValue \n" +
                        "insert into OutMediationStream;",
                description = "This function raises the 'inValue1' to the power of 'inValue2' and directs " +
                        "the output to the output stream, 'OutMediationStream. For example, (5.6d, 3.0d)" +
                        " returns 175.61599999999996.")
)
public class PowerFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:power() function, " +
                    "required 2, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "math:power() function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
        attributeType = attributeExpressionExecutors[1].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "math:power() function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        if (data[0] != null && data[1] != null) {
            return Math.pow(convertToDouble(data[0]), convertToDouble(data[1]));
        }
        return null;
    }

    @Override
    protected Object execute(Object data, State state) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

}
