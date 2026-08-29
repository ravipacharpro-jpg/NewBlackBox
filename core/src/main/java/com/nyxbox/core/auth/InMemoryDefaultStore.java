package com.nyxbox.core.auth;

import java.util.concurrent.atomic.AtomicReference;

/** Non-Android store for tests / headless usage. */
public class InMemoryDefaultStore implements DefaultStore {
    private final AtomicReference<String> def = new AtomicReference<>();

    @Override
    public String getDefault() {
        return def.get();
    }

    @Override
    public void setDefault(String id) {
        def.set(id);
    }

    @Override
    public void clear() {
        def.set(null);
    }
}
