package per.piers.onlineJudge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM users WHERE email=?")                                                                        // password
                .authoritiesByUsernameQuery("SELECT email, role FROM users WHERE email=?");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                // user
                .regexMatchers("/user/information").hasAnyAuthority("user","admin")
                // test
                .regexMatchers("/test/question/\\d+").hasAnyAuthority("user","admin")
                // score
                .regexMatchers("/score/.+").hasAnyAuthority("user","admin")
                // testManager
                .regexMatchers("/testManager/.+").hasAnyAuthority("admin")
                // others
                .anyRequest().permitAll().and() // set authorization matcher
                .formLogin().loginPage("/user/login").defaultSuccessUrl("/user/information").and()
                .rememberMe().tokenValiditySeconds(604800).key("OnlineJudge").and()
                .logout().logoutUrl("/user/logout").logoutSuccessUrl("/user/login");
    }

}
