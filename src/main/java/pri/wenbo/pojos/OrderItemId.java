package pri.wenbo.pojos;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

/**
 * Created by Mofy on 2016/11/8.
 */
public class OrderItemId implements Serializable{

    @QuerySqlField
    private String dpId;

    @QuerySqlField
    @AffinityKeyMapped
    private String orderId;

    @QuerySqlField
    private String oid;

    public String getDpId() {
        return dpId;
    }

    public void setDpId(String dpId) {
        this.dpId = dpId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getId()
    {
        return orderId + "_" + oid;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return getId().equals(((OrderItemId) obj).getId());
    }

}
