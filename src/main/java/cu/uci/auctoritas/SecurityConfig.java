package cu.uci.auctoritas;



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
                 .antMatchers("/api/datasource/getPersonalAuthorityDatasources").permitAll()
                 .antMatchers("/api/datasource/getCorporateAuthorityDatasources").permitAll()
                 .antMatchers("/usermanagement.html").hasAuthority("admin")
                 .anyRequest().authenticated()
                 .and()
                 .formLogin()
                 .loginPage("/login.html").defaultSuccessUrl("/personalauthor.html")
                 .permitAll()
                 .and()
                 .httpBasic()
                 .realmName("Auctoritas").and()
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


}