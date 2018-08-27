package com.saman.oak.portal.domain.acl;
/**
 * The AclSidEntity domain class contains entries for the names of grant recipients
 * (a principal or authority - SID is an acronym for “security identity”).
 * These are typically usernames (where principal is true) but can also be a GrantedAuthority (role name, where principal is false).
 * When granting permissions to a role, any user with that role receives that permission.
 */

import com.saman.oak.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@javax.persistence.Entity(name = AclSidEntity.ENTITY_NAME)
@Table(name = AclSidEntity.TABLE_NAME, schema = AclSidEntity.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "ACL_SID_UQ_1", columnNames = {"SID", "PRINCIPAL"})})
public class AclSidEntity extends BaseEntity<Long> implements AclSidConstant {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "SID", nullable = false)
    private String sid;

    @Column(name = "PRINCIPAL", nullable = false)
    @Type(type = "boolean")
    private Boolean principal;

    @OneToMany(mappedBy = "sid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AclEntryEntity> aclSids;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AclObjectIdentityEntity> aclObjectIdentities;

    @Override
    public Long getId() {
        return id;
    }
}
