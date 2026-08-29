package com.nyxbox.core.auth;

import java.util.ArrayList;
import java.util.List;

/**
 * Sequential fallback authenticator.
 *
 * <p>Behaviour:
 * <ol>
 *   <li>Try the saved default method first.</li>
 *   <li>If the default succeeds, return it (and keep it as default).</li>
 *   <li>If the default fails, clear it and restart the fallback over all methods in order.</li>
 *   <li>The first method that succeeds is saved as the new default and returned.</li>
 *   <li>If every method fails, {@link AuthException} is thrown.</li>
 * </ol>
 */
public class FallbackAuthenticator {
    private final List<LoginMethod> methods;
    private final DefaultStore store;

    public FallbackAuthenticator(List<LoginMethod> methods, DefaultStore store) {
        if (methods == null || methods.isEmpty()) {
            throw new IllegalArgumentException("At least one login method is required");
        }
        if (store == null) {
            throw new IllegalArgumentException("DefaultStore is required");
        }
        this.methods = new ArrayList<>(methods);
        this.store = store;
    }

    /**
     * Run the sequential fallback login.
     * @return the first {@link LoginMethod} that succeeded (also saved as default)
     * @throws AuthException if all methods failed
     */
    public LoginMethod authenticate() throws AuthException {
        String defId = store.getDefault();

        if (defId != null) {
            LoginMethod def = findById(defId);
            if (def != null) {
                if (tryMethod(def)) {
                    store.setDefault(def.id());
                    return def;
                }
                // Default failed -> restart fallback from scratch
                store.clear();
            }
        }

        for (LoginMethod m : methods) {
            if (defId != null && m.id().equals(defId)) {
                continue; // already tried above
            }
            if (tryMethod(m)) {
                store.setDefault(m.id());
                return m;
            }
        }

        throw new AuthException("All login methods failed");
    }

    private boolean tryMethod(LoginMethod m) {
        try {
            return m.authenticate();
        } catch (Throwable t) {
            return false;
        }
    }

    private LoginMethod findById(String id) {
        for (LoginMethod m : methods) {
            if (m.id().equals(id)) {
                return m;
            }
        }
        return null;
    }
}
