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
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Entity(name = UserEntity.ENTITY_NAME)
@Table(name = UserEntity.TABLE_NAME, schema = UserEntity.SCHEMA)
@XStreamAlias(UserEntity.ENTITY_NAME)
@Component
public class UserEntity extends BaseEntity<Long> implements UserConstant, UserDetails {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
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

    @Lob
    @Column(name = "IMAGE")
    @JsonSerialize(using = ByteArraySerializer.class)
    @XStreamConverter(value = EncodedByteArrayConverter.class)
    private byte[] image;

    @Embedded
    private PersonEntity person;

    @JsonIgnore
    @XStreamOmitField
    @ManyToMany
    @JoinTable(name = TABLE_NAME + "_" + AuthorityEntity.TABLE_NAME, schema = SecurityConstant.SCHEMA,
            joinColumns = @JoinColumn(name = TABLE_NAME + UserEntity.ID_SUFFIX, referencedColumnName = ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = AuthorityEntity.TABLE_NAME + AuthorityEntity.ID_SUFFIX, referencedColumnName = ID_COLUMN))
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

    public String getEmail() {
        return email;
    }

    public byte[] getImage() {
        return image;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public List<DeviceEntity> getDevices() {
        return devices;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserEntity setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public UserEntity setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public UserEntity setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public UserEntity setImage(byte[] image) {
        this.image = image;
        return this;
    }

    public UserEntity setPerson(PersonEntity person) {
        this.person = person;
        return this;
    }

    public UserEntity setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
        return this;
    }

    public UserEntity setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
        return this;
    }

    public UserEntity setDevices(List<DeviceEntity> devices) {
        this.devices = devices;
        return this;
    }

}
