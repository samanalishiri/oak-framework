package com.saman.oak.portal.business.service.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.IdentifiableSerializer;
import com.saman.oak.core.converter.IdentifiableToIdConverter;
import com.saman.oak.core.model.BaseModel;
import com.saman.oak.portal.business.service.user.UserModel;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@XStreamAlias("authorityModel")
@Component
public class AuthorityModel extends BaseModel<Long> implements GrantedAuthority {

    private Long id;

    private String authority;

    private String description;

    private boolean enabled = true;

    @JsonSerialize(using = IdentifiableSerializer.class)
    @JsonProperty("parentId")
    @XStreamConverter(value = IdentifiableToIdConverter.class)
    @XStreamAlias("parentId")
    private AuthorityModel parent;

    @JsonIgnore
    @XStreamOmitField
    private List<AuthorityModel> children;

    @JsonIgnore
    @XStreamOmitField
    private List<UserModel> users;

    @Override
    public Long getId() {
        return id;
    }

    public AuthorityModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public AuthorityModel setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AuthorityModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AuthorityModel setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AuthorityModel getParent() {
        return parent;
    }

    public AuthorityModel setParent(AuthorityModel parent) {
        this.parent = parent;
        return this;
    }

    public List<AuthorityModel> getChildren() {
        return children;
    }

    public AuthorityModel setChildren(List<AuthorityModel> children) {
        this.children = children;
        return this;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public AuthorityModel setUsers(List<UserModel> users) {
        this.users = users;
        return this;
    }
}
