package com.saman.oak.portal.business.service.user;

import com.saman.oak.portal.business.dao.user.UserDetailsDao;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Administrator on 9/29/2017.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @Override
    public UserEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDetailsDao.findByUsername(s);
    }

    public Optional<UserResource> save(UserEntity entity) {
        String encodedPassword = passwordEncoder.encode(entity.password());
        entity.password(encodedPassword);
        UserEntity user = userDetailsDao.save(entity);

        return Optional.of(userResourceAssembler.toResource(user));
    }


}
