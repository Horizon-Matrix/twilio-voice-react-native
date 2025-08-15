package com.twiliovoicereactnative;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import expo.modules.core.interfaces.ReactActivityLifecycleListener;

public class ExpoActivityLifecycleListener implements ReactActivityLifecycleListener {
  private VoiceActivityProxy voiceActivityProxy;

  @Override
  public void onCreate(Activity activity, Bundle savedInstanceState) {
    if (voiceActivityProxy == null) {
      voiceActivityProxy = new VoiceActivityProxy(activity, permission -> {
        if (Manifest.permission.RECORD_AUDIO.equals(permission)) {
          Toast.makeText(
            activity,
            "Microphone permission is required for calls.",
            Toast.LENGTH_LONG
          ).show();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                   Manifest.permission.BLUETOOTH_CONNECT.equals(permission)) {
          Toast.makeText(
            activity,
            "Bluetooth permission is required for call audio routing.",
            Toast.LENGTH_LONG
          ).show();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                   Manifest.permission.POST_NOTIFICATIONS.equals(permission)) {
          Toast.makeText(
            activity,
            "Notification permission is required to show call notifications.",
            Toast.LENGTH_LONG
          ).show();
        }
      });
    }

    voiceActivityProxy.onCreate(savedInstanceState);
  }

  @Override
  public boolean onNewIntent(Intent intent) {
    if (voiceActivityProxy != null) {
      voiceActivityProxy.onNewIntent(intent);
    }
    return false;
  }

  @Override
  public void onDestroy(Activity activity) {
    if (voiceActivityProxy != null) {
      voiceActivityProxy.onDestroy();
    }
  }
}
