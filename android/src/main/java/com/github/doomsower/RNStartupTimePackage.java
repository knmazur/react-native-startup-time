package com.github.doomsower;

import android.os.SystemClock;
import androidx.annotation.Nullable;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RNStartupTimePackage extends TurboReactPackage {
    private static final long START_MARK = SystemClock.uptimeMillis();

    private boolean enforceSingleInvocation;

    public RNStartupTimePackage() {
        this(true);
    }

    /**
     * When used for collecting the performance analytics, redundant samples adulterate the data.
     * Redundant invocations may occur depending on the lifecycle event to which the sampling is attached,
     * e.g. bringing the app forward from the background. In such case, the app is not actually
     * started up but just shown, so the reported startup time may appear unrealistic. Such unrealistic
     * samples are going to adulterate your data, hence should be avoided.
     *
     * @param enforceSingleInvocation use {@code true} to prevent the redundant readings. In case of a redundant call,
     *                              a {@link IllegalStateException} is thrown which you can {@code catch} in your code.
     */
    public RNStartupTimePackage(boolean enforceSingleInvocation) {
        this.enforceSingleInvocation = enforceSingleInvocation;
    }

    @Nullable
    @Override
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        if (name.equals(RNStartupTimeModuleImpl.NAME)) {
            return new RNStartupTimeModule(reactContext, START_MARK, enforceSingleInvocation);
        } else {
            return null;
        }
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return () -> {
            final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
           // boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;

            moduleInfos.put(
                    RNStartupTimeModuleImpl.NAME,
                    new ReactModuleInfo(
                            RNStartupTimeModuleImpl.NAME,
                            RNStartupTimeModuleImpl.NAME,
                            false, // canOverrideExistingModule
                            false, // needsEagerInit
                            true, // hasConstants
                            false, // isCxxModule
                            //isTurboModule // isTurboModule
                            false
            ));
            return moduleInfos;
        };
    }
}
