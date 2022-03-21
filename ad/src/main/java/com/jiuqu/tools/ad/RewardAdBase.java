package com.jiuqu.tools.ad;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class RewardAdBase{
    /**
     * 广告位ID
     */
    protected String _adUnit = "";
    /**
     * 主Activity
     */
    protected Activity _context = null;
    /**
     * 存放激励视频广告的List
     */
    protected ArrayList rewardAdList = null;
    /**
     * 计时器
     */
    private Timer timer = null;
    /**
     * 计时器任务
     */
    private TimerTask timerTask = null;
    /**
     * 延迟时间队列
     */
    private int[] delayQueue = new int[]{0,2,4,6,8,10};
    /**
     * 延迟时间索引
     */
    private int delayIndex = 0;

    /**
     * 标志位，标志广告是否准备好
     */
    protected boolean isReady = false;
    /**
     * 广告事件监听
     */
    protected AdEventListener _adEventListener = null;
    /**
     * 属性，获取广告是否准备好
     * @return
     */
    public boolean IsReady(){
        return rewardAdList != null && rewardAdList.size() > 0;
    }

    /**
     * 初始化广告
     */
    public void InitAd(Activity context, String adId,@Nullable AdEventListener listener){
        _adUnit = adId;
        _context = context;
        _adEventListener = listener;
    }

    protected void OnInitFinished(){
        StartLoadAdTimer();
    }

    /**
     * 加载广告
     */
    public void LoadAd(){
        Log.d(AdUtils.REWARD_AD_TAG,"LoadAd");
    }

    /**
     * 展示广告
     */
    public void ShowAd(){

    }

    /**
     * 开始一个定时器，周期拉取激励视频广告
     */
    protected void StartLoadAdTimer(){
        StopLoadAdTimer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                LoadAd();
            }
        };
        Log.d(AdUtils.REWARD_AD_TAG,"Start Timer");
        timer = new Timer();
        timer.schedule(timerTask,delayQueue[delayIndex] * 1000);
    }

    /**
     * 停止定时器
     */
    protected void StopLoadAdTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 加载失败时，重新开启定时器加载广告
     */
    protected void OnLoadFailed(){
        delayIndex += 1;
        if(delayIndex >= delayQueue.length){
            delayIndex = delayQueue.length - 1;
        }
        StartLoadAdTimer();
        if(_adEventListener != null){
            _adEventListener.OnAdLoadFailed();
        }
    }

    /**
     * 加载成功时，取消定时器广告加载,并重制等待队列
     */
    protected void OnLoadSuccess(){
        delayIndex = 0;
        StopLoadAdTimer();
        if(_adEventListener != null){
            _adEventListener.OnAdLoaded();
        }
    }

    protected void OnAdClose(){
        StartLoadAdTimer();
        if(_adEventListener != null){
            _adEventListener.OnAdClosed();
        }
    }

    /**
     * For Down
     * Call Unity Event
     */

    protected void ShowRewardEvent(){
        if(_adEventListener != null){
            _adEventListener.OnAdShowSuccess();
        }

    }

    protected void RewardAdFailed(){
        if(_adEventListener != null){
            _adEventListener.OnAdShowFailed();
        }

    }

    protected void RewardAdEndPlay(){
        if(_adEventListener != null){
            _adEventListener.OnAdRewarded();
        }

    }

    protected void CloseRewardEvent(){
        if(_adEventListener != null){
            _adEventListener.OnAdClosed();
        }

    }
}
