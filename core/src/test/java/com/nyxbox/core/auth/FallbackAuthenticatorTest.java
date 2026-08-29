package com.nyxbox.core.auth;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

public class FallbackAuthenticatorTest {

    static class FakeMethod implements LoginMethod {
        final String id;
        boolean succeed;
        int calls = 0;

        FakeMethod(String id, boolean succeed) {
            this.id = id;
            this.succeed = succeed;
        }

        @Override
        public String id() {
            return id;
        }

        @Override
        public boolean authenticate() throws Exception {
            calls++;
            if (!succeed) {
                throw new Exception("fail " + id);
            }
            return true;
        }
    }

    @Test
    public void firstSucceeds_savesDefault() throws Exception {
        FakeMethod a = new FakeMethod("google", true);
        FakeMethod b = new FakeMethod("fb", false);
        InMemoryDefaultStore store = new InMemoryDefaultStore();
        FallbackAuthenticator f = new FallbackAuthenticator(List.of(a, b), store);

        assertEquals("google", f.authenticate().id());
        assertEquals("google", store.getDefault());
        assertEquals(1, a.calls);
        assertEquals(0, b.calls);
    }

    @Test
    public void middleSucceeds_fallbackSkipsFirst() throws Exception {
        FakeMethod a = new FakeMethod("google", false);
        FakeMethod b = new FakeMethod("fb", true);
        InMemoryDefaultStore store = new InMemoryDefaultStore();
        FallbackAuthenticator f = new FallbackAuthenticator(List.of(a, b), store);

        assertEquals("fb", f.authenticate().id());
        assertEquals("fb", store.getDefault());
        assertEquals(1, a.calls);
        assertEquals(1, b.calls);
    }

    @Test(expected = AuthException.class)
    public void allFail_throws() throws Exception {
        FakeMethod a = new FakeMethod("google", false);
        FakeMethod b = new FakeMethod("fb", false);
        new FallbackAuthenticator(List.of(a, b), new InMemoryDefaultStore()).authenticate();
    }

    @Test
    public void defaultTriedFirstOnNextRun() throws Exception {
        FakeMethod a = new FakeMethod("google", true);
        FakeMethod b = new FakeMethod("fb", false);
        InMemoryDefaultStore store = new InMemoryDefaultStore();
        FallbackAuthenticator f = new FallbackAuthenticator(List.of(a, b), store);
        f.authenticate(); // saves "google" as default

        a.calls = 0;
        b.calls = 0;
        FallbackAuthenticator f2 = new FallbackAuthenticator(List.of(a, b), store);
        assertEquals("google", f2.authenticate().id());
        assertEquals(1, a.calls); // default tried first, no fallback needed
        assertEquals(0, b.calls);
    }

    @Test
    public void defaultFails_restartsFallback() throws Exception {
        FakeMethod a = new FakeMethod("google", true); // becomes default, then flips to fail
        FakeMethod b = new FakeMethod("fb", true);
        InMemoryDefaultStore store = new InMemoryDefaultStore();
        FallbackAuthenticator f = new FallbackAuthenticator(List.of(a, b), store);
        f.authenticate(); // saves "google"

        a.succeed = false; // default now broken
        a.calls = 0;
        b.calls = 0;
        FallbackAuthenticator f2 = new FallbackAuthenticator(List.of(a, b), store);
        assertEquals("fb", f2.authenticate().id());
        assertEquals("fb", store.getDefault()); // default updated to fallback winner
        assertEquals(1, a.calls); // default tried once and failed
        assertEquals(1, b.calls); // fallback picked the next working method
    }
}
