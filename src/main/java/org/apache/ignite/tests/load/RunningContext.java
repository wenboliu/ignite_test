package org.apache.ignite.tests.load;

import java.util.List;

/**
 * Created by twer on 11/11/2016.
 */
public interface RunningContext {
    long getStartPosition();

    long getEndPosition();

    String[] getCacheNames();

    List<KeyValuePair> generateLoadTestsObject(long i);
}
