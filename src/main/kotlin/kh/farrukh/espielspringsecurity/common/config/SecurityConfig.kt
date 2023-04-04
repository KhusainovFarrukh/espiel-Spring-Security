package kh.farrukh.espielspringsecurity.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher

//@Configuration
//@EnableWebSecurity
//class SecurityConfig(
//    @Value("\${spring.security.oauth2.whitelist}") private val whitelistUrls: List<String>
//) {
//
//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
////        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
//        // Require authentication for all requests
//        http.csrf().disable()
//        //http.oauth2Client();
//        http.csrf().disable().cors().disable().authorizeHttpRequests()
//            .requestMatchers(RequestMatcher {
//                whitelistUrls.contains(it.servletPath)
//            })
//            .permitAll()
//            .anyRequest()
//            .authenticated()
//
//        // Allow showing pages within a frame
//        http.headers().frameOptions().sameOrigin()
//        return http.build()
//    }
//
////    private fun jwtAuthenticationConverter(): JwtAuthenticationConverter? {
////        val jwtAuthenticationConverter = JwtAuthenticationConverter()
////        // Convert realm_access.roles claims to granted authorities, for use in access decisions
////        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(KeycloakRealmRoleConverter())
////        return jwtAuthenticationConverter
////    }
//
////    @Bean
////    fun jwtDecoderByIssuerUri(properties: OAuth2ResourceServerProperties): JwtDecoder? {
////        val issuerUri = properties.jwt.issuerUri
////        val jwtDecoder = JwtDecoders.fromIssuerLocation<JwtDecoder>(issuerUri) as NimbusJwtDecoder
////        // Use preferred_username from claims as authentication name, instead of UUID subject
////        jwtDecoder.setClaimSetConverter(UsernameSubClaimAdapter())
////        return jwtDecoder
////    }
//
////    internal class UsernameSubClaimAdapter :
////        Converter<Map<String?, Any?>?, Map<String, Any?>?> {
////        private val delegate = MappedJwtClaimSetConverter.withDefaults(emptyMap())
////        override fun convert(@NotNull claims: Map<String?, Any?>?): Map<String, Any?>? {
////            val convertedClaims = delegate.convert(claims!!)
////            val username = convertedClaims!!["preferred_username"] as String?
////            convertedClaims["sub"] = username
////            return convertedClaims
////        }
////    }
//
////    internal class KeycloakRealmRoleConverter :
////        Converter<Jwt, Collection<GrantedAuthority>> {
////        override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
////            val realmAccess = jwt.claims["realm_access"] as Map<String, Any>?
////            return (realmAccess!!["roles"] as List<String?>?)!!.stream()
////                .map { role: String? ->
////                    SimpleGrantedAuthority(
////                        role
////                    )
////                }
////                .collect(Collectors.toList())
////        }
////    }
//
////    @Bean
////    fun passwordEncoder(): Argon2PasswordEncoder? {
////        return Argon2PasswordEncoder(16, 256, 4, 10124, 11)
////    }
//
//}