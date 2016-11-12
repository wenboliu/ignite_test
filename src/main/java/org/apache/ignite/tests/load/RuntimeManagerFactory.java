package org.apache.ignite.tests.load;

import com.datastax.driver.core.policies.RoundRobinPolicy;
import org.apache.ignite.cache.store.cassandra.datasource.DataSource;

import java.util.List;

/**
 * Created by twer on 10/11/2016.
 */
public class RuntimeManagerFactory {
    private List<String> igniteHosts;
    private List<String> cassandraHosts;
    private String[] cacheNames;

    public RuntimeManagerFactory(List<String> igniteHosts, List<String> cassandraHosts, String... cacheName) {
        this.igniteHosts = igniteHosts;
        this.cassandraHosts = cassandraHosts;
        this.cacheNames = cacheName;
    }

    public RuntimeManager createRuntimeManager() {
        return new RuntimeManager(createDataSource(), igniteHosts, cacheNames);
    }

    private DataSource createDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUser("cassandra");
        dataSource.setPassword("cassandra");
        dataSource.setContactPoints(cassandraHosts.toArray(new String[]{}));
        dataSource.setWriteConsistency("ONE");
        dataSource.setReadConsistency("ONE");
        dataSource.setLoadBalancingPolicy(new RoundRobinPolicy());
        return dataSource;
    }

}
