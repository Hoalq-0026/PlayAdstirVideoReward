package com.digital.playadstirvideoreward

import android.app.Activity
import com.ad_stir.interstitial.AdstirVideoAds
import com.ad_stir.videoreward.AdstirVideoReward
import com.ad_stir.videoreward.AdstirVideoRewardListener

class CustomAdstirVideoReward private constructor(private val activity: Activity) {

    private var adstirVideoReward: AdstirVideoReward? = null
    private var callbackVideoRewardListener: CallbackVideoRewardListener? = null

    private val adstirVideoRewardListener = object : AdstirVideoRewardListener {

        override fun onLoad(p0: Int) {
            if (callbackVideoRewardListener != null) {
                callbackVideoRewardListener!!.onLoad()
            }
        }

        override fun onReward(p0: Int) {
            if (callbackVideoRewardListener != null) {
                callbackVideoRewardListener!!.onReward()
            }
        }

        override fun onFailed(p0: Int) {
            if (callbackVideoRewardListener != null) {
                callbackVideoRewardListener!!.onFailed()
            }
        }

        override fun onStartFailed(p0: Int) {
            if (callbackVideoRewardListener != null) {
                callbackVideoRewardListener!!.onStartFailed()
            }
        }

        override fun onClose(p0: Int) {
            if (callbackVideoRewardListener != null) {
                callbackVideoRewardListener!!.onClose()
            }
        }

        override fun onStart(p0: Int) {
        }

        override fun onRewardCanceled(p0: Int) {
        }

        override fun onFinished(p0: Int) {
        }

    }
    fun setCallbackVideoRewardListener(listener: CallbackVideoRewardListener) {
        callbackVideoRewardListener = listener
    }

    fun setMediaUserId(mediaUserId: String) {
        AdstirVideoAds.setMediaUserID(mediaUserId)
    }

    fun initAdstirVideo() {
        val spotIds = intArrayOf(activity.resources.getInteger(R.integer.spot_id))
        AdstirVideoAds.init(activity, activity.getString(R.string.mediaId), *spotIds)
        adstirVideoReward = AdstirVideoReward(activity, activity.getString(R.string.mediaId), activity.resources.getInteger(R.integer.spot_id))
        adstirVideoReward!!.adstirVideoRewardListener = adstirVideoRewardListener
        adstirVideoReward!!.load()
    }

    fun loadVideoReward() {
        if (adstirVideoReward != null) {
            adstirVideoReward!!.load()
        }
    }

    fun showVideoReward() {
        if (adstirVideoReward != null && adstirVideoReward!!.canShow()) {
            adstirVideoReward!!.showRewardVideo()
        }
    }

    fun isPlayVideoReward(): Boolean = adstirVideoReward!!.canShow()

    fun onResume() {
        if (adstirVideoReward != null) {
            adstirVideoReward!!.resume()
        }
    }

    fun onPause() {
        if (adstirVideoReward != null) {
            adstirVideoReward!!.pause()
        }
    }

    fun onDestroy() {
        if (adstirVideoReward != null) {
            adstirVideoReward!!.destroy()
        }
    }

    companion object {
        private var instance: CustomAdstirVideoReward? = null

        fun getInstance(activity: Activity): CustomAdstirVideoReward {
            if (instance == null) {
                instance = CustomAdstirVideoReward(activity)
            }
            return instance!!
        }
    }
}
