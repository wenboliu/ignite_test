package pri.wenbo.pojos;

import org.apache.ignite.tests.load.Generator;

/**
 * Created by Mofy on 2016/11/7.
 */
public class OrderIdGenerator implements Generator {

    @Override
    public Object generate(long i) {
        CmsOrderId cmsOrderId = new CmsOrderId();
        cmsOrderId.setDpId("dpId" + (i % 10));
        cmsOrderId.setOrderId("orderId" + i);
        return cmsOrderId;
    }
}
