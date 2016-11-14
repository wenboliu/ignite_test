package org.apache.ignite.tests.load.ignite;

import com.google.common.base.Splitter;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.tests.load.LoadDataUtils;
import org.apache.ignite.tests.load.RunningContext;
import org.apache.ignite.tests.load.RuntimeManager;
import org.apache.ignite.tests.load.Worker;
import pri.wenbo.pojos.CmsOrder;

import javax.cache.Cache.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by twer on 13/11/2016.
 */
public class QueryWorker extends Worker {

    private Executor executor;

    /** */
    public static final String LOGGER_NAME = "IgniteReadLoadTest";

    /** */
    public QueryWorker(RuntimeManager runtimeManager, RunningContext runningContext) {
        super(runtimeManager, runningContext);
        this.executor = Executors.newCachedThreadPool();
    }

    /** {@inheritDoc} */
    @Override protected String loggerName() {
        return LOGGER_NAME;
    }

    /** {@inheritDoc} */
    @Override protected boolean batchMode() {
        return false;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override protected void process(IgniteCache cache, Object key, Object val) {
        long middle = LoadDataUtils.randomDate();
        long start = middle - 1800 * 1000;
        long end = middle + 1800 * 1000;
        String dpIdOrderId = (String) key;
        SqlQuery personJoinSql = new SqlQuery<AffinityKey<String>, CmsOrder>(CmsOrder.class, "from \"cmsOrder\".CmsOrder , \"orderItem\".OrderItem where \"cmsOrder\".CmsOrder._key = \"orderItem\".OrderItem.dpIdOrderId and \"cmsOrder\".CmsOrder._key like ? and \"cmsOrder\".CmsOrder.created >= ? and \"cmsOrder\".CmsOrder.created <= ? ");
        personJoinSql.setArgs(Splitter.on("-").split(dpIdOrderId).iterator().next()+"-%", start, end);
        personJoinSql.setPageSize(10);
        personJoinSql.setDistributedJoins(true);


        int i = 0;

        try (QueryCursor<Entry<String, CmsOrder>> cursor = cache.query(personJoinSql)) {
            for (Entry<String, CmsOrder> e : cursor) {
                i++;
            }
        }
//        System.out.println("get " + i + " rows result");


    }
}
