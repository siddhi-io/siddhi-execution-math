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
import org.wso2.siddhi.core.executor.ConstantExpressionExecutor;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Collections;
import java.util.Map;
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
        description = "1.  A sequence of calls to rand() generates a stream of pseudo-random numbers. " +
                "This function uses the java.util.Random class internally." +
                "\n" +
                "2. A sequence of calls to rand(seed) generates a stream of pseudo-random numbers. " +
                "This function uses the java.util.Random class internally. ",
        parameters = {
                @Parameter(
                        name = "seed",
                        optional = true,
                        defaultValue = "defaultSeed",
                        description = "An optional seed value that will be used to generate the random number sequence",
                        type = {DataType.INT, DataType.LONG})},
        returnAttributes = @ReturnAttribute(
                description = "A stream of pseudo-random numbers",
                type = {DataType.DOUBLE}),
        examples = @Example(
                description = "A random double value between 0 and 1 will be generated using math:rand()",
                syntax = "define stream InValueStream (symbol string, price long, volume long); \n" +
                        "from InValueStream select symbol, math:rand() as randNumber \n" +
                        "select math:oct(inValue) as octValue \n" +
                        "insert into OutMediationStream;")
)
public class RandomFunctionExtension extends FunctionExecutor {

    private static final String RANDOM = "RANDOM";
    //state-variables
    private Random random;

    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
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

    }

    @Override
    protected Object execute(Object[] data) {
        return null;    // This method won't get called. Hence, unimplemented.
    }

    @Override
    protected Object execute(Object data) {
        return random.nextDouble();
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

    @Override
    public Map<String, Object> currentState() {
        return Collections.singletonMap(RANDOM, random);
    }

    @Override
    public void restoreState(Map<String, Object> map) {
        random = (Random) map.get(RANDOM);
    }
}
