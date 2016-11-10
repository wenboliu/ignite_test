package pri.wenbo;

import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.google.common.collect.Lists;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.cache.store.cassandra.CassandraCacheStoreFactory;
import org.apache.ignite.cache.store.cassandra.datasource.DataSource;
import org.apache.ignite.cache.store.cassandra.persistence.KeyValuePersistenceSettings;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.core.io.ClassPathResource;

import javax.cache.Cache.Entry;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Created by twer on 09/11/2016.
 */
public class Main {
    public static final void main(String[] args) throws URISyntaxException {
//        String host = "172.19.0.125";
        String host = args[0];

        DataSource dataSource = getDataSource(host);

        CacheConfiguration cache1Config = createCacheConfiguration("persistence-settings-1.xml", "cache1", dataSource);
        CacheConfiguration cache2Config = createCacheConfiguration("persistence-settings-2.xml", "person", dataSource);
        cache2Config.setIndexedTypes(PersonId.class, Person.class);
        CacheConfiguration cache3Config = createCacheConfiguration("persistence-settings-3.xml", "company", dataSource);
        cache3Config.setIndexedTypes(CompanyId.class, Company.class);

        IgniteConfiguration igniteConfiguration = createIgniteConfiguration(host, cache1Config, cache2Config, cache3Config);

        Ignite ignite = Ignition.getOrStart(igniteConfiguration);
        IgniteCache<Long, Long> igniteCache1 = ignite.getOrCreateCache(cache1Config);

        igniteCache1.put(1L, 1L);
        igniteCache1.put(2L, 2L);
        igniteCache1.put(3L, 3L);
        igniteCache1.put(4L, 4L);

        IgniteCache<PersonId, Person> igniteCache2 = ignite.getOrCreateCache(cache2Config);

        igniteCache2.put(new PersonId("com1", "dep1", 1L), new Person("firstName1", "lastName1", 10, true, 180, 100.00f, new Date(), Lists.newArrayList("111111111")));
        igniteCache2.put(new PersonId("com1", "dep2", 2L), new Person("firstName2", "lastName2", 11, true, 180, 100.00f, new Date(), Lists.newArrayList("111111112")));
        igniteCache2.put(new PersonId("com2", "dep1", 1L), new Person("firstName3", "lastName3", 12, true, 180, 100.00f, new Date(), Lists.newArrayList("111111113")));

        IgniteCache<CompanyId, Company> igniteCache3 = ignite.getOrCreateCache(cache3Config);

        igniteCache3.put(new CompanyId("com1"), new Company("company1"));
        igniteCache3.put(new CompanyId("com2"), new Company("company2"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SqlQuery personSql = new SqlQuery(Person.class, "departmentCode = ?");

        try (QueryCursor<Entry<PersonId, Person>> cursor = igniteCache2.query(personSql.setArgs("dep1"))) {
            for (Entry<PersonId, Person> e : cursor)
                System.out.println(e.getValue().toString());
        }

        SqlQuery companySql = new SqlQuery(Company.class, "name = ?");

        try (QueryCursor<Entry<CompanyId, Company>> cursor = igniteCache3.query(companySql.setArgs("company2"))) {
            for (Entry<CompanyId, Company> e : cursor)
                System.out.println(e.getValue().toString());
        }

        SqlQuery personJoinSql = new SqlQuery(Person.class, "from \"person\".Person , \"company\".Company where \"person\".Person.companyCode = \"company\".Company.companyCode and \"company\".Company.name = ?");

        personJoinSql.setPageSize(1);

        try (QueryCursor<Entry<PersonId, Person>> cursor = igniteCache2.query(personJoinSql.setArgs("company2"))) {
            for (Entry<PersonId, Person> e : cursor) {
                System.out.println(e.getKey().getCompanyCode());
                System.out.println(e.getValue().toString());
            }
        }

        System.exit(0);
    }

    private static IgniteConfiguration createIgniteConfiguration(String host, CacheConfiguration... cacheConfig) {
        TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder = new TcpDiscoveryVmIpFinder();
        tcpDiscoveryVmIpFinder.setAddresses(Lists.newArrayList(host));

        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        tcpDiscoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);

        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setClientMode(true);
        igniteConfiguration.setMetricsLogFrequency(0);
        igniteConfiguration.setCacheConfiguration(cacheConfig);
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);
        return igniteConfiguration;
    }

    private static CacheConfiguration createCacheConfiguration(String persistenceFileName, String cacheName, DataSource dataSource) throws URISyntaxException {
        KeyValuePersistenceSettings cache1_settings = new KeyValuePersistenceSettings(new ClassPathResource("pri/wenbo/" + persistenceFileName));

        CassandraCacheStoreFactory cassandraCacheStoreFactory = new CassandraCacheStoreFactory();
        cassandraCacheStoreFactory.setDataSource(dataSource);
        cassandraCacheStoreFactory.setPersistenceSettings(cache1_settings);

        CacheConfiguration cache1Config = new CacheConfiguration();
        cache1Config.setName(cacheName);
        cache1Config.setWriteThrough(true);
        cache1Config.setReadThrough(true);
        cache1Config.setWriteBehindEnabled(true);
        cache1Config.setCacheStoreFactory(cassandraCacheStoreFactory);
        return cache1Config;
    }

    private static DataSource getDataSource(String host) {
        DataSource dataSource = new DataSource();
        dataSource.setLoadBalancingPolicy(new RoundRobinPolicy());
        dataSource.setContactPoints(host);
        dataSource.setUser("cassandra");
        dataSource.setPassword("cassandra");
        dataSource.setWriteConsistency("ONE");
        dataSource.setReadConsistency("ONE");
        return dataSource;
    }
}
