package com.saman.oak.portal.business.service.authority;

import com.saman.oak.core.converter.Converter;
import com.saman.oak.portal.domain.authority.AuthorityEntity;
import org.springframework.stereotype.Component;

@Component(AuthorityConverter.NAME)
public class AuthorityConverter extends Converter<AuthorityEntity, AuthorityModel> {
    public static final String NAME = "authorityConverter";


    @Override
    public void internal(AuthorityEntity input, AuthorityModel output, int deep, String... relations) {
        output.setId(input.getId()).setAuthority(input.getAuthority());
    }

    @Override
    public void internal(AuthorityModel input, AuthorityEntity output, int deep, String... relations) {
        output.setId(input.getId()).setAuthority(input.getAuthority());
    }

    @Override
    public AuthorityEntity newEntity() {
        return new AuthorityEntity();
    }

    @Override
    public AuthorityModel newModel() {
        return new AuthorityModel();
    }
}
