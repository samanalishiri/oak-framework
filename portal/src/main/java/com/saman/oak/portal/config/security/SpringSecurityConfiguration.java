package com.saman.oak.portal.config.security;

import com.saman.oak.core.properties.EnvironmentHelper;
import com.saman.oak.portal.config.security.bean.AuthenticationFailureHandler;
import com.saman.oak.portal.config.security.bean.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import static com.saman.oak.core.web.Controller.ROOT;
import static com.saman.oak.core.web.Controller.VIEW;
import static com.saman.oak.portal.SecurityConstant.COOKIE;
import static com.saman.oak.portal.SecurityConstant.CSRF_HEADER_NAME;
import static com.saman.oak.portal.SecurityConstant.SESSION_MAX;
import static com.saman.oak.portal.controller.HomeController.HOME_URL;
import static com.saman.oak.portal.controller.security.LoginController.LOGIN_ACTION;
import static com.saman.oak.portal.controller.security.LoginController.LOGIN_VIEW;
import static com.saman.oak.portal.controller.security.LoginController.LOGOUT_ACTION;
import static com.saman.oak.portal.domain.user.UserConstant.PASSWORD;
import static com.saman.oak.portal.domain.user.UserConstant.USERNAME;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Configuration
@EnableWebSecurity
@PropertySource(value = {"resources/app.properties"})
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private EnvironmentHelper env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = new EnvironmentHelper(env);
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/" + env.value("context.url") + "/**").authenticated()
                .antMatchers("/", "/resources/**", "/webjars/**", "/" + env.value("context.url") + "/", "/" + env.value("context.url")).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(env.intValue("session.max", SESSION_MAX))
                .expiredUrl(LOGOUT_ACTION)
                .and().sessionFixation().newSession()
                .and().formLogin()
                .loginPage(LOGIN_VIEW)
                .loginProcessingUrl(LOGIN_ACTION)
                .failureHandler(authenticationFailureHandler)
                .defaultSuccessUrl(HOME_URL + VIEW)
                .usernameParameter(USERNAME).passwordParameter(PASSWORD)
                .permitAll()
                .and().logout()
                .logoutUrl(LOGOUT_ACTION)
                .logoutSuccessUrl(ROOT)
                .clearAuthentication(true)
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
                .deleteCookies(COOKIE)
                .invalidateHttpSession(true)
                .and().httpBasic()
                .and().exceptionHandling()
                .and().csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
        ;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName(CSRF_HEADER_NAME);
        return repository;
    }
}
