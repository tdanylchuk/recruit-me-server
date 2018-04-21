package com.tdanylchuk.recruitme.configuration

import com.tdanylchuk.recruitme.service.RoleConstants.ADMIN_ROLE
import com.tdanylchuk.recruitme.service.UserService
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
class WebSecurityConfiguration(private val userService: UserService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http!!
                .httpBasic()
                .and()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/register").permitAll()
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

}
