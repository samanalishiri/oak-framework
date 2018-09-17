package com.saman.oak.portal.domain.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.IdentifiableSerializer;
import com.saman.oak.core.converter.IdentifiableToIdConverter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.portal.SecurityConstant;
import com.saman.oak.portal.domain.permission.PermissionEntity;
import com.saman.oak.portal.domain.user.UserEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

@Entity(name = AuthorityEntity.ENTITY_NAME)
@Table(name = AuthorityEntity.TABLE_NAME, schema = AuthorityEntity.SCHEMA)
@XStreamAlias(AuthorityEntity.ENTITY_NAME)
@Component
public class AuthorityEntity extends BaseEntity<Long> implements AuthorityConstant, GrantedAuthority {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "AUTHORITY", nullable = false)
    private String authority;

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
    private AuthorityEntity parent;

    @JsonIgnore
    @XStreamOmitField
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuthorityEntity> children;

    @JsonIgnore
    @XStreamOmitField
    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    private List<UserEntity> users;

    @JsonIgnore
    @XStreamOmitField
    @ManyToMany
    @JoinTable(name = AuthorityEntity.TABLE_NAME + UNDER_LINE + PermissionEntity.TABLE_NAME, schema = SecurityConstant.SCHEMA,
            joinColumns = @JoinColumn(name = AuthorityEntity.TABLE_NAME + AuthorityEntity.ID_SUFFIX, referencedColumnName = ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = PermissionEntity.TABLE_NAME + PermissionEntity.ID_SUFFIX, referencedColumnName = ID_COLUMN))
    private List<PermissionEntity> permissions;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AuthorityEntity getParent() {
        return parent;
    }

    public List<AuthorityEntity> getChildren() {
        return children;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public List<PermissionEntity> getPermissions() {
        return permissions;
    }

    public AuthorityEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public AuthorityEntity setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public AuthorityEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public AuthorityEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AuthorityEntity setParent(AuthorityEntity parent) {
        this.parent = parent;
        return this;
    }

    public AuthorityEntity setChildren(List<AuthorityEntity> children) {
        this.children = children;
        return this;
    }

    public AuthorityEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }

    public AuthorityEntity setPermissions(List<PermissionEntity> permissions) {
        this.permissions = permissions;
        return this;
    }

    public boolean hasPermission(Long permission) {
        return permissions.stream().anyMatch(permission::equals);
    }

}
