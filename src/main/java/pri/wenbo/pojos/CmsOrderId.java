package pri.wenbo.pojos;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

/**
 * Created by Mofy on 2016/11/7.
 */
public class CmsOrderId implements Serializable{

    @QuerySqlField
    @AffinityKeyMapped
    private String dpId;

    @QuerySqlField
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDpId() {
        return dpId;
    }

    public void setDpId(String dpId) {
        this.dpId = dpId;
    }

    public String getId()
    {
        return dpId + "_" + orderId;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        CmsOrderId anotherCmsOrderId = (CmsOrderId) obj;
        return getId().equals(anotherCmsOrderId.getId());
    }

}
