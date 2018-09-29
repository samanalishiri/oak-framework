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
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@XStreamAlias("authorityModel")
@Component
public class AuthorityModel extends BaseModel<Long> {

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AuthorityModel getParent() {
        return parent;
    }

    public void setParent(AuthorityModel parent) {
        this.parent = parent;
    }

    public List<AuthorityModel> getChildren() {
        return children;
    }

    public void setChildren(List<AuthorityModel> children) {
        this.children = children;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
