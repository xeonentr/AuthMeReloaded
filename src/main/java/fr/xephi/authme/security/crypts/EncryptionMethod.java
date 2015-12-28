package fr.xephi.authme.security.crypts;

/**
 * Public interface for custom password encryption methods.
 */
public interface EncryptionMethod {

    /**
     * Hash the given password for the given player name.
     *
     * @param password The password to hash
     * @param name     The name of the player (sometimes required to generate a salt with)
     *
     * @return The hash result for the password.
     * @see    HashResult
     */
    HashResult computeHash(String password, String name);

    /**
     * Hash the given password with the given salt for the given player.
     *
     * @param password The password to hash
     * @param salt     The salt to add to the hash
     * @param name     The player's name (sometimes required to generate a salt with)
     *
     * @return The hashed password
     * @see    #hasSeparateSalt()
     */
    String computeHash(String password, String salt, String name);

    /**
     * Check whether the given hash matches the clear-text password.
     *
     * @param hash      The hash to verify
     * @param password  The clear-text password to verify the hash against
     * @param salt      The salt if it is stored separately (null otherwise)
     * @param name      The player name to do the check for (sometimes required for generating the salt)
     *
     * @return True if the password matches, false otherwise
     */
    boolean comparePassword(String hash, String password, String salt, String name);

    /**
     * Generate a new salt to hash a password with.
     *
     * @return The generated salt, null if the method does not use a random text-based salt
     */
    String generateSalt();

    /**
     * Return whether the encryption method requires the salt to be stored separately and
     * passed again to {@link #comparePassword(String, String, String, String)}. Note that
     * an encryption method returning {@code false} does not imply that it uses no salt; it
     * may be embedded into the hash or it may use the username as salt.
     *
     * @return True if the salt has to be stored and retrieved separately, false otherwise
     */
    boolean hasSeparateSalt();

}
