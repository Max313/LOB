package com.example.lammel.lob;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class ApplicationAnalytics extends Application {


        private static GoogleAnalytics sAnalytics;
        private static Tracker sTracker;

        @Override
        public void onCreate() {
            super.onCreate();

            sAnalytics = GoogleAnalytics.getInstance(this);
        }

        /**
         * Gets the default {@link Tracker} for this {@link Application}.
         * @return tracker
         */
        synchronized public Tracker getDefaultTracker() {
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            if (sTracker == null) {
                sTracker = sAnalytics.newTracker(R.xml.global_tracker);
            }

            return sTracker;
        }
}
