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

import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.wso2.extension.siddhi.execution.math.util.UnitTestAppender;
import org.wso2.siddhi.core.SiddhiAppRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.exception.SiddhiAppCreationException;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.StreamJunction;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;

public class CubeRootFunctionExtensionTestCase {
    protected static SiddhiManager siddhiManager;
    private static Logger logger = Logger.getLogger(CubeRootFunctionExtensionTestCase.class);

    @Test
    public void testProcess() throws Exception {
        logger.info("CubeRootFunctionExtension TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                + "select math:cbrt(inValue) as cbrtValue "
                + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime =
                siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                Double result;
                for (Event event : inEvents) {
                    result = (Double) event.getData(0);
                    AssertJUnit.assertEquals((Double) 2.5712815906582356, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Double[]{17d});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase1() throws Exception {
        logger.info("CubeRootFunctionExtension exceptionTestCase1");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:cbrt(inValue,inValue) as cbrtValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase2() throws Exception {
        logger.info("CubeRootFunctionExtension exceptionTestCase2");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue object);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:cbrt(inValue) as cbrtValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test
    public void exceptionTestCase3() throws Exception {
        logger.info("CubeRootFunctionExtension exceptionTestCase3");
        UnitTestAppender appender = new UnitTestAppender();
        logger = Logger.getLogger(StreamJunction.class);
        logger.addAppender(appender);
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:cbrt(inValue) as cbrtValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Double[]{null});
        Thread.sleep(100);
        AssertJUnit.assertTrue(appender.getMessages().contains("Input to the math:cbrt() function cannot be null"));
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcessInteger() throws Exception {
        logger.info("CubeRootFunctionExtension testProcessInteger");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:cbrt(inValue) as cbrtValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                Double result;
                for (Event event : inEvents) {
                    result = (Double) event.getData(0);
                    AssertJUnit.assertEquals((Double) 2.5712815906582356, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Integer[]{17});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcessLong() throws Exception {
        logger.info("CubeRootFunctionExtension testProcessLong");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:cbrt(inValue) as cbrtValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                Double result;
                for (Event event : inEvents) {
                    result = (Double) event.getData(0);
                    AssertJUnit.assertEquals((Double) 55.7691388638383, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Long[]{173453L});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcessFloat() throws Exception {
        logger.info("CubeRootFunctionExtension testProcessFloat");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:cbrt(inValue) as cbrtValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                Double result;
                for (Event event : inEvents) {
                    result = (Double) event.getData(0);
                    AssertJUnit.assertEquals((Double) 5.576913917782566, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Float[]{173.453f});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }
}
