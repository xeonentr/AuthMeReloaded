package fr.xephi.authme.security.crypts.description;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Describes the type of salt the encryption algorithm uses. This is purely for documentation
 * purposes and is ignored by the code.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasSalt {

    /** The type of the salt. */
    SaltType value();

    /** For text salts, the length of the salt. */
    int length() default 0;

}