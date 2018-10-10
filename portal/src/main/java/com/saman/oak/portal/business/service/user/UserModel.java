package com.saman.oak.portal.business.service.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.ByteArraySerializer;
import com.saman.oak.core.model.BaseModel;
import com.saman.oak.portal.business.service.authority.AuthorityModel;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@XStreamAlias("userModel")
@Component
public class UserModel extends BaseModel<Long> implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private String email;

    private boolean enabled = true;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    @JsonSerialize(using = ByteArraySerializer.class)
    @XStreamConverter(value = EncodedByteArrayConverter.class)
    private byte[] image;

    private List<AuthorityModel> authorities;

    @Override
    public Long getId() {
        return id;
    }

    public UserModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserModel setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public UserModel setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public UserModel setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public UserModel setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public UserModel setImage(byte[] image) {
        this.image = image;
        return this;
    }

    @Override
    public List<AuthorityModel> getAuthorities() {
        return authorities;
    }

    public UserModel setAuthorities(List<AuthorityModel> authorities) {
        this.authorities = authorities;
        return this;
    }
}
