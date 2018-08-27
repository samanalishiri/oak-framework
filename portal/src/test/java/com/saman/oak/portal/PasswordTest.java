package com.saman.oak.portal;

import com.saman.oak.portal.config.SpringDataJpaConfiguration;
import com.saman.oak.portal.config.SpringSecurityConfiguration;
import com.saman.oak.portal.config.web.ContextBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ContextBean.class,
        SpringSecurityConfiguration.class,
        SpringDataJpaConfiguration.class})
@WebAppConfiguration("src/test/webapp")
public class PasswordTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void init() {
        Assert.assertNotNull(passwordEncoder);
    }

    @Test
    public void generatePassword() {
        String password = passwordEncoder.encode("password");
        System.out.println("password = " + password);
    }
}
