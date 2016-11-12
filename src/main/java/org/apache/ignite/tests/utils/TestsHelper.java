/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.tests.utils;

import java.util.ResourceBundle;

/**
 * Helper class for all tests
 */
public class TestsHelper {

    /** */
    private static final ResourceBundle TESTS_SETTINGS = ResourceBundle.getBundle("tests");

    /** */
    private static final int BULK_OPERATION_SIZE = parseTestSettings("bulk.operation.size");

    /** */
    private static final int LOAD_TESTS_THREADS_COUNT = parseTestSettings("load.tests.threads.count");

    /** */
    private static final int LOAD_TESTS_WARMUP_PERIOD = parseTestSettings("load.tests.warmup.period");

    /** */
    private static final int LOAD_TESTS_EXECUTION_TIME = parseTestSettings("load.tests.execution.time");

    /** */
    private static final int LOAD_TESTS_REQUESTS_LATENCY = parseTestSettings("load.tests.requests.latency");

    /** */
    private static final int LOAD_TESTS_EXECUTION_REDO = parseTestSettings("load.tests.execution.redo");

    /** */
    private static final int LOAD_TESTS_DP_COUNT = parseTestSettings("load.tests.db.count");


    /** */
    private static int parseTestSettings(String name) {
        return Integer.parseInt(TESTS_SETTINGS.getString(name));
    }


    /** */
    public static int getLoadTestsThreadsCount() {
        return LOAD_TESTS_THREADS_COUNT;
    }

    /** */
    public static int getLoadTestsWarmupPeriod() {
        return LOAD_TESTS_WARMUP_PERIOD;
    }

    /** */
    public static int getLoadTestsExecutionTime() {
        return LOAD_TESTS_EXECUTION_TIME;
    }

    /** */
    public static int getLoadTestsExecutionRedo() {
        return LOAD_TESTS_EXECUTION_REDO;
    }

    /** */
    public static int getLoadTestsRequestsLatency() {
        return LOAD_TESTS_REQUESTS_LATENCY;
    }

    /** */
    public static int getLoadTestsDBCount() {
        return LOAD_TESTS_DP_COUNT;
    }

    /** */
    public static int getBulkOperationSize() {
        return BULK_OPERATION_SIZE;
    }


}
