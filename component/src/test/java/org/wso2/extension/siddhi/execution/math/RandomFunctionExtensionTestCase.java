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
import org.wso2.siddhi.core.exception.SiddhiAppCreationException;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;

public class RandomFunctionExtensionTestCase {
    private static Logger log = Logger.getLogger(RandomFunctionExtensionTestCase.class);
    private volatile int count;
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count = 0;
        eventArrived = false;
    }

    @Test
    public void testRandomFunctionExtensionWithoutSeed() throws InterruptedException {
        log.info("RandomFunctionExtension TestCase, without seed");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , math:rand() as randNumber " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                count = count + inEvents.length;
                eventArrived = true;
                if (inEvents.length == 3) {
                    double[] randNumbers = new double[3];
                    randNumbers[0] = (Double) inEvents[0].getData(1);
                    randNumbers[1] = (Double) inEvents[1].getData(1);
                    randNumbers[2] = (Double) inEvents[2].getData(1);
                    boolean isDuplicatePresent = false;
                    if (randNumbers[0] == randNumbers[1] ||
                            randNumbers[0] == randNumbers[2] ||
                            randNumbers[1] == randNumbers[2]) {
                        isDuplicatePresent = true;
                    }
                    AssertJUnit.assertEquals(false, isDuplicatePresent);
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L});
        inputHandler.send(new Object[]{"WSO2", 60.5f, 200L});
        inputHandler.send(new Object[]{"XYZ", 60.5f, 200L});
        Thread.sleep(100);
        AssertJUnit.assertEquals(3, count);
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testRandomFunctionExtensionWithSeed() throws InterruptedException {
        log.info("RandomFunctionExtension TestCase, with seed");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , math:rand(12) as randNumber " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                count = count + inEvents.length;
                eventArrived = true;
                if (inEvents.length == 3) {
                    double[] randNumbers = new double[3];
                    randNumbers[0] = (Double) inEvents[0].getData(1);
                    randNumbers[1] = (Double) inEvents[1].getData(1);
                    randNumbers[2] = (Double) inEvents[2].getData(1);
                    boolean isDuplicatePresent = false;
                    log.info(randNumbers[0] + ", " + randNumbers[1]);
                    if (randNumbers[0] == randNumbers[1] ||
                            randNumbers[0] == randNumbers[2] ||
                            randNumbers[1] == randNumbers[2]) {
                        isDuplicatePresent = true;
                    }
                    AssertJUnit.assertEquals(false, isDuplicatePresent);
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L});
        inputHandler.send(new Object[]{"WSO2", 60.5f, 200L});
        inputHandler.send(new Object[]{"XYZ", 60.5f, 200L});
        Thread.sleep(500);
        siddhiAppRuntime.shutdown();
        AssertJUnit.assertEquals(3, count);
        AssertJUnit.assertTrue(eventArrived);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testRandomFunctionExtensionExceptionTestCase1() throws InterruptedException {
        log.info("RandomFunctionExtension testRandomFunctionExtensionExceptionTestCase1");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , math:rand(symbol,symbol) as " +
                        "randNumber insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testRandomFunctionExtensionExceptionTestCase2() throws InterruptedException {
        log.info("RandomFunctionExtension testRandomFunctionExtensionExceptionTestCase2");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol object, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , math:rand(symbol) as " +
                        "randNumber insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testRandomFunctionExtensionExceptionTestCase3() throws InterruptedException {
        log.info("RandomFunctionExtension testRandomFunctionExtensionExceptionTestCase3");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , math:rand(symbol) as " +
                        "randNumber insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testRandomFunctionExtensionExceptionTestCase4() throws InterruptedException {
        log.info("RandomFunctionExtension testRandomFunctionExtensionExceptionTestCase4");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , math:rand(price) as randNumber " +
                        "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition +
                                                                                             query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                count = count + inEvents.length;
                eventArrived = true;
                if (inEvents.length == 3) {
                    double[] randNumbers = new double[3];
                    randNumbers[0] = (Double) inEvents[0].getData(1);
                    randNumbers[1] = (Double) inEvents[1].getData(1);
                    randNumbers[2] = (Double) inEvents[2].getData(1);
                    boolean isDuplicatePresent = false;
                    if (randNumbers[0] == randNumbers[1] ||
                        randNumbers[0] == randNumbers[2] ||
                        randNumbers[1] == randNumbers[2]) {
                        isDuplicatePresent = true;
                    }
                    AssertJUnit.assertEquals(false, isDuplicatePresent);
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L});
        inputHandler.send(new Object[]{"WSO2", 60.5f, 200L});
        inputHandler.send(new Object[]{"XYZ", 60.5f, 200L});
        Thread.sleep(500);
        siddhiAppRuntime.shutdown();
        AssertJUnit.assertEquals(3, count);
        AssertJUnit.assertTrue(eventArrived);
    }

    @Test
    public void testRandomFunctionExtensionWithSeed2() throws InterruptedException {
        log.info("RandomFunctionExtension TestCase, with seed2");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , math:rand(1223l) as randNumber " +
                        "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition +
                                                                                             query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                count = count + inEvents.length;
                eventArrived = true;
                if (inEvents.length == 3) {
                    double[] randNumbers = new double[3];
                    randNumbers[0] = (Double) inEvents[0].getData(1);
                    randNumbers[1] = (Double) inEvents[1].getData(1);
                    randNumbers[2] = (Double) inEvents[2].getData(1);
                    boolean isDuplicatePresent = false;
                    if (randNumbers[0] == randNumbers[1] ||
                        randNumbers[0] == randNumbers[2] ||
                        randNumbers[1] == randNumbers[2]) {
                        isDuplicatePresent = true;
                    }
                    AssertJUnit.assertEquals(false, isDuplicatePresent);
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L});
        inputHandler.send(new Object[]{"WSO2", 60.5f, 200L});
        inputHandler.send(new Object[]{"XYZ", 60.5f, 200L});
        Thread.sleep(500);
        siddhiAppRuntime.shutdown();
        AssertJUnit.assertEquals(3, count);
        AssertJUnit.assertTrue(eventArrived);
    }
}
