package pri.wenbo.pojos;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

/**
 * Created by Mofy on 2016/11/7.
 */
public class CmsOrderId implements Serializable{
    @QuerySqlField
    private String dpIdOrderId;

    public String getDpIdOrderId() {
        return dpIdOrderId;
    }

    public void setDpIdOrderId(String dpIdOrderId) {
        this.dpIdOrderId = dpIdOrderId;
    }

    @Override
    public int hashCode() {
        return dpIdOrderId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        CmsOrderId anotherCmsOrderId = (CmsOrderId) obj;
        return dpIdOrderId.equals(anotherCmsOrderId.dpIdOrderId);
    }

}
