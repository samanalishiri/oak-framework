package com.saman.oak.portal.domain.permission;
/**
 * this entity store permissions
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.IdentifiableSerializer;
import com.saman.oak.core.converter.IdentifiableToIdConverter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.portal.domain.authority.AuthorityEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@javax.persistence.Entity(name = PermissionEntity.ENTITY_NAME)
@Table(name = PermissionEntity.TABLE_NAME, schema = PermissionEntity.SCHEMA)
@XStreamAlias(PermissionEntity.ENTITY_NAME)
public class PermissionEntity extends BaseEntity<Long> implements PermissionConstant {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ENABLED")
    @Type(type = "boolean")
    private boolean enabled = true;

    @JsonSerialize(using = IdentifiableSerializer.class)
    @JsonProperty("parentId")
    @XStreamConverter(value = IdentifiableToIdConverter.class)
    @XStreamAlias("parentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = TABLE_NAME + PermissionEntity.ID_SUFFIX)
    private PermissionEntity parent;

    @JsonIgnore
    @XStreamOmitField
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermissionEntity> children;

    @JsonIgnore
    @XStreamOmitField
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
    private List<AuthorityEntity> authorities;

    @Override
    public Long getId() {
        return id;
    }
}
