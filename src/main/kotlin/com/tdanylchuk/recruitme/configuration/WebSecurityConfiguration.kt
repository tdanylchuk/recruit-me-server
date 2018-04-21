package com.tdanylchuk.recruitme.configuration

import com.tdanylchuk.recruitme.service.RoleConstants.ADMIN_ROLE
import com.tdanylchuk.recruitme.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
class WebSecurityConfiguration(
        private val userService: UserService) : WebSecurityConfigurerAdapter() {
    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun configure(http: HttpSecurity?) {
        http!!
                .httpBasic()
                .and()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/admin/**").hasAuthority(ADMIN_ROLE)
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors()
                .and()
                .logout()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("POST", "PUT", "OPTIONS", "DELETE", "GET")
        configuration.allowCredentials = true
        configuration.exposedHeaders = listOf("Content-Disposition", "Content-Length")
        configuration.allowedHeaders = listOf("Origin",
                "Accept",
                "X-Requested-With",
                "Authorization",
                "Content-Type",
                "X-XSRF-TOKEN",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers")
        configuration.maxAge = 3600
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return object : PasswordEncoder {
            override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
                return rawPassword!! == encodedPassword
            }

            override fun encode(rawPassword: CharSequence?): String {
                return rawPassword.toString()
            }
        }
    }

    @Bean
    override fun userDetailsServiceBean(): UserDetailsService {
        return UserDetailsService { email ->
            val loadUser = userService.loadUser(email)
                    ?: throw UsernameNotFoundException("Incorrect combination of email and password.")
            log.info("Checking user - [$email]...")
            User(loadUser.email, loadUser.password, listOf(SimpleGrantedAuthority(loadUser.role)))
        }
    }

}
