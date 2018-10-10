package com.saman.oak.portal.domain.permission;

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
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Entity(name = PermissionEntity.ENTITY_NAME)
@Table(name = PermissionEntity.TABLE_NAME, schema = PermissionEntity.SCHEMA)
@XStreamAlias(PermissionEntity.ENTITY_NAME)
public class PermissionEntity extends BaseEntity<Long> implements PermissionConstant {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
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
    @JoinColumn(name = "PARENT", referencedColumnName = ID_COLUMN)
    private PermissionEntity parent;

    @JsonIgnore
    @XStreamOmitField
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermissionEntity> children;

    @JsonIgnore
    @XStreamOmitField
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AuthorityEntity> authorities;

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public PermissionEntity getParent() {
        return parent;
    }

    public List<PermissionEntity> getChildren() {
        return children;
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public PermissionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PermissionEntity setName(String name) {
        this.name = name;
        return this;
    }

    public PermissionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public PermissionEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public PermissionEntity setParent(PermissionEntity parent) {
        this.parent = parent;
        return this;
    }

    public PermissionEntity setChildren(List<PermissionEntity> children) {
        this.children = children;
        return this;
    }

    public PermissionEntity setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
        return this;
    }

}
