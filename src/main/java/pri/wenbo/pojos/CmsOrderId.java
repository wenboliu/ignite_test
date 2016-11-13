package pri.wenbo.pojos;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

/**
 * Created by Mofy on 2016/11/7.
 */
public class CmsOrderId implements Serializable{
    @QuerySqlField
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

    @Override
    public int hashCode() {
        return orderId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        CmsOrderId anotherCmsOrderId = (CmsOrderId) obj;
        return orderId.equals(anotherCmsOrderId.orderId);
    }

}
