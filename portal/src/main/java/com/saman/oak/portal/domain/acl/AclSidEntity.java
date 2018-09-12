package com.saman.oak.portal.domain.acl;
/**
 * The AclSidEntity domain class contains entries for the names of grant recipients
 * (a principal or authority - SID is an acronym for “security identity”).
 * These are typically usernames (where principal is true) but can also be a GrantedAuthority (role name, where principal is false).
 * When granting permissions to a role, any user with that role receives that permission.
 */

import com.saman.oak.core.domain.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Entity(name = AclSidEntity.ENTITY_NAME)
@Table(name = AclSidEntity.TABLE_NAME, schema = AclSidEntity.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "ACL_SID_UQ_1", columnNames = {"SID", "PRINCIPAL"})})
public class AclSidEntity extends BaseEntity<Long> implements AclSidConstant {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "SID", nullable = false)
    private String sid;

    @Type(type = "boolean")
    @Column(name = "PRINCIPAL", nullable = false)
    private Boolean principal;

    @OneToMany(mappedBy = "sid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AclEntryEntity> aclSids;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AclObjectIdentityEntity> aclObjectIdentities;

    @Override
    public Long getId() {
        return id;
    }

    public String getSid() {
        return sid;
    }

    public AclSidEntity setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public AclSidEntity setPrincipal(Boolean principal) {
        this.principal = principal;
        return this;
    }

    public List<AclEntryEntity> getAclSids() {
        return aclSids;
    }

    public AclSidEntity setAclSids(List<AclEntryEntity> aclSids) {
        this.aclSids = aclSids;
        return this;
    }

    public List<AclObjectIdentityEntity> getAclObjectIdentities() {
        return aclObjectIdentities;
    }

    public AclSidEntity setAclObjectIdentities(List<AclObjectIdentityEntity> aclObjectIdentities) {
        this.aclObjectIdentities = aclObjectIdentities;
        return this;
    }

    public AclSidEntity setId(Long id) {
        this.id = id;
        return this;
    }

}
