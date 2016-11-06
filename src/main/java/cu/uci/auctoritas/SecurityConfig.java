package cu.uci.auctoritas;

/**
 * Created by bichos on 31/05/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from users where username= ?")
                .authoritiesByUsernameQuery("select username, role from users where username= ?")
              .passwordEncoder(new StandardPasswordEncoder("d@r@yn3"));
    }


     protected void configure(HttpSecurity http) throws Exception {
         http
                 .csrf().disable()
                 .authorizeRequests()
                 .antMatchers(HttpMethod.POST).authenticated()
                 .antMatchers(HttpMethod.DELETE).authenticated()
                 .antMatchers(HttpMethod.PUT).authenticated()
                 .antMatchers("/api/author/**").permitAll()
                 .antMatchers("/api/vocabulary").permitAll()
                 .antMatchers("/api/term").permitAll()
                 .antMatchers("/usermanagement.html").hasAuthority("admin")
                 .anyRequest().authenticated()
                 .and()
                 .formLogin()
                 .loginPage("/login.html").defaultSuccessUrl("/personalauthor.html")
                 .permitAll()
                 .and()
                 .httpBasic()
                 .realmName("Auctoritas").and()
//                 .requiresChannel()
//                 .antMatchers("/api/user/**").requiresSecure()
//                 .antMatchers("/api/localauthors/**").requiresSecure()
//                 .and()
                 .rememberMe()
                 .tokenValiditySeconds(14400)
                 .key("auctoritasKey")
                 .and()
                 .logout()
                 .logoutUrl("/logout")
                 .logoutSuccessUrl("/login.html")
                 .invalidateHttpSession(true)
                 .and()
                 .exceptionHandling().accessDeniedPage("/403.html");


     }
    @Autowired
        public void configureGlobalCHECK(AuthenticationManagerBuilder auth) throws Exception {

//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName("org.postgresql.Driver");
//            dataSource.setUrl("jdbc:postgresql://localhost/users");
//            dataSource.setUsername("postgres");
//            dataSource.setPassword("1234567890");
//
//            auth
//                    .jdbcAuthentication().dataSource(dataSource)
//                    .usersByUsernameQuery("select * from users where username=?")
//                    .authoritiesByUsernameQuery("select * from user_roles where username=?");
        }

}