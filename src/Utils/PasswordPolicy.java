/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 * PasswordPolicy
 *
 * A tiny utility class that enforces a “strong‐password” rule:
 *   • minimum 8 characters  
 *   • at least 1 lowercase letter  
 *   • at least 1 uppercase letter  
 *   • at least 1 digit  
 *   • at least 1 special symbol  (@ $ ! % * # ? &)
 *
 * All checks are done with a single regular expression so the class has
 * **no external dependencies**.  It is designed for *static use only*—
 * you never create an instance; just call {@code isStrong(...)}.
 */
public final class PasswordPolicy {

    /**
     * Regex parts:
     *  (?=.*[a-z])        → at least one lowercase
     *  (?=.*[A-Z])        → at least one uppercase
     *  (?=.*\\d)          → at least one digit
     *  (?=.*[@$!%*#?&])   → at least one special symbol from the allowed set
     *  [A-Za-z\\d@$!%*#?&]{8,} → total length ≥ 8, consisting only of allowed chars
     */
    private static final String REGEX =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    /** no instances allowed */
    private PasswordPolicy() {}

    /**
     * Returns {@code true} if the supplied password meets the policy.
     */
    public static boolean isStrong(String pwd){
        return pwd != null && pwd.matches(REGEX);
    }

    /**
     * Human-readable requirement string suitable for UI messages.
     */
    public static String requirementMsg(){
        return "Password must be at least 8 characters long and include "
             + "uppercase, lowercase, a digit, and a special symbol.";
    }
}
