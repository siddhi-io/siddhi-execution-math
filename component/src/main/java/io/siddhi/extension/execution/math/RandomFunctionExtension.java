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
import io.siddhi.core.executor.ConstantExpressionExecutor;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Random;

/**
 * rand() or rand(seed);
 * A sequence of calls to rand(seed) generates a stream of pseudo-random numbers.
 * Accept Type(s): INT/LONG
 * Return Type(s): DOUBLE
 */
@Extension(
        name = "rand",
        namespace = "math",
        description = "This returns a stream of pseudo-random numbers when a sequence of calls are sent to the" +
                " `rand()`. " +
                "Optionally, it is possible to define a seed, i.e., `rand(seed)` using which the pseudo-random " +
                "numbers are generated. These functions internally use the `java.util.Random` class.",
        parameters = {
                @Parameter(
                        name = "seed",
                        optional = true,
                        defaultValue = "defaultSeed",
                        description = "An optional seed value that will be used to generate the random" +
                                " number sequence.",
                        type = {DataType.INT, DataType.LONG},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(),
                @ParameterOverload(parameterNames = {"seed"})
        },
        returnAttributes = @ReturnAttribute(
                description = "A stream of pseudo-random numbers",
                type = {DataType.DOUBLE}),
        examples = @Example(

                syntax = "define stream InValueStream (symbol string, price long, volume long); \n" +
                        "from InValueStream select symbol, math:rand() as randNumber \n" +
                        "select math:oct(inValue) as octValue \n" +
                        "insert into OutMediationStream;",
                description = "In the example given above, a random double value between 0 and 1 will be" +
                        " generated using math:rand().")
)
public class RandomFunctionExtension extends FunctionExecutor {

    private static final String RANDOM = "RANDOM";
    //state-variables
    private Random random;

    @Override
    protected StateFactory init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length > 1) {
            throw new SiddhiAppValidationException("Invalid no of Arguments Passed. Required 0 or 1. Found " +
                    attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors.length == 1) {
            if (attributeExpressionExecutors[0] == null) {
                throw new SiddhiAppValidationException("Invalid input given to math:rand() function. The 'seed' " +
                        "argument cannot be null");
            }
            Attribute.Type type = attributeExpressionExecutors[0].getReturnType();
            if (type != Attribute.Type.INT && type != Attribute.Type.LONG) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the argument of math:rand() " +
                        "function, required " + Attribute.Type.INT + " or " + Attribute.Type.LONG + ", but found " +
                        type.toString());
            }
            long seed;
            if (attributeExpressionExecutors[0] instanceof ConstantExpressionExecutor) {
                Object constantObj = ((ConstantExpressionExecutor) attributeExpressionExecutors[0]).getValue();
                if (type == Attribute.Type.INT) {
                    int intSeed;
                    intSeed = (Integer) constantObj;
                    seed = (long) intSeed;
                } else {
                    seed = (Long) constantObj;
                }
            } else {
                throw new SiddhiAppValidationException("The seed argument of math:rand() function should be a " +
                        "constant, but found " + attributeExpressionExecutors[0].getClass().toString());
                //This should be a constant because the instantiation of
                // java.util.Random should be done in the init() method.
            }
            random = new Random(seed);
        } else {
            random = new Random();
        }
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    protected Object execute(Object data, State state) {
        return random.nextDouble();
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

}
