package pri.wenbo;

import com.google.common.base.Splitter;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.tests.load.RuntimeManager;
import org.apache.ignite.tests.load.RuntimeManagerFactory;

import java.net.URISyntaxException;

/**
 * Created by twer on 09/11/2016.
 */
public class Main {
    public static final void main(String[] args) throws URISyntaxException {
        String hosts = args[0];
        String cassandraHosts = args[1];
        String cacheName = "order";
        RuntimeManagerFactory runtimeManagerFactory = new RuntimeManagerFactory(Splitter.on(",").splitToList(hosts),Splitter.on(",").splitToList(cassandraHosts), cacheName);


        RuntimeManager runtimeManager = runtimeManagerFactory.createRuntimeManager();
        runtimeManager.start();
        IgniteCache<Long, Long> igniteCache1 = runtimeManager.getCache(cacheName);

        igniteCache1.put(1L, 1L);
        igniteCache1.put(2L, 2L);
        igniteCache1.put(3L, 3L);
        igniteCache1.put(4L, 4L);

//        IgniteCache<PersonId, Person> igniteCache2 = ignite.getOrCreateCache(runtimeManager.getCacheConfiguration("person"));
//
//        igniteCache2.put(new PersonId("com1", "dep1", 1L), new Person("firstName1", "lastName1", 10, true, 180, 100.00f, new Date(), Lists.newArrayList("111111111")));
//        igniteCache2.put(new PersonId("com1", "dep2", 2L), new Person("firstName2", "lastName2", 11, true, 180, 100.00f, new Date(), Lists.newArrayList("111111112")));
//        igniteCache2.put(new PersonId("com2", "dep1", 1L), new Person("firstName3", "lastName3", 12, true, 180, 100.00f, new Date(), Lists.newArrayList("111111113")));
//
//        IgniteCache<CompanyId, Company> igniteCache3 = ignite.getOrCreateCache(runtimeManager.getCacheConfiguration("company"));
//
//        igniteCache3.put(new CompanyId("com1"), new Company("company1"));
//        igniteCache3.put(new CompanyId("com2"), new Company("company2"));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        SqlQuery personSql = new SqlQuery(Person.class, "departmentCode = ?");
//
//        try (QueryCursor<Entry<PersonId, Person>> cursor = igniteCache2.query(personSql.setArgs("dep1"))) {
//            for (Entry<PersonId, Person> e : cursor)
//                System.out.println(e.getValue().toString());
//        }
//
//        SqlQuery companySql = new SqlQuery(Company.class, "name = ?");
//
//        try (QueryCursor<Entry<CompanyId, Company>> cursor = igniteCache3.query(companySql.setArgs("company2"))) {
//            for (Entry<CompanyId, Company> e : cursor)
//                System.out.println(e.getValue().toString());
//        }
//
//        SqlQuery personJoinSql = new SqlQuery(Person.class, "from \"person\".Person , \"company\".Company where \"person\".Person.companyCode = \"company\".Company.companyCode and \"company\".Company.name = ?");
//
//        personJoinSql.setPageSize(1);
//
//        try (QueryCursor<Entry<PersonId, Person>> cursor = igniteCache2.query(personJoinSql.setArgs("company2"))) {
//            for (Entry<PersonId, Person> e : cursor) {
//                System.out.println(e.getKey().getCompanyCode());
//                System.out.println(e.getValue().toString());
//            }
//        }

        System.exit(0);
    }

    private static IgniteCache getCache(String cacheName, RuntimeManager runtimeManager, Ignite ignite) {
        return ignite.getOrCreateCache(runtimeManager.getCacheConfiguration(cacheName));
    }

}
