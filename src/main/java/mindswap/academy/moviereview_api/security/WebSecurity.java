package mindswap.academy.moviereview_api.security;

import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import mindswap.academy.moviereview_api.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static mindswap.academy.moviereview_api.security.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final IUserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public WebSecurity(IUserRepository userRepository,
                       UserService userService,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.encoder = encoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                //Login page
                .antMatchers("/login").permitAll()
                // Sign up Page
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                //Movie page
                .antMatchers(HttpMethod.POST, "/api/v1/movie/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/movie/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/movie/**").hasRole("ADMIN")
                //Writer page
                .antMatchers(HttpMethod.POST, "/api/v1/writer/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/writer/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/writer/**").hasRole("ADMIN")
                //Actor page
                .antMatchers(HttpMethod.POST, "/api/v1/actor/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/actor/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/actor/**").hasRole("ADMIN")
                //Director page
                .antMatchers(HttpMethod.POST, "/api/v1/director/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/director/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/director/**").hasRole("ADMIN")
                //Genre page
                .antMatchers(HttpMethod.POST, "/api/v1/genre/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/genre/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/genre/**").hasRole("ADMIN")
                //Roles page
                .antMatchers(HttpMethod.POST, "/api/v1/users/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/users/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/users/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/users/roles/**").hasRole("ADMIN")
                //Rating page
                .antMatchers(HttpMethod.PUT, "/api/v1/rating/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/rating/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/rating/**").hasAnyRole("ADMIN")
                //Review page
                .antMatchers(HttpMethod.DELETE, "/api/v1/review/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/api/v1/review/**").hasAnyRole("ADMIN", "USER")
                //Users page
                .antMatchers(HttpMethod.GET, "/api/v1/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyRole("ADMIN", "USER")
                // Generic get call
                .antMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "USER")
                //Swagger page
                .antMatchers("/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
                        "/configuration/ui", "/configuration/security", "/swagger-ui.html",
                        "/webjars/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), this.userRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }
}