package com.saman.oak.portal.business.service.user;

import com.saman.oak.core.converter.Converter;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import com.saman.oak.portal.business.dao.user.UserDetailsDao;
import com.saman.oak.portal.business.exception.UserNotFoundException;
import com.saman.oak.portal.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService, UserService<Long, UserModel, UserResource, UserEntity, UserDetailsDao> {

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    private UserDetailsDao dao;

    private Converter<UserEntity, UserModel> converter;

    @Override
    public void setEntityType(Class<? extends BaseEntity> c) {

    }

    @Override
    public void setModelType(Class<? extends BaseModel> c) {

    }

    @Override
    public UserDetailsDao getDao() {
        return dao;
    }

    @Autowired
    @Override
    public void setDao(UserDetailsDao userDetailsDao) {
        this.dao = userDetailsDao;
    }

    @Override
    public Converter<UserEntity, UserModel> getConverter() {
        return converter;
    }

    @Autowired
    @Qualifier(UserConverter.NAME)
    @Override
    public void setConverter(Converter<UserEntity, UserModel> converter) {
        this.converter = converter;
    }

    @Override
    @Transactional(readOnly = true)
    public UserModel loadUserByUsername(String s) throws UsernameNotFoundException {
        return getConverter().convert(dao.findByUsername(s).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public Optional save(UserModel model) {
        String encodedPassword = passwordEncoder.encode(model.getPassword());
        UserEntity entity = converter.convert(model);
        entity.setPassword(encodedPassword);
        UserEntity user = dao.save(entity);

        return Optional.of(userResourceAssembler.toResource(user));
    }

    @Override
    public Optional edit(UserModel model) {
        return Optional.empty();
    }

    @Override
    public Optional find() {
        return Optional.empty();
    }

    @Override
    public Optional find(Long key) {
        return Optional.empty();
    }

    @Override
    public void delete(Long key) {

    }

    @Override
    public void fastDelete(Long key) {

    }

    @Override
    public Optional find(UserModel model) {
        return Optional.empty();
    }

    @Override
    public Optional<UserResource> findUnique(UserModel userModel) {
        return Optional.empty();
    }

}
