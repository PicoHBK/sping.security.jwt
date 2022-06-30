package spring.bp.biblioteca.configuration;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET).hasRole("ADMIN").anyRequest().authenticated().and().httpBasic();
        
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails fido = User.builder().
                username("fido").
                password(passwordEncoder().encode("fido")).
                roles("ADMIN").build();
    
        UserDetails tito = User.builder().
        username("tito").
        password(passwordEncoder().encode("tito")).
        roles("USER").build();
        return new InMemoryUserDetailsManager(fido, tito);
    }
    
    

   

   

}
    
