package com.saman.oak.portal.domain.acl;
/**
 * The AclClassEntity domain class contains entries for the names of each application domain class that has associated permissions.
 */

import com.saman.oak.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@javax.persistence.Entity(name = AclClassEntity.ENTITY_NAME)
@Table(name = AclClassEntity.TABLE_NAME, schema = AclClassEntity.SCHEMA)
public class AclClassEntity extends BaseEntity<Long> implements AclClassConstant {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "CLASS_NAME", unique = true, nullable = false)
    private String className;

    @OneToMany(mappedBy = "aclClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AclObjectIdentityEntity> aclClasses;

    @Override
    public Long getId() {
        return id;
    }
}
