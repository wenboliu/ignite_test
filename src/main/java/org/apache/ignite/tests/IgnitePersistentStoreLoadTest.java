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

package org.apache.ignite.tests;

import com.google.common.base.Splitter;
import org.apache.ignite.tests.load.LoadTestDriver;
import org.apache.ignite.tests.load.RuntimeManager;
import org.apache.ignite.tests.load.RuntimeManagerFactory;
import org.apache.ignite.tests.load.WriteOrderAndItemRunningContextFactoryImpl;
import org.apache.ignite.tests.load.ignite.WriteWorker;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Load tests for Ignite caches which utilizing {@link org.apache.ignite.cache.store.cassandra.CassandraCacheStore}
 * to store cache data into Cassandra tables
 */
public class IgnitePersistentStoreLoadTest extends LoadTestDriver {
    /** */
    private static final Logger LOGGER = Logger.getLogger("IgniteLoadTests");

    /**
     * test starter.
     *
     * @param args Test arguments.
     */
    public static void main(String[] args) {
        try {
            LOGGER.info("Ignite load tests execution started");

            if(args.length < 2) {
                LOGGER.error("please input the hosts");
            }

            List<String> igniteHosts = Splitter.on(",").splitToList(args[0]);
            List<String> cassandraHosts = Splitter.on(",").splitToList(args[1]);
            RuntimeManagerFactory runtimeManagerFactory = new RuntimeManagerFactory(igniteHosts, cassandraHosts, "cmsOrder", "orderItem");

            LoadTestDriver driver = new IgnitePersistentStoreLoadTest();


            driver.runTest("WRITE", WriteWorker.class, runtimeManagerFactory, WriteWorker.LOGGER_NAME, new WriteOrderAndItemRunningContextFactoryImpl());

//            driver.runTest("BULK_WRITE", BulkWriteWorker.class, runtimeManagerFactory, BulkWriteWorker.LOGGER_NAME, new WriteOrderAndItemRunningContextFactoryImpl());

//            driver.runTest("READ", ReadWorker.class, hosts, ReadWorker.LOGGER_NAME, new RunningContextFactoryImpl(cacheNames));

//            driver.runTest("BULK_READ", BulkReadWorker.class, hosts, BulkReadWorker.LOGGER_NAME, new RunningContextFactoryImpl(cacheNames));

            LOGGER.info("Ignite load tests execution completed");
        }
        catch (Throwable e) {
            LOGGER.error("Ignite load tests execution failed", e);
            throw new RuntimeException("Ignite load tests execution failed", e);
        }
        finally {
        }
    }

    /** {@inheritDoc} */
    @Override protected Logger logger() {
        return LOGGER;
    }

    /** {@inheritDoc}
     * @param runtimeManagerFactory*/
    @Override protected RuntimeManager setup(RuntimeManagerFactory runtimeManagerFactory) {
        RuntimeManager runtimeManager = runtimeManagerFactory.createRuntimeManager();
        runtimeManager.start();
        return runtimeManager;
    }

    /** {@inheritDoc} */
    @Override protected void tearDown(Object obj) {
        RuntimeManager runtimeManager = (RuntimeManager)obj;
        if (runtimeManager != null)
            runtimeManager.close();
    }
}
