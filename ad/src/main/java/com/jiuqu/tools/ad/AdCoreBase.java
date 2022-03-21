package com.jiuqu.tools.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AdCoreBase {
    /**
     * 主Activity
     */
    protected  Activity _context = null;

    /**
     * 购买事件回调
     */
    protected BuyProductEvent _buyProductEvent = null;

    /**
     * Appid
     */
    protected String _appId = "";

    protected String _appKey = "";

    protected String _appSecret = "";
    /**
     * 初始化SDK
     * @param context
     */
    public void Init(Context context){}

    /**
     * 初始化SDK
     * @param context
     */
    public void Init(Context context,String appId,String appKey,String appSecret){
        _appId = appId;
        _appKey = appKey;
        _appSecret = appSecret;
    }

    /**
     * 设置主Activity
     * @param acy
     */
    private void SetActivity(Activity acy){
        if(_context != null)return;
        _context = acy;
    }

    /**
     * 需要时登陆SDK
     * @param activity
     */
    public void LoginSdkWhenNeed(Activity activity){
        SetActivity(activity);
    }

    /**
     * 在APP启动时登陆SDK
     * @param activity
     */
    public void LoginSdkByAppStart(Activity activity){
        SetActivity(activity);
    }

    /**
     * 登陆回调
     */
    public void OnActivityCheck(Activity acty,int requestCode, int resultCode, @Nullable Intent data){

    }



    /**
     * 购买商品
     * @param acty
     * @param pName
     * @param priceType
     */
    public void BuyProduct(Activity acty,String pName, int priceType,BuyProductEvent event){
        _buyProductEvent = event;
        SetActivity(acty);
    }

    /**
     * 隐私协议同意时
     * @param first 是否为第一次进入游戏且同意
     */
    public void OnPrivacyAgree(Activity context ,boolean first){

    }

    /**
     * 接收按键事件
     * @param keyCode
     * @param event
     * @return
     */
    public boolean OnExitKeyEvent(int keyCode, KeyEvent event){
        return false;
    }

}
