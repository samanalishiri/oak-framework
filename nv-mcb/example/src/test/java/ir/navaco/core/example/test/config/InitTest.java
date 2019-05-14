package ir.navaco.core.example.test.config;

import ir.navaco.core.config.init.ContextBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 *
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ContextBean.class,
        SpringDataJpaConfiguration.class})
@WebAppConfiguration("src/test/webapp")
public class InitTest {

    @Test
    public void init() {
        System.out.println("init test");
    }

}
