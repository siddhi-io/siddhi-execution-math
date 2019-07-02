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

/**
 * pi();
 * Returns the value of pi.
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "pi",
        namespace = "math",
        description = "This function returns the `java.lang.Math.PI` constant, which is the closest value to pi, " +
                "i.e., the ratio of the circumference of a circle to its diameter. ",
        parameterOverloads = {
                @ParameterOverload()
        },
        returnAttributes = @ReturnAttribute(
                description = "The value 3.141592653589793, which is the closest value to pi.",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (inValue double); \n" +
                        "from InValueStream \n" +
                        "select math:pi() as piValue \n" +
                        "insert into OutMediationStream;",
                description = "pi() always returns 3.141592653589793.")
)
public class PiFunctionExtension extends FunctionExecutor {

    private Attribute.Type returnType = Attribute.Type.DOUBLE;

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        //Nothing to be done.
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    protected Object execute(Object data, State state) {
        return Math.PI;
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

}
