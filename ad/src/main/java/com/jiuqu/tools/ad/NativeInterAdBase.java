package com.jiuqu.tools.ad;

import android.app.Activity;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NativeInterAdBase {
    /**
     * 广告位ID
     */
    protected String _adUnit = "";
    /**
     * 主Activity
     */
    protected Activity _context = null;
    /**
     * 存放Banner的容器
     */
    protected FrameLayout _nativeInterView = null;
    /**
     * 列表数组
     */
    protected ArrayList nativeInterAdList = null;
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
     * 打开原生时，Banner是否在显示
     */
    private Boolean bannerState = false;

    /**
     * 检查广告是否准备好
     * @return
     */
    public boolean IsReady(){
        return nativeInterAdList != null && nativeInterAdList.size() > 0;
    }

    /**
     * 初始化广告
     */
    public void InitAd(Activity context, String adId,FrameLayout nativeInterView){
        _adUnit = adId;
        _context = context;
        _nativeInterView = nativeInterView;
    }

    protected void OnInitFinished(){
        StartLoadAdTimer();
    }

    /**
     * 加载广告
     */
    public void LoadAd(){
        Log.d(AdUtils.NATIVE_INTER_AD_TAG,"LoadAd");
    }

    /**
     * 展示广告
     */
    public void ShowAd(){
        bannerState = AdUtils.getInstance().IsBannerShow();
    }
    /**
     * 展示广告
     */
    public void ShowAd(boolean switchClose){
        bannerState = AdUtils.getInstance().IsBannerShow();
    }

    /**
     * 隐藏广告
     */
    public void HideAd(){
        if(bannerState){
            AdUtils.getInstance().ShowBannerAd();
        }
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
        Log.d(AdUtils.NATIVE_INTER_AD_TAG,"Start Timer");
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
    }

    /**
     * 加载成功时，取消定时器广告加载,并重制等待队列
     */
    protected void OnLoadSuccess(){
        delayIndex = 0;
        StopLoadAdTimer();
    }

    protected void OnAdClose(){
        if(nativeInterAdList == null || nativeInterAdList.size() >= 5)return;
        StartLoadAdTimer();
    }
}
