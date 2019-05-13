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

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.query.output.callback.QueryCallback;
import io.siddhi.core.stream.StreamJunction;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.util.EventPrinter;
import io.siddhi.extension.execution.math.util.UnitTestAppender;
import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class MinFunctionExtensionTestCase {
    protected static SiddhiManager siddhiManager;
    private static Logger logger = Logger.getLogger(MinFunctionExtensionTestCase.class);

    @Test
    public void testProcess() throws Exception {
        logger.info("MinFunctionExtension TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                + "select math:min(inValue1,inValue2) as minValue "
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
                    AssertJUnit.assertEquals((Double) 91d, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{123.67d, 91});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase1() throws Exception {
        logger.info("MinFunctionExtension exceptionTestCase1");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2,inValue1) as minValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase2() throws Exception {
        logger.info("MinFunctionExtension exceptionTestCase2");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 object,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase3() throws Exception {
        logger.info("MinFunctionExtension exceptionTestCase3");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double,inValue2 object);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test
    public void exceptionTestCase4() throws Exception {
        logger.info("MinFunctionExtension exceptionTestCase4");
        UnitTestAppender appender = new UnitTestAppender();
        logger = Logger.getLogger(StreamJunction.class);
        logger.addAppender(appender);
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
        inputHandler.send(new Object[]{null, 91});
        Thread.sleep(100);
        AssertJUnit.assertTrue(appender.getMessages().contains("Input to the math:min() function cannot be null"));
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void exceptionTestCase5() throws Exception {
        logger.info("MinFunctionExtension exceptionTestCase4");
        UnitTestAppender appender = new UnitTestAppender();
        logger = Logger.getLogger(StreamJunction.class);
        logger.addAppender(appender);
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
        inputHandler.send(new Object[]{123.67d, null});
        Thread.sleep(100);
        AssertJUnit.assertTrue(appender.getMessages().contains("Input to the math:min() function cannot be null"));
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcess2() throws Exception {
        logger.info("MinFunctionExtension testProcess2");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 int,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
                    AssertJUnit.assertEquals((Double) 91d, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{123, 91});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcess3() throws Exception {
        logger.info("MinFunctionExtension testProcess3");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 long,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
                    AssertJUnit.assertEquals((Double) 91d, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{1231324L, 91});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcess4() throws Exception {
        logger.info("MinFunctionExtension testProcess4");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 float,inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
                    AssertJUnit.assertEquals((Double) 91d, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{123.1324f, 91});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcess5() throws Exception {
        logger.info("MinFunctionExtension testProcess5");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 float,inValue2 double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
                    AssertJUnit.assertEquals((Double) 91.324d, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{123.1324f, 91.324d});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcess6() throws Exception {
        logger.info("MinFunctionExtension testProcess6");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 float,inValue2 long);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
                    AssertJUnit.assertEquals((Double) 91324.0d, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{123132.54454f, 91324L});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcess7() throws Exception {
        logger.info("MinFunctionExtension testProcess7");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 float,inValue2 float);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:min(inValue1,inValue2) as minValue "
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
                    AssertJUnit.assertEquals((Double) 91.3239974975586d, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{123.1324f, 91.324f});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }
}
