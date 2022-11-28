package kh.farrukh.espielspringsecurity.auth.password_encoder

interface PasswordEncoder {

    fun encode(password: CharSequence): String
    fun matches(password: CharSequence, encodedPassword: String): Boolean
    fun upgradeEncoding(encodedPassword: String): Boolean
}