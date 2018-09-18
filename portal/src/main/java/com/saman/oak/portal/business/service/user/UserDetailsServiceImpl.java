package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.validation.ObjectUtils;
import com.saman.oak.portal.business.dao.user.UserDetailsDao;
import com.saman.oak.portal.controller.UserNullException;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
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
        UserEntity user = userDetailsDao.findByUsername(s);
        ObjectUtils.requireNonNull(user, new UserNullException("user.validation.isNull"));
        return user;
    }

    public Optional<UserResource> save(UserEntity entity) {
        String encodedPassword = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
        UserEntity user = userDetailsDao.save(entity);

        return Optional.of(userResourceAssembler.toResource(user));
    }


}
