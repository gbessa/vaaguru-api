package br.com.hoptech.vaaguruapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.hoptech.vaaguruapi.adapters.FacebookSignInAdapter;
import br.com.hoptech.vaaguruapi.security.JWTAuthenticationFilter;
import br.com.hoptech.vaaguruapi.security.JWTAuthorizationFilter;
import br.com.hoptech.vaaguruapi.security.JWTUtil;
import br.com.hoptech.vaaguruapi.services.FacebookConnectionSignup;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;  
    
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
 
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;
 
    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;

    @Autowired
    private Environment env;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };
    
    private static final String[] PUBLIC_MATCHERS_GET = { 
	    "/countries/**",
	    "/schedules/**",
	    "/rowers/**",
	    "/teams/**",
	    "/invitations/**"};

    private static final String[] PUBLIC_MATCHERS_POST = { 
	    "/auth/forgot**",
	    "/auth/facebook**",
	    "/rowers/**"};
    
    private static final String[] PUBLIC_MATCHERS_DELETE = { 
	    };
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

	// H2-console Bypass
	if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
	    http.headers().frameOptions().disable();
	}

	http.cors().and().csrf().disable(); // Não precisa de proteção contra ataque de Sessão, pois o sistema será stateless
	
	http.authorizeRequests()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		.antMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).permitAll()
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated();
	
	http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
	http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
    }
    
    @Bean
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
          .setConnectionSignUp(facebookConnectionSignup);
         
        return new ProviderSignInController(
          connectionFactoryLocator, 
          usersConnectionRepository, 
          new FacebookSignInAdapter());
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
	CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	source.registerCorsConfiguration("/**", configuration);
	return source;
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }
}
