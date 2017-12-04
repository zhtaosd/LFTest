package com.longfor.core.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by zhanghaitao1 on 2017/12/4.
 */

public final class Configrator {
    private static final HashMap<Object, Object> LONGFOR_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTETCEPTERS = new ArrayList<>();

    private Configrator() {
        LONGFOR_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LONGFOR_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    static Configrator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configrator INSTANCE = new Configrator();
    }

    final HashMap<Object, Object> getLongforConfigs() {
        return LONGFOR_CONFIGS;
    }

    public final void configure() {
        initIcons();
        LONGFOR_CONFIGS.put(ConfigKeys.CONFIG_READY,true);
    }

    public final Configrator withApiHost(String host) {
        LONGFOR_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configrator withLoaderDelayed(long delayed) {
        LONGFOR_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configrator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configrator withInterceptor(Interceptor interceptor) {
        INTETCEPTERS.add(interceptor);
        LONGFOR_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTETCEPTERS);
        return this;
    }

    public final Configrator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTETCEPTERS.addAll(interceptors);
        LONGFOR_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTETCEPTERS);
        return this;
    }

    public final Configrator withWeChatAppId(String appId) {
        LONGFOR_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configrator withWeChatAppSecret(String appSecret) {
        LONGFOR_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configrator withActivity(Activity activity) {
        LONGFOR_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configrator withJavascriptInterface(@NonNull String name) {
        LONGFOR_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }
    private void checkConfiguration() {
        final boolean isReady = (boolean) LONGFOR_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LONGFOR_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LONGFOR_CONFIGS.get(key);
    }
}

