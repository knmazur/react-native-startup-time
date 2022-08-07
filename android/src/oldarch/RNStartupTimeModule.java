package com.github.doomsower;

import android.app.Activity;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = RNStartupTimeModuleImpl.NAME)
public class RNStartupTimeModule extends ReactContextBaseJavaModule {
    private RNStartupTimeModuleImpl module;

    public RNStartupTimeModule(ReactApplicationContext reactContext, long startMark, boolean enforceSingleInvocation) {
        super(reactContext);
        this.module = new RNStartupTimeModuleImpl(startMark, enforceSingleInvocation);
    }

    @Override
    public String getName() {
        return RNStartupTimeModuleImpl.NAME;
    }

    @ReactMethod
    public void getTimeSinceStartup(Promise promise) {
        module.getTimeSinceStartup(promise, getCurrentActivity());
    }
}
