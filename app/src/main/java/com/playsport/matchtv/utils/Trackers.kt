package com.playsport.matchtv.utils

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal
import com.playsport.matchtv.utils.Id.FLYER
import com.playsport.matchtv.utils.Id.SIGNAL_ID

object Trackers {
    fun init(context: Context) {
        OneSignal.initWithContext(context)
        OneSignal.setAppId(SIGNAL_ID)

        AppsFlyerLib.getInstance().init(FLYER, null, context)
        AppsFlyerLib.getInstance().start(context)
    }
}