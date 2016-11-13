package org.apache.ignite.tests.load;


import org.apache.ignite.tests.utils.TestsHelper;

public class QueryRunningContextFactoryImpl implements RunningContextFactory {
    private final int parallel;
    private final int range;


    public QueryRunningContextFactoryImpl() {
        this.parallel = TestsHelper.getLoadTestsThreadsCount();
        this.range = TestsHelper.getLoadTestsDBCount();
    }


    @Override
    public RunningContext createRunningContext(int i) {
        int start = range * i / parallel;
        int end = range * (i + 1) / parallel;
        return new QueryRunningContextImpl(start, end);
    }
}
