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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.siddhi.core.SiddhiAppRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;

import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class PercentileFunctionExtensionTestCase {
    private static final String INPUT_STREAM_DOUBLE = "define stream inputStream (sensorId int, temperature double);";
    private static final String INPUT_STREAM_FLOAT = "define stream inputStream (sensorId int, temperature float);";
    private static final String INPUT_STREAM_INT = "define stream inputStream (sensorId int, temperature int);";
    private static final String INPUT_STREAM_LONG = "define stream inputStream (sensorId int, temperature long);";
    protected static SiddhiManager siddhiManager;
    private static Logger logger = Logger.getLogger(PercentileFunctionExtensionTestCase.class);
    private CountDownLatch countDownLatch;
    private volatile int count;
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count = 0;
        eventArrived = false;
    }

    @Test
    public void testPercentileFunctionExtensionDouble1() throws Exception {
        logger.info("PercentileFunctionExtension no window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_DOUBLE + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10d});
        inputHandler.send(new Object[]{2, 30d});
        inputHandler.send(new Object[]{3, 50d});
        inputHandler.send(new Object[]{4, 40d});
        inputHandler.send(new Object[]{5, 80d});
        inputHandler.send(new Object[]{6, 60d});
        inputHandler.send(new Object[]{7, 20d});
        inputHandler.send(new Object[]{8, 90d});
        inputHandler.send(new Object[]{9, 70d});
        inputHandler.send(new Object[]{10, 100d});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionDouble2() throws Exception {
        logger.info("PercentileFunctionExtension length window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.length(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_DOUBLE + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10d});
        inputHandler.send(new Object[]{2, 30d});
        inputHandler.send(new Object[]{3, 100d});
        inputHandler.send(new Object[]{4, 40d});
        inputHandler.send(new Object[]{5, 80d});
        inputHandler.send(new Object[]{6, 60d});
        inputHandler.send(new Object[]{7, 20d});
        inputHandler.send(new Object[]{8, 90d});
        inputHandler.send(new Object[]{9, 70d});
        inputHandler.send(new Object[]{10, 50d});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionDouble3() throws Exception {
        logger.info("PercentileFunctionExtension lengthBatch window test case.");

        final int expectedNoOfEvents = 2;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.lengthBatch(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_DOUBLE + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10d});
        inputHandler.send(new Object[]{2, 30d});
        inputHandler.send(new Object[]{3, 100d});
        inputHandler.send(new Object[]{4, 40d});
        inputHandler.send(new Object[]{5, 80d});
        inputHandler.send(new Object[]{6, 60d});
        inputHandler.send(new Object[]{7, 20d});
        inputHandler.send(new Object[]{8, 90d});
        inputHandler.send(new Object[]{9, 70d});
        inputHandler.send(new Object[]{10, 50d});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(2, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionFloat1() throws Exception {
        logger.info("PercentileFunctionExtension no window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_FLOAT + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10f});
        inputHandler.send(new Object[]{2, 30f});
        inputHandler.send(new Object[]{3, 50f});
        inputHandler.send(new Object[]{4, 40f});
        inputHandler.send(new Object[]{5, 80f});
        inputHandler.send(new Object[]{6, 60f});
        inputHandler.send(new Object[]{7, 20f});
        inputHandler.send(new Object[]{8, 90f});
        inputHandler.send(new Object[]{9, 70f});
        inputHandler.send(new Object[]{10, 100f});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionFloat2() throws Exception {
        logger.info("PercentileFunctionExtension length window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.length(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_FLOAT + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10f});
        inputHandler.send(new Object[]{2, 30f});
        inputHandler.send(new Object[]{3, 100f});
        inputHandler.send(new Object[]{4, 40f});
        inputHandler.send(new Object[]{5, 80f});
        inputHandler.send(new Object[]{6, 60f});
        inputHandler.send(new Object[]{7, 20f});
        inputHandler.send(new Object[]{8, 90f});
        inputHandler.send(new Object[]{9, 70f});
        inputHandler.send(new Object[]{10, 50f});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionFloat3() throws Exception {
        logger.info("PercentileFunctionExtension lengthBatch window test case.");

        final int expectedNoOfEvents = 2;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.lengthBatch(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_FLOAT + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10f});
        inputHandler.send(new Object[]{2, 30f});
        inputHandler.send(new Object[]{3, 100f});
        inputHandler.send(new Object[]{4, 40f});
        inputHandler.send(new Object[]{5, 80f});
        inputHandler.send(new Object[]{6, 60f});
        inputHandler.send(new Object[]{7, 20f});
        inputHandler.send(new Object[]{8, 90f});
        inputHandler.send(new Object[]{9, 70f});
        inputHandler.send(new Object[]{10, 50f});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(2, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionInt1() throws Exception {
        logger.info("PercentileFunctionExtension no window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_INT + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10});
        inputHandler.send(new Object[]{2, 30});
        inputHandler.send(new Object[]{3, 50});
        inputHandler.send(new Object[]{4, 40});
        inputHandler.send(new Object[]{5, 80});
        inputHandler.send(new Object[]{6, 60});
        inputHandler.send(new Object[]{7, 20});
        inputHandler.send(new Object[]{8, 90});
        inputHandler.send(new Object[]{9, 70});
        inputHandler.send(new Object[]{10, 100});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionInt2() throws Exception {
        logger.info("PercentileFunctionExtension length window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.length(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_INT + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10});
        inputHandler.send(new Object[]{2, 30});
        inputHandler.send(new Object[]{3, 100});
        inputHandler.send(new Object[]{4, 40});
        inputHandler.send(new Object[]{5, 80});
        inputHandler.send(new Object[]{6, 60});
        inputHandler.send(new Object[]{7, 20});
        inputHandler.send(new Object[]{8, 90});
        inputHandler.send(new Object[]{9, 70});
        inputHandler.send(new Object[]{10, 50});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionInt3() throws Exception {
        logger.info("PercentileFunctionExtension lengthBatch window test case.");

        final int expectedNoOfEvents = 2;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.lengthBatch(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_INT + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10});
        inputHandler.send(new Object[]{2, 30});
        inputHandler.send(new Object[]{3, 100});
        inputHandler.send(new Object[]{4, 40});
        inputHandler.send(new Object[]{5, 80});
        inputHandler.send(new Object[]{6, 60});
        inputHandler.send(new Object[]{7, 20});
        inputHandler.send(new Object[]{8, 90});
        inputHandler.send(new Object[]{9, 70});
        inputHandler.send(new Object[]{10, 50});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(2, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionLong1() throws Exception {
        logger.info("PercentileFunctionExtension no window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_LONG + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(50.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(80.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10L});
        inputHandler.send(new Object[]{2, 30L});
        inputHandler.send(new Object[]{3, 50L});
        inputHandler.send(new Object[]{4, 40L});
        inputHandler.send(new Object[]{5, 80L});
        inputHandler.send(new Object[]{6, 60L});
        inputHandler.send(new Object[]{7, 20L});
        inputHandler.send(new Object[]{8, 90L});
        inputHandler.send(new Object[]{9, 70L});
        inputHandler.send(new Object[]{10, 100L});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionLong2() throws Exception {
        logger.info("PercentileFunctionExtension length window test case.");

        final int expectedNoOfEvents = 10;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.length(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_LONG + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(10.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(30.0, event.getData(0));
                            break;
                        case 3:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 4:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 5:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 6:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 7:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 8:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 9:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        case 10:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10L});
        inputHandler.send(new Object[]{2, 30L});
        inputHandler.send(new Object[]{3, 100L});
        inputHandler.send(new Object[]{4, 40L});
        inputHandler.send(new Object[]{5, 80L});
        inputHandler.send(new Object[]{6, 60L});
        inputHandler.send(new Object[]{7, 20L});
        inputHandler.send(new Object[]{8, 90L});
        inputHandler.send(new Object[]{9, 70L});
        inputHandler.send(new Object[]{10, 50L});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(10, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }

    @Test
    public void testPercentileFunctionExtensionLong3() throws Exception {
        logger.info("PercentileFunctionExtension lengthBatch window test case.");

        final int expectedNoOfEvents = 2;
        countDownLatch = new CountDownLatch(expectedNoOfEvents);
        siddhiManager = new SiddhiManager();

        String executionPlan = ("@info(name = 'query1') from inputStream#window.lengthBatch(5) "
                + "select math:percentile(temperature, 97.0) as percentile "
                + "insert into outputStream;");
        SiddhiAppRuntime executionPlanRuntime = siddhiManager
                .createSiddhiAppRuntime(INPUT_STREAM_LONG + executionPlan);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                eventArrived = true;
                for (Event event : inEvents) {
                    countDownLatch.countDown();
                    count++;
                    switch (count) {
                        case 1:
                            AssertJUnit.assertEquals(100.0, event.getData(0));
                            break;
                        case 2:
                            AssertJUnit.assertEquals(90.0, event.getData(0));
                            break;
                        default:
                            AssertJUnit.fail();
                    }
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();

        inputHandler.send(new Object[]{1, 10L});
        inputHandler.send(new Object[]{2, 30L});
        inputHandler.send(new Object[]{3, 100L});
        inputHandler.send(new Object[]{4, 40L});
        inputHandler.send(new Object[]{5, 80L});
        inputHandler.send(new Object[]{6, 60L});
        inputHandler.send(new Object[]{7, 20L});
        inputHandler.send(new Object[]{8, 90L});
        inputHandler.send(new Object[]{9, 70L});
        inputHandler.send(new Object[]{10, 50L});

        countDownLatch.await(1000, MILLISECONDS);
        AssertJUnit.assertEquals(2, count);
        AssertJUnit.assertTrue(eventArrived);
        executionPlanRuntime.shutdown();
    }
}
