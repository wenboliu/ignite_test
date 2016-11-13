package pri.wenbo.pojos;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

/**
 * Created by Mofy on 2016/11/8.
 */
public class OrderItemId implements Serializable{

    @QuerySqlField
    @AffinityKeyMapped
    private String dpIdOrderId;

    @QuerySqlField
    private String oid;

    public String getDpIdOrderId() {
        return dpIdOrderId;
    }

    public void setDpIdOrderId(String dpIdOrderId) {
        this.dpIdOrderId = dpIdOrderId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getId()
    {
        return dpIdOrderId + "_" + oid;
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
