package com.jiuqu.tools.ad;

import android.app.Activity;

public class SplashAdBase {
    /**
     * 广告位ID
     */
    protected String _adUnit = "";

    /**
     * 主Activity
     */
    protected Activity _context = null;

    public void InitAd(Activity context, String adId){
        _adUnit = adId;
        _context = context;
    }
}
