package org.apache.ignite.tests.load;

import com.google.common.collect.Lists;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.store.cassandra.CassandraCacheStoreFactory;
import org.apache.ignite.cache.store.cassandra.datasource.DataSource;
import org.apache.ignite.cache.store.cassandra.persistence.KeyValuePersistenceSettings;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.core.io.ClassPathResource;
import pri.wenbo.pojos.CmsOrder;
import pri.wenbo.pojos.OrderItem;
import pri.wenbo.pojos.OrderItemId;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
* Created by twer on 12/11/2016.
*/
public class RuntimeManager {
    private List<String> igniteHosts;
    private String[] cacheNames;
    private final Map<String, CacheConfiguration> cacheConfigurationMap = new HashMap<>();
    private Ignite ignite;

    public RuntimeManager(DataSource dataSource, List<String> igniteHosts, String[] cacheNames) {
        this.igniteHosts = igniteHosts;
        this.cacheNames = cacheNames;
        for (String cacheName : cacheNames) {
            addCache(cacheName, dataSource);
        }
    }

    public void start() {
        ignite = Ignition.getOrStart(create());
    }


    private IgniteConfiguration create() {
        TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder = new TcpDiscoveryVmIpFinder();
        tcpDiscoveryVmIpFinder.setAddresses(RuntimeManager.this.igniteHosts);

        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        tcpDiscoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);

        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setClientMode(true);
        igniteConfiguration.setMetricsLogFrequency(0);
        Iterator<CacheConfiguration> iterator = cacheConfigurationMap.values().iterator();
        igniteConfiguration.setCacheConfiguration(Lists.newArrayList(iterator).toArray(new CacheConfiguration[]{}));
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);
        return igniteConfiguration;
    }

    private void addCache(String name, DataSource dataSource) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName(name);
        cacheConfiguration.setCacheStoreFactory(createCassandraCacheStoreFactory(name, dataSource));
//        String pojoName = "pri.wenbo.pojos." + StringUtils.capitalize(name);
//        try {
//            cacheConfiguration.setIndexedTypes(Class.forName(pojoName + "Id"), Class.forName(pojoName));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        if(name.contains("orderItem")) {
            cacheConfiguration.setIndexedTypes(OrderItemId.class, OrderItem.class);
        } else {
            cacheConfiguration.setIndexedTypes(String.class, CmsOrder.class);
        }
        cacheConfigurationMap.put(name, cacheConfiguration);
    }

    private CassandraCacheStoreFactory createCassandraCacheStoreFactory(String cacheName, DataSource dataSource) {
        CassandraCacheStoreFactory storeFactory = new CassandraCacheStoreFactory();
        storeFactory.setPersistenceSettings(new KeyValuePersistenceSettings(new ClassPathResource("pri/wenbo/persistence-settings-"+cacheName+".xml")));
        storeFactory.setDataSource(dataSource);
        return storeFactory;
    }

    public CacheConfiguration getCacheConfiguration(String name) {
        return cacheConfigurationMap.get(name);
    }

    public IgniteCache getCache(String cacheName) {
        return this.ignite.getOrCreateCache(getCacheConfiguration(cacheName));
    }

    public boolean isStarted() {
        return ignite != null;
    }

    public void close() {
        if(ignite != null)
            ignite.close();
    }

    public Map<String, IgniteCache> createIgniteCacheMap() {
        Map<String, IgniteCache> igniteCaches =  new HashMap<>();
        String[] cacheNames = this.cacheNames;
        for (String cacheName : cacheNames) {
            igniteCaches.put(cacheName, getCache(cacheName));
        }
        return igniteCaches;
    }
}
