package com.jiuqu.tools.ad;

public interface AdEventListener {

    void OnAdLoaded();

    void OnAdLoadFailed();

    void OnAdShowSuccess();

    void OnAdShowFailed();

    void OnAdRewarded();

    void OnAdClosed();
}
