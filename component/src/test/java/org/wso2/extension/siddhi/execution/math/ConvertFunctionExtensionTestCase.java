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

public class ConvertFunctionExtensionTestCase {
    protected static SiddhiManager siddhiManager;
    private static Logger logger = Logger.getLogger(ConvertFunctionExtensionTestCase.class);

    @Test
    public void testProcess() throws Exception {
        logger.info("ConvertFunctionExtension TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                + "select math:conv(inValue,fromBase,toBase) as convertedValue "
                + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime =
                siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                String result;
                for (Event event : inEvents) {
                    result = (String) event.getData(0);
                    AssertJUnit.assertEquals("127", result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"7f", 16, 10});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase1() throws Exception {
        logger.info("ConvertFunctionExtension exceptionTestCase1");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:conv(inValue,fromBase,toBase,toBase) as convertedValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase2() throws Exception {
        logger.info("ConvertFunctionExtension exceptionTestCase2");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:conv(toBase,fromBase,toBase) as convertedValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase3() throws Exception {
        logger.info("ConvertFunctionExtension exceptionTestCase3");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:conv(inValue,inValue,toBase) as convertedValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase4() throws Exception {
        logger.info("ConvertFunctionExtension exceptionTestCase4");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:conv(inValue,fromBase,inValue) as convertedValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);
    }

    @Test
    public void exceptionTestCase5() throws Exception {
        logger.info("ConvertFunctionExtension exceptionTestCase5");
        UnitTestAppender appender = new UnitTestAppender();
        logger = Logger.getLogger(StreamJunction.class);
        logger.addAppender(appender);
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:conv(inValue,fromBase,toBase) as convertedValue "
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
        inputHandler.send(new Object[]{null, 16, 10});
        Thread.sleep(100);
        AssertJUnit.assertTrue(appender.getMessages().contains("Invalid input given to math:conv() function. "
                                                                       + "First argument cannot be null."));
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void exceptionTestCase6() throws Exception {
        logger.info("ConvertFunctionExtension exceptionTestCase6");
        UnitTestAppender appender = new UnitTestAppender();
        logger = Logger.getLogger(StreamJunction.class);
        logger.addAppender(appender);
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:conv(inValue,fromBase,toBase) as convertedValue "
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
        inputHandler.send(new Object[]{"7f", null, 10});
        Thread.sleep(100);
        AssertJUnit.assertTrue(appender.getMessages().contains("Invalid input given to math:conv() function. "
                                                                       + "Second argument cannot be null."));
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void exceptionTestCase7() throws Exception {
        logger.info("ConvertFunctionExtension exceptionTestCase7");
        UnitTestAppender appender = new UnitTestAppender();
        logger = Logger.getLogger(StreamJunction.class);
        logger.addAppender(appender);
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string,fromBase int,toBase int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:conv(inValue,fromBase,toBase) as convertedValue "
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
        inputHandler.send(new Object[]{"7f", 16, null});
        Thread.sleep(100);
        AssertJUnit.assertTrue(appender.getMessages().contains("Invalid input given to math:conv() function. "
                                                                       + "Third argument cannot be null."));
        siddhiAppRuntime.shutdown();
    }
}
