package com.digital.playadstirvideoreward

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import jp.united.framgia.CallbackVideoRewardListener
import jp.united.framgia.CustomAdstirVideoReward


class MainActivity : AppCompatActivity() {

    private lateinit var load: Button
    private lateinit var play: Button
    private lateinit var statusWaiting: TextView
    private lateinit var statusLoading: TextView
    private lateinit var statusLoaded: TextView
    private lateinit var reward: TextView
    private var point: Int = 0
    private var isRewarded = false
    private lateinit var videoReward: CustomAdstirVideoReward

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpAdstir()

    }

    override fun onResume() {
        videoReward.onResume()
        if (isRewarded) {
            isRewarded = false
            Toast.makeText(this, R.string.reward_success, Toast.LENGTH_SHORT).show()
        }
        super.onResume()
    }

    override fun onPause() {
        videoReward.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        videoReward.onDestroy()
        super.onDestroy()
    }

    private fun setUpAdstir() {
        val email: String = "le.quang.hoa@framgia.com"
        val versionApp: Int = getAppVersion()
        val mediaUserId: String = "$email&$versionApp"

        videoReward = CustomAdstirVideoReward.getInstance(this)
        videoReward.setMediaUserId(mediaUserId)
        videoReward.initAdstirVideo()
        videoReward.setCallbackVideoRewardListener(object : CallbackVideoRewardListener {
            override fun onLoad() {
                play.isEnabled = true
                setStatus(LoadStatus.LOADED)
            }

            override fun onFailed() {
                load.isEnabled = true
                setStatus(LoadStatus.LOADING)
            }

            override fun onStartFailed() {
                load.isEnabled = true
                setStatus(LoadStatus.WAITING)
            }

            override fun onReward() {
                isRewarded = true
                reward.text = (++point).toString()
            }

            override fun onClose() {
                load.isEnabled = true
                setStatus(LoadStatus.WAITING)
            }
        })
    }

    private fun setUpView() {
        statusWaiting = findViewById<TextView>(R.id.status_waiting)
        statusLoading = findViewById<TextView>(R.id.status_loading)
        statusLoaded = findViewById<TextView>(R.id.status_loaded)

        reward = findViewById<TextView>(R.id.reward)

        play = findViewById<Button>(R.id.button_play)
        play.isEnabled = false
        play.setOnClickListener {
            if (videoReward.isPlayVideoReward()) {
                play.isEnabled = false
                setStatus(LoadStatus.WAITING)
                videoReward.showVideoReward()
            }
        }

        load = findViewById<Button>(R.id.button_load)
        load.setEnabled(true)
        load.setOnClickListener {
            load.isEnabled = false
            setStatus(LoadStatus.LOADING)
            videoReward.loadVideoReward()
        }

    }

    private fun setStatus(status: LoadStatus) {

        setTextViewEnabled(statusWaiting, false)
        setTextViewEnabled(statusLoading, false)
        setTextViewEnabled(statusLoaded, false)

        when (status) {
            LoadStatus.WAITING -> setTextViewEnabled(statusWaiting, true)
            LoadStatus.LOADING -> setTextViewEnabled(statusLoading, true)
            LoadStatus.LOADED -> setTextViewEnabled(statusLoaded, true)
        }
    }

    private fun setTextViewEnabled(t: TextView, enable: Boolean) {
        if (enable) {
            t.setTextColor(resources.getColor(R.color.colorActiveStatusText))
            t.setBackgroundResource(R.color.colorActiveStatusBackground)
        } else {
            t.setTextColor(resources.getColor(R.color.colorInactiveStatusText))
            t.setBackgroundResource(R.color.colorInactiveStatusBackground)
        }
    }


    private fun getAppVersion(): Int {
        val packageManager = this.packageManager
        var versionCode: Int = try {
            val packageInfo = packageManager.getPackageInfo(this.packageName, 0)
            packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            0
        }
        return versionCode
    }
}
