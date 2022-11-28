package kh.farrukh.espielspringsecurity.auth.password_encoder

import org.apache.commons.logging.LogFactory
import java.security.SecureRandom
import java.util.regex.Pattern


/**
 * Implementation of PasswordEncoder that uses the BCrypt strong hashing function. Clients
 * can optionally supply a "version" ($2a, $2b, $2y) and a "strength" (a.k.a. log rounds
 * in BCrypt) and a SecureRandom instance. The larger the strength parameter the more work
 * will have to be done (exponentially) to hash the passwords. The default value is 10.
 *
 * @author Dave Syer
 */
class BCryptPasswordEncoder @JvmOverloads constructor(
    version: BCryptVersion,
    strength: Int,
    random: SecureRandom? = null
) :
    PasswordEncoder {
    private val BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}")
    private val logger = LogFactory.getLog(javaClass)
    private val strength: Int
    private val version: BCryptVersion
    private val random: SecureRandom?
    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param random the secure random instance to use
     */
    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     */
    @JvmOverloads
    constructor(version: BCryptVersion, random: SecureRandom? = null) : this(version, -1, random) {
    }
    /**
     * @param strength the log rounds to use, between 4 and 31
     * @param random the secure random instance to use
     */
    /**
     * @param strength the log rounds to use, between 4 and 31
     */
    @JvmOverloads
    constructor(strength: Int = -1, random: SecureRandom? = null) : this(BCryptVersion.`$2A`, strength, random) {
    }
    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param strength the log rounds to use, between 4 and 31
     * @param random the secure random instance to use
     */
    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param strength the log rounds to use, between 4 and 31
     */
    init {
        require(!(strength != -1 && (strength < BCrypt.MIN_LOG_ROUNDS || strength > BCrypt.MAX_LOG_ROUNDS))) { "Bad strength" }
        this.version = version
        this.strength = if (strength == -1) 10 else strength
        this.random = random
    }

    override fun encode(rawPassword: CharSequence): String {
        requireNotNull(rawPassword) { "rawPassword cannot be null" }
        val salt = salt
        return BCrypt.hashpw(rawPassword.toString(), salt)
    }

    private val salt: String
        private get() = if (random != null) {
            BCrypt.gensalt(version.version, strength, random)
        } else BCrypt.gensalt(version.version, strength)

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        requireNotNull(rawPassword) { "rawPassword cannot be null" }
        if (encodedPassword == null || encodedPassword.length == 0) {
            logger.warn("Empty encoded password")
            return false
        }
        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            logger.warn("Encoded password does not look like BCrypt")
            return false
        }
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword)
    }

    override fun upgradeEncoding(encodedPassword: String): Boolean {
        if (encodedPassword == null || encodedPassword.length == 0) {
            logger.warn("Empty encoded password")
            return false
        }
        val matcher = BCRYPT_PATTERN.matcher(encodedPassword)
        require(matcher.matches()) { "Encoded password does not look like BCrypt: $encodedPassword" }
        val strength = matcher.group(2).toInt()
        return strength < this.strength
    }

    /**
     * Stores the default bcrypt version for use in configuration.
     *
     * @author Lin Feng
     */
    enum class BCryptVersion(val version: String) {
        `$2A`("$2a"), `$2Y`("$2y"), `$2B`("$2b");

    }
}