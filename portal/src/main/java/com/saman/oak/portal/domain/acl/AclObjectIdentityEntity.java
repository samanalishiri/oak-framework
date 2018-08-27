package com.saman.oak.portal.domain.acl;
/**
 * The AclObjectIdentityEntity domain class contains entries representing individual domain class instances (OIDs).
 * It has a field for the instance id (objectId) and domain class (aclClass) that uniquely identify the instance.
 * In addition there are optional nullable fields for the parent OID (parent) and owner (owner).
 * There’s also a flag (entriesInheriting) to indicate whether ACL entries can inherit from a parent ACL.
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@javax.persistence.Entity(name = AclObjectIdentityEntity.ENTITY_NAME)
@Table(name = AclObjectIdentityEntity.TABLE_NAME, schema = AclObjectIdentityEntity.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "ACL_OBJECT_IDENTITY_UQ_1", columnNames = {"ACL_CLASS", "OBJECT_ID"})})
public class AclObjectIdentityEntity extends BaseEntity<Long> implements AclObjectIdentityConstant {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "OBJECT_ID", nullable = false)
    private Long ObjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACL_CLASS", referencedColumnName = AclClassEntity.TABLE_NAME + ID_SUFFIX, nullable = false)
    private AclClassEntity aclClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER", referencedColumnName = AclSidEntity.TABLE_NAME + AclSidEntity.ID_SUFFIX)
    private AclSidEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = AclObjectIdentityEntity.TABLE_NAME + AclObjectIdentityEntity.ID_SUFFIX)
    private AclObjectIdentityEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AclObjectIdentityEntity> children;

    @Column(name = "ENTRIES_INHERITING", nullable = false)
    @Type(type = "boolean")
    private Boolean entriesInheriting;

    @OneToMany(mappedBy = "aclObjectIdentity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AclEntryEntity> aclEntries;

    @Override
    public Long getId() {
        return id;
    }
}
