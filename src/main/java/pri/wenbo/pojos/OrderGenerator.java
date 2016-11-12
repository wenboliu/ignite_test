package pri.wenbo.pojos;

import org.apache.ignite.tests.load.Generator;

import java.util.Random;


public class OrderGenerator implements Generator{
    @Override
    public Object generate(long i) {
        Random random = new Random();
        CmsOrder cmsOrder = new CmsOrder();
        cmsOrder.setCustomerno("customer" + i);
        cmsOrder.setCreated(System.currentTimeMillis());
        cmsOrder.setStatus(random.nextInt(2) + 1);
        return cmsOrder;
    }
}
