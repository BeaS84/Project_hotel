package com.hotel.pethotel.config;
import com.hotel.pethotel.model.ClientModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    MvcRequestMatcher.Builder matcher(HandlerMappingIntrospector handlerMappingIntrospector) {
        return new MvcRequestMatcher.Builder(handlerMappingIntrospector);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/h2-console/**"));

    }
//    public static void configure( AuthenticationManagerBuilder auth, ClientModel clientModel)
//            throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(clientModel.getEmail())
//                .password(clientModel.getPassword())
//                .roles("USER");
////                .withUser("Aayush")
////                .password("Saini")
////                .roles("student_role");
//    }

    @Bean
    public SecurityFilterChain filterSecurity(HttpSecurity http, MvcRequestMatcher.Builder mvcMatcher) throws Exception {
        http
               // .exceptionHandling().accessDeniedPage("/accessDenied")
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/","/register").permitAll()
                                .requestMatchers("/adminpanel/**").hasRole("ADMIN")
                                .requestMatchers("/clientpanel/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
               // .exceptionHandling().accessDeniedPage("/accessDenied.html").and()
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                                .logoutSuccessUrl("/")//po wylogowaniu wracamy na home,
                        // nie na stronę logowania
                )//.exceptionHandling().accessDeniedPage("/403")
             ;
        return http.build();
    }
}