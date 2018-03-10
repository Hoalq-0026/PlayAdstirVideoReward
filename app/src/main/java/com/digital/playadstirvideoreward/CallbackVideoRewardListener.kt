package jp.united.framgia

/**
 * Created by le.quang.hoa on 2/27/18.
 */

interface CallbackVideoRewardListener {

    fun onLoad()

    fun onFailed()

    fun onStartFailed()

    fun onReward()

    fun onClose()

}
