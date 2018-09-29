package com.saman.oak.portal.business.service.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.ByteArraySerializer;
import com.saman.oak.core.model.BaseModel;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
import org.springframework.stereotype.Component;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@XStreamAlias("userModel")
@Component
public class UserModel extends BaseModel<Long> {

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

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
