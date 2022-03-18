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
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.util.EventPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PowerFunctionExtensionTestCase {
    protected static SiddhiManager siddhiManager;
    private static Logger logger = LogManager.getLogger(PowerFunctionExtensionTestCase.class);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        eventArrived = false;
    }

    @Test
    public void testProcess() throws Exception {
        logger.info("PowerFunctionExtension TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double, inValue2 double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                + "select math:power(inValue1,inValue2) as powerValue "
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
                    AssertJUnit.assertEquals((Double) 175.61599999999996, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Double[]{5.6d, 3.0d});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase1() throws Exception {
        logger.info("PowerFunctionExtension exceptionTestCase1");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double, inValue2 double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2,inValue1) as powerValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase2() throws Exception {
        logger.info("PowerFunctionExtension exceptionTestCase2");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 object, inValue2 double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2) as powerValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void exceptionTestCase3() throws Exception {
        logger.info("PowerFunctionExtension exceptionTestCase3");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double, inValue2 object);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2) as powerValue "
                                         + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test
    public void exceptionTestCase4() throws Exception {
        logger.info("PowerFunctionExtension exceptionTestCase4");
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double, inValue2 double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2) as powerValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    AssertJUnit.assertEquals(null, event.getData(0));
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Double[]{null, 3.0d});
        Thread.sleep(100);
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void exceptionTestCase5() throws Exception {
        logger.info("PowerFunctionExtension exceptionTestCase5");
        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 double, inValue2 double);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2) as powerValue "
                                         + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream +
                                                                                             eventFuseExecutionPlan);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    AssertJUnit.assertEquals(null, event.getData(0));
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Double[]{5.6d, null});
        Thread.sleep(100);
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcessInteger() throws Exception {
        logger.info("PowerFunctionExtension testProcessInteger");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 int, inValue2 int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2) as powerValue "
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
                    AssertJUnit.assertEquals((Double) 2.7902351804926126E52, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Integer[]{56, 30});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcessLong() throws Exception {
        logger.info("PowerFunctionExtension testProcessLong");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 long, inValue2 long);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2) as powerValue "
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
                    AssertJUnit.assertEquals((Double) 9.430298923255593E112, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Long[]{324L, 45L});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testProcessFloat() throws Exception {
        logger.info("PowerFunctionExtension testProcessFloat");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue1 float, inValue2 float);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                                         + "select math:power(inValue1,inValue2) as powerValue "
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
                    AssertJUnit.assertEquals((Double) 2.8447817306898337E68, result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Float[]{32.344f, 45.34f});
        Thread.sleep(100);
        siddhiAppRuntime.shutdown();
    }
}
