-- ============================================================================
-- login_fix.lua  (v2)
-- Patches Tencent IMSDK / MSDK social login for: GOOGLE, FACEBOOK, TWITTER
--
-- What this does:
--   1) Forces system-browser (non-embedded-webview) login for each platform.
--      (Facebook deprecated embedded webview; forcing browser login is the
--       standard fix that actually makes FB/Twitter/Google login work.)
--   2) Keeps a global guard so any later SetMSDKConfig call cannot re-enable
--      the broken embedded-webview path.
--   3) Provides a configurable FALLBACK_ORDER so you can prefer/deprioritise
--      platforms, plus a tryLoginWithFallback() helper for the game's own
--      login UI to attempt one platform then the next.
--
-- IMPORTANT (read before shipping):
--   * The enum values below are COMMON MSDK defaults. Your specific IMSDK/IMSDK
--     version may use different values -> override PLATFORM/CHANNEL if needed.
--   * True automatic cross-platform fallback needs the GAME's own
--     "start login for platform X" function. Plug it into tryLoginWithFallback()
--     (marked below). Without it, this script still fixes each platform's login,
--     just doesn't auto-switch at runtime.
-- ============================================================================

pcall(function()
    -- ===== Platform / Channel enum overrides (set per your MSDK/IMSDK version) =====
    local PLATFORM = {
        GOOGLE   = BP_ENUM_PLAYFORM_GOOGLE   or 5,
        FACEBOOK = BP_ENUM_PLAYFORM_BGBG     or 2,
        TWITTER  = BP_ENUM_PLAYFORM_TWITTER  or 42,
    }
    local CHANNEL = {
        GOOGLE   = BP_ENUM_IMSDK_CHANNEL_GOOGLE   or 4,
        FACEBOOK = BP_ENUM_IMSDK_CHANNEL_FACEBOOK or 1,
        TWITTER  = BP_ENUM_IMSDK_CHANNEL_TWITTER  or 35,
    }

    -- order to try / prefer. Edit freely, e.g. { "GOOGLE", "FACEBOOK", "TWITTER" }
    local FALLBACK_ORDER = { "GOOGLE", "FACEBOOK", "TWITTER" }

    -- config keys that disable embedded webview (force system browser)
    local WEBVIEW_CONFIG = {
        GOOGLE   = "IMSDK_GOOGLE_LOGIN_USING_WEB",
        FACEBOOK = "IMSDK_FACEBOOK_LOGIN_USING_WEB",
        TWITTER  = "IMSDK_TWITTER_LOGIN_USING_WEB",
    }

    -- safely force a platform's webview-off config
    local function forceWebviewOff(Helper, platform)
        local key = WEBVIEW_CONFIG[platform]
        if key and Helper then
            pcall(Helper.SetMSDKConfig, Helper, { [key] = "false" }, false)
        end
    end

    -- ===== Patch deeplink / system-webview login =====
    local DL = require("client.logic.login.logic_imsdk_deeplink_login")
    if DL then
        local OWL = DL.LoginViaSystemWebview
        if OWL then
            DL.LoginViaSystemWebview = function(Self, LoginType)
                for _, p in ipairs(FALLBACK_ORDER) do
                    if LoginType == PLATFORM[p] then return false end
                end
                return OWL(Self, LoginType)
            end
        end
        local OWB = DL.BindViaSystemWebview
        if OWB then
            DL.BindViaSystemWebview = function(Self, LoginType)
                for _, p in ipairs(FALLBACK_ORDER) do
                    if LoginType == CHANNEL[p] then return false end
                end
                return OWB(Self, LoginType)
            end
        end
    end

    -- ===== Patch main Login() =====
    local II = require("client.logic.login.logic_imsdk_interface")
    if II then
        local OL = II.Login
        if OL then
            II.Login = function(Self, LoginType, ExtraJson, SkipLocalCacheCheck)
                local IH = import("IMSDKHelper")
                if IH then
                    local IHI = IH.GetInstance()
                    if IHI then
                        local iLCI = IHI:ConvertTConndChannel2IMSDKChannel(LoginType)
                        for _, p in ipairs(FALLBACK_ORDER) do
                            if iLCI == CHANNEL[p] or LoginType == PLATFORM[p] then
                                forceWebviewOff(IHI, p)
                            end
                        end
                    end
                end
                return OL(Self, LoginType, ExtraJson, SkipLocalCacheCheck)
            end
        end
    end

    -- ===== Global SetMSDKConfig guard (never let embedded webview turn back on) =====
    local IH = import("IMSDKHelper")
    if IH then
        local Helper = IH.GetInstance()
        if Helper then
            local OriginalSetConfig = Helper.SetMSDKConfig
            if OriginalSetConfig then
                Helper.SetMSDKConfig = function(Self, Config, Save)
                    if Config then
                        for _, p in ipairs(FALLBACK_ORDER) do
                            local k = WEBVIEW_CONFIG[p]
                            if Config[k] then Config[k] = "false" end
                        end
                    end
                    return OriginalSetConfig(Self, Config, Save)
                end
            end
        end
    end

    -- ============================================================================
    -- tryLoginWithFallback(...)
    -- Call this from the GAME's login UI instead of a direct login call, so that
    -- if one platform fails, the next in FALLBACK_ORDER is attempted.
    --
    -- PLUG IN YOUR GAME'S login initiator below. Example shape:
    --    local function gameStartLogin(platform)
    --        -- your game's function that begins login for `platform`
    --    end
    -- By default we just call the patched II.Login with each platform's LoginType.
    -- ============================================================================
    local function gameStartLogin(platform)
        local II2 = require("client.logic.login.logic_imsdk_interface")
        if II2 and II2.Login then
            return II2.Login(nil, PLATFORM[platform], nil, false)
        end
    end

    _G.tryLoginWithFallback = function(...)
        for _, p in ipairs(FALLBACK_ORDER) do
            local ok, err = pcall(gameStartLogin, p)
            if ok then
                return true, p   -- success on platform p
            end
            -- else try next platform
        end
        return false, nil
    end
end)
