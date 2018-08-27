package com.saman.oak.portal.domain.authority;
/**
 * this entity store roles
 */

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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
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
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@Entity(name = AuthorityEntity.ENTITY_NAME)
@Table(name = AuthorityEntity.TABLE_NAME, schema = AuthorityEntity.SCHEMA)
@XStreamAlias(AuthorityEntity.ENTITY_NAME)
@Component
public class AuthorityEntity extends BaseEntity<Long> implements AuthorityConstant, GrantedAuthority {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
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
    @JoinColumn(name = "PARENT", referencedColumnName = AuthorityEntity.TABLE_NAME + AuthorityEntity.ID_SUFFIX)
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
    @JoinTable(name = AuthorityEntity.TABLE_NAME + "_" + PermissionEntity.TABLE_NAME, schema = SecurityConstant.SCHEMA,
            joinColumns = @JoinColumn(name = AuthorityEntity.TABLE_NAME + AuthorityEntity.ID_SUFFIX, referencedColumnName = AuthorityEntity.TABLE_NAME + AuthorityEntity.ID_SUFFIX),
            inverseJoinColumns = @JoinColumn(name = PermissionEntity.TABLE_NAME + PermissionEntity.ID_SUFFIX, referencedColumnName = PermissionEntity.TABLE_NAME + PermissionEntity.ID_SUFFIX))
    private List<PermissionEntity> permissions;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public boolean hasPermission(Long permission) {
        return permissions.stream()
                .filter(item -> Objects.equals(item.getId().longValue(), permission.longValue()))
                .findFirst().isPresent();
    }

}
