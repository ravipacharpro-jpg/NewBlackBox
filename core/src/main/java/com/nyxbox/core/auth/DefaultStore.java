package com.nyxbox.core.auth;

/** Persists the id of the last successful login method so it can be reused next time. */
public interface DefaultStore {
    /** @return saved default method id, or null if none. */
    String getDefault();

    /** Persist the successful method id as the new default. */
    void setDefault(String id);

    /** Forget the saved default (used when the default stops working). */
    void clear();
}
