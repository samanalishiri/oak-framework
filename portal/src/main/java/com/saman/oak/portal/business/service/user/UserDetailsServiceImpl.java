package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.converter.Converter;
import com.saman.oak.portal.business.exception.UserNotFoundException;
import com.saman.oak.portal.business.repository.user.UserRepositoty;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoty repositoty;

    @Autowired
    private Converter<UserEntity, UserModel> converter;

    @Override
    @Transactional(readOnly = true)
    public UserModel loadUserByUsername(String s) throws UsernameNotFoundException {
        return converter.convert(repositoty.findByUsername(s).orElseThrow(UserNotFoundException::new));
    }

}
