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
public class PersonId implements Serializable {
    /** */
    @QuerySqlField
    @AffinityKeyMapped
    private String companyCode;

    /** */
    @QuerySqlField
    private String departmentCode;

    /** */
    @QuerySqlField
    private long personNum;

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public PersonId() {
    }

    /** */
    public PersonId(String companyCode, String departmentCode, long personNum) {
        this.companyCode = companyCode;
        this.departmentCode = departmentCode;
        this.personNum = personNum;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("SimplifiableIfStatement")
    @Override public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PersonId))
            return false;

        PersonId id = (PersonId)obj;

        if ((companyCode != null && !companyCode.equals(id.companyCode)) ||
            (id.companyCode != null && !id.companyCode.equals(companyCode)))
            return false;

        if ((companyCode != null && !companyCode.equals(id.companyCode)) ||
            (id.companyCode != null && !id.companyCode.equals(companyCode)))
            return false;

        return personNum == id.personNum;
    }

    /** {@inheritDoc} */
    @Override public int hashCode() {
        String code = (companyCode == null ? "" : companyCode) +
            (departmentCode == null ? "" : departmentCode) +
                personNum;

        return code.hashCode();
    }

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public void setCompanyCode(String code) {
        companyCode = code;
    }

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public String getCompanyCode() {
        return companyCode;
    }

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public void setDepartmentCode(String code) {
        departmentCode = code;
    }

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public String getDepartmentCode() {
        return departmentCode;
    }

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public void setPersonNumber(long personNum) {
        this.personNum = personNum;
    }

    /** */
    @SuppressWarnings("UnusedDeclaration")
    public long getPersonNumber() {
        return personNum;
    }
}
