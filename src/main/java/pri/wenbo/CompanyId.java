/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pri.wenbo;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

/**
 * Simple POJO which could be stored as a key in Ignite cache
 */
public class CompanyId implements Serializable {
    /** */
    @QuerySqlField
    @AffinityKeyMapped
    private String companyCode;

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public CompanyId() {
    }

    /** */
    public CompanyId(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CompanyId))
            return false;

        CompanyId id = (CompanyId) obj;

        return this.companyCode != null && this.companyCode.equals(id.companyCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        String code = (this.companyCode == null ? "" : this.companyCode);

        return code.hashCode();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
