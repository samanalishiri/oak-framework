package ir.navaco.core.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Access(AccessType.PROPERTY)
public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID>, Serializable {

    private Date insertDate;

    private Long insertUserId;

    private Long insertOrganizationId;

    private Date modifyDate;

    private Long modifyUserId;

    private Long modifyOrganizationId;

    private Integer version;

    private ID id;

    @Transient
    @Override
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Column(name = "INSERT_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Column(name = "INSERT_USER_ID", updatable = false)
    public Long getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(Long insertUserId) {
        this.insertUserId = insertUserId;
    }

    @Column(name = "INSERT_ORGANIZATION_ID", updatable = false)
    public Long getInsertOrganizationId() {
        return insertOrganizationId;
    }

    public void setInsertOrganizationId(Long insertOrganizationId) {
        this.insertOrganizationId = insertOrganizationId;
    }

    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "MODIFY_USER_ID")
    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Column(name = "MODIFY_ORGANIZATION_ID")
    public Long getModifyOrganizationId() {
        return modifyOrganizationId;
    }

    public void setModifyOrganizationId(Long modifyOrganizationId) {
        this.modifyOrganizationId = modifyOrganizationId;
    }

    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
