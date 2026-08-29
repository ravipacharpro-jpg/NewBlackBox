package com.nyxbox.core.auth;

/**
 * A single login strategy (e.g. Google, Facebook, Twitter, or any custom auth).
 * Implementations must be safe to call repeatedly and should throw or return false on failure.
 */
public interface LoginMethod {
    /** Stable unique id, used to persist the preferred/default method. */
    String id();

    /**
     * Attempt to authenticate.
     * @return true if authentication succeeded
     * @throws Exception if authentication failed (any error)
     */
    boolean authenticate() throws Exception;
}
