package kh.farrukh.espielspringsecurity.auth.password_encoder

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PasswordEncoderConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}