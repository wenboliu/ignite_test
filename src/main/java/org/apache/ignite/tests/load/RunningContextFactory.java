package org.apache.ignite.tests.load;

/**
 * Created by twer on 11/11/2016.
 */
public interface RunningContextFactory {
    WriteOrderAndItemRunningContextImpl createRunningContext(int i);
}
