package com.saman.oak.portal.domain.acl;
/**
 * the AclEntryEntity domain class contains entries representing grants (or denials) of a permission on an object instance to a recipient.
 * The aclObjectIdentity field references the domain class instance (since an instance can have many granted permissions).
 * The sid field references the recipient.
 * The granting field determines whether the entry grants the permission (true) or denies it (false).
 * The aceOrder field specifies the position of the entry, which is important because the entries are evaluated in order
 * and the first matching entry determines whether access is allowed. auditSuccess and auditFailure determine
 * whether to log success and/or failure events (these both default to false).
 * The mask field holds the permission. This can be a source of confusion because the name (and the Spring Security documentation)
 * indicates that it’s a bit mask. A value of 1 indicates permission A, a value of 2 indicates permission B, a value of 4 indicates
 * permission C, a value of 8 indicates permission D, etc.
 * So you would think that a value of 5 would indicate a grant of both permission A and C.
 * Unfortunately this is not the case. There is a CumulativePermission class that supports this,
 * but the standard classes don’t support it (AclImpl.isGranted() checks for == rather than using | (bitwise or) so a combined entry would never match).
 * So rather than grouping all permissions for one recipient on one instances into a bit mask, you must create individual records for each.
 */

import com.saman.oak.core.domain.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Entity(name = AclEntryEntity.ENTITY_NAME)
@Table(name = AclEntryEntity.TABLE_NAME, schema = AclEntryEntity.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "ACL_ENTRY_UQ_1", columnNames = {"ACL_OBJECT_IDENTITY", "ACE_ORDER"})})
public class AclEntryEntity extends BaseEntity<Long> implements AclEntryConstant {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACL_OBJECT_IDENTITY", referencedColumnName = ID_COLUMN, nullable = false)
    private AclObjectIdentityEntity aclObjectIdentity;

    @Column(name = "ACE_ORDER", nullable = false)
    private Integer aceOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SID", referencedColumnName = ID_COLUMN, nullable = false)
    private AclSidEntity sid;

    @Column(name = "MASK", nullable = false)
    private Integer mask;

    @Column(name = "GRANTING", nullable = false)
    @Type(type = "boolean")
    private Boolean granting;

    @Column(name = "AUDIT_SUCCESS", nullable = false)
    @Type(type = "boolean")
    private Boolean auditSuccess;

    @Column(name = "AUDIT_FAILURE", nullable = false)
    @Type(type = "boolean")
    private Boolean auditFailure;

    @Override
    public Long getId() {
        return id;
    }

    public AclObjectIdentityEntity getAclObjectIdentity() {
        return aclObjectIdentity;
    }

    public AclEntryEntity setAclObjectIdentity(AclObjectIdentityEntity aclObjectIdentity) {
        this.aclObjectIdentity = aclObjectIdentity;
        return this;
    }

    public Integer getAceOrder() {
        return aceOrder;
    }

    public AclEntryEntity setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
        return this;
    }

    public AclSidEntity getSid() {
        return sid;
    }

    public AclEntryEntity setSid(AclSidEntity sid) {
        this.sid = sid;
        return this;
    }

    public Integer getMask() {
        return mask;
    }

    public AclEntryEntity setMask(Integer mask) {
        this.mask = mask;
        return this;
    }

    public Boolean getGranting() {
        return granting;
    }

    public AclEntryEntity setGranting(Boolean granting) {
        this.granting = granting;
        return this;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public AclEntryEntity setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
        return this;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public AclEntryEntity setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
        return this;
    }

    public AclEntryEntity setId(Long id) {
        this.id = id;
        return this;
    }

}
