package com.saman.oak.portal.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.ByteArraySerializer;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.portal.SecurityConstant;
import com.saman.oak.portal.domain.authority.AuthorityEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@javax.persistence.Entity(name = UserEntity.ENTITY_NAME)
@Table(name = UserEntity.TABLE_NAME, schema = UserEntity.SCHEMA)
@XStreamAlias(UserEntity.ENTITY_NAME)
@Component
public class UserEntity extends BaseEntity<Long> implements UserConstant, UserDetails {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWD", nullable = false)
    private String password;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "ENABLED")
    @Type(type = "boolean")
    private boolean enabled = true;

    @Column(name = "NON_EXPIRED")
    @Type(type = "boolean")
    private boolean accountNonExpired = true;

    @Column(name = "NON_LOCKED")
    @Type(type = "boolean")
    private boolean accountNonLocked = true;

    @Column(name = "PASSWORD_NON_EXPIRED")
    @Type(type = "boolean")
    private boolean credentialsNonExpired = true;

    @JsonSerialize(using = ByteArraySerializer.class)
    @XStreamConverter(value = EncodedByteArrayConverter.class)
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @Embedded
    private PersonEntity person;

    @JsonIgnore
    @XStreamOmitField
    @ManyToMany
    @JoinTable(name = TABLE_NAME + "_" + AuthorityEntity.TABLE_NAME, schema = SecurityConstant.SCHEMA,
            joinColumns = @JoinColumn(name = TABLE_NAME + UserEntity.ID_SUFFIX, referencedColumnName = TABLE_NAME + UserEntity.ID_SUFFIX),
            inverseJoinColumns = @JoinColumn(name = AuthorityEntity.TABLE_NAME + AuthorityEntity.ID_SUFFIX, referencedColumnName = AuthorityEntity.TABLE_NAME + AuthorityEntity.ID_SUFFIX))
    private List<AuthorityEntity> authorities;

    @JsonIgnore
    @XStreamOmitField
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;

    @JsonIgnore
    @XStreamOmitField
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeviceEntity> devices;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
