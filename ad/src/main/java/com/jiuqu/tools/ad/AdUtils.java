package com.jiuqu.tools.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdUtils {

    public static final String REWARD_AD_TAG = "RewardAdTag";
    public static final String INTER_AD_TAG = "InterAdTag";
    public static final String IMAGE_AD_TAG = "ImageAdTag";
    public static final String BANNER_AD_TAG = "BannerAdTag";
    public static final String NATIVE_AD_TAG = "NativeAdTag";
    public static final String NATIVE_INTER_AD_TAG = "NativeInterAdTag";
    public static final String SPLASH_AD_TAG = "SplashAdTag";

    private final String _appId = "105745429";
    private final String _appKey = "";
    private final String _appSecret = "==";

    private final String _bannerId = "testw6vs28auh3";
    private final String _rewardId = "testx9dtjwj8hp";
    private final String _interId = "testb4znbuh3n2";
    private final String _imageId = "b61a47119a13ff";
    private final String _nativeId = "testy63txaom86";
    private final String _nativeInterId = "testy63txaom86";
    private final String _splashId = "testd7c5cewoj6";

    /**
     * 单例实现
     */
    private static AdUtils instance = null;
    public static synchronized AdUtils getInstance(){
        if(instance == null){
            instance = new AdUtils();
        }
        return instance;
    }

    /**
     * 主Activity
     */
    private Activity _context = null;

    /**
     * 广告核心适配器
     */
    private AdCoreBase _adCoreAdapter = null;

    /**
     * 激励视频适配器
     */
    private RewardAdBase _rewardAdapter = null;
    /**
     * 插屏视频适配器
     */
    private InterAdBase _interAdapter = null;
    /**
     * 图文插屏适配器
     */
    private ImageAdBase _imageAdapter = null;
    /**
     * Banner适配器
     */
    private BannerAdBase _bannerAdapter = null;
    /**
     * 原生广告适配器
     */
    private NativeAdBase _nativeAdapter = null;
    /**
     * 顶部原生适配器
     */
    private TopNativeAdBase _topNativeAdapter = null;
    /**
     * 原生插屏广告适配器
     */
    private NativeInterAdBase _nativeInterAdapter = null;
    /**
     * 开屏广告适配器
     */
    private SplashAdBase _splashAdapter = null;

    /**
     * 初始化广告SDK
     * @param context
     */
    public void InitAdSdk(Context context){
        GetCoreAdapter();
        if(_adCoreAdapter != null){
            _adCoreAdapter.Init(context);
            _adCoreAdapter.Init(context,_appId,_appKey,_appSecret);
        }
    }

    /**
     * 需要时登陆sdk
     */
    public void LoginSdkWhenNeed(Activity context){
        if(_adCoreAdapter != null){
            _adCoreAdapter.LoginSdkWhenNeed(context);
        }
    }

    /**
     * 在APP启动时登陆SDK
     * @param context
     */
    public void LoginSdkByAppStart(Activity context){
        if(_adCoreAdapter != null){
            _adCoreAdapter.LoginSdkByAppStart(context);
        }
    }

    /**
     *  购买商品
     * @param acty
     * @param pName
     * @param priceType
     */
    public void BuyProduct(Activity acty,String pName, int priceType,BuyProductEvent event){
        if(_adCoreAdapter != null){
            _adCoreAdapter.BuyProduct(acty, pName, priceType,event);
        }
    }

    /**
     * 主Activity事件回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void OnActivityCheck(Activity acty,int requestCode, int resultCode, @Nullable Intent data){
        if(_adCoreAdapter != null){
            _adCoreAdapter.OnActivityCheck(acty,requestCode, resultCode, data);
        }
    }

    /**
     * 隐私协议同意时
     * @param context 事件触发的Activity
     * @param first 是否为首次进入游戏的同意
     */
    public void OnPrivacyAgree(Activity context,boolean first){
        if(_adCoreAdapter != null){
            _adCoreAdapter.OnPrivacyAgree(context,first);
        }
    }

    /**
     * 初始化激励视频广告SDK
     * @param context 主Activity
     */
    public void InitRewardAdSdk(Activity context, @Nullable AdEventListener listener){
        if(_context == null)_context = context;
        GetRewardAdapter();
        if(_rewardAdapter != null){
            _rewardAdapter.InitAd(context,_rewardId,listener);
        }
    }

    /**
     * 初始化插屏广告SDK
     * @param context
     */
    public void InitInterAdSdk(Activity context, @Nullable AdEventListener listener){
        if(_context == null)_context = context;
        GetInterAdapter();
        if(_interAdapter != null){
            _interAdapter.InitAd(context,_interId,listener);
        }
    }

    /**
     * 初始化图文插屏广告SDK
     * @param context
     */
    public void InitImageAdSdk(Activity context){
        if(_context == null)_context = context;
        GetImageAdapter();
        if(_imageAdapter != null){
            _imageAdapter.InitAd(context,_imageId);
        }
    }

    /**
     * 初始化Banner广告
     * @param context
     * @param bannerView
     */
    public void InitBannerAdSdk(Activity context, FrameLayout bannerView){
        if(_context == null)_context = context;
        GetBannerAdapter();
        if(_bannerAdapter != null){
            _bannerAdapter.InitAd(context,_bannerId,bannerView);
        }
    }

    /**
     * 初始化原生广告
     * @param context
     * @param nativeView
     */
    public void InitNativeAdSdk(Activity context, FrameLayout nativeView){
        if(_context == null)_context = context;
        GetNativeAdapter();
        if(_nativeAdapter != null){
            _nativeAdapter.InitAd(context,_nativeId,nativeView);
        }
    }
    /**
     * 初始化顶部原生广告
     * @param context
     * @param nativeView
     */
    public void InitTopNativeAdSdk(Activity context, FrameLayout nativeView){
        if(_context == null)_context = context;
        GetTopNativeAdapter();
        if(_topNativeAdapter != null){
            _topNativeAdapter.InitAd(context,_nativeId,nativeView);
        }
    }

    /**
     * 初始化原生插屏广告
     * @param context
     * @param nativeInterView
     */
    public void InitNativeInterAdSdk(Activity context,FrameLayout nativeInterView){
        if(_context == null)_context = context;
        GetNativeInterAdapter();
        if(_nativeInterAdapter != null){
            _nativeInterAdapter.InitAd(context,_nativeInterId,nativeInterView);
        }
    }

    /**
     * 初始化开屏广告并自动展示
     * @param context
     */
    public void InitSplashAdSdk(Activity context){
        if(_context == null)_context = context;
        GetSplashAdapter();
        if(_splashAdapter != null){
            _splashAdapter.InitAd(context,_splashId);
        }
    }

    /**
     * 激励视频是否准备好
     * @return
     */
    public boolean IsRewardReady(){
        return _rewardAdapter != null && _rewardAdapter.IsReady();
    }

    /**
     * 播放激励视频
     */
    public void ShowRewardAd(){
        if(_rewardAdapter == null) return;
        _rewardAdapter.ShowAd();
    }

    /**
     * 插屏视频是否准备好
     * @return
     */
    public boolean IsInterReady(){return _interAdapter != null && _interAdapter.IsReady();}

    /**
     * 展示插屏视频
     */
    public void ShowInterAd(){
        if(_interAdapter == null)return;
        _interAdapter.ShowAd();
    }

    /**
     * 图文插屏是否准备好
     * @return
     */
    public boolean IsImageReady(){ return _imageAdapter != null && _imageAdapter.IsReady();}

    /**
     * 展示图文插屏
     */
    public void ShowImageAd(){
        if(_imageAdapter == null)return;
        _imageAdapter.ShowAd();
    }

    /**
     * Banner是否显示状态
     * @return
     */
    public boolean IsBannerShow(){return _bannerAdapter != null && _bannerAdapter.bannerShowState;}

    /**
     * 展示Banner
     */
    public void ShowBannerAd(){
        if(_bannerAdapter == null)return;
        _bannerAdapter.ShowAd();
    }

    /**
     * 隐藏Banner
     */
    public void HideBannerAd(){
        if(_bannerAdapter == null) return;
        _bannerAdapter.HideAd();
    }

    /**
     * 原生广告是否准备好
     * @return
     */
    public boolean IsNativeReady(){return _nativeAdapter != null && _nativeAdapter.IsReady();}

    /**
     * 默认参数展示原生广告
     */
    public void ShowNativeAd(){
        if(_nativeAdapter == null)return;
        _nativeAdapter.ShowAd();
    }

    /**
     * 自定义参数展示原生广告
     * @param btnup
     * @param showBg
     * @param offset
     */
    public void ShowNativeAd(boolean btnup, boolean showBg, int offset){
        if(_nativeAdapter == null)return;
        _nativeAdapter.ShowAd(btnup,showBg,offset);
    }

    /**
     * 自定义参数展示原生广告
     * @param btnup
     * @param showBg
     * @param offset
     */
    public void ShowNativeAd(boolean btnup, boolean showBg, int offset,boolean switchClose){
        if(_nativeAdapter == null)return;
        _nativeAdapter.ShowAd(btnup,showBg,offset,switchClose);
    }
    /**
     * 关闭原生广告
     */
    public void HideNativeAd(){
        if(_nativeAdapter == null)return;
        _nativeAdapter.HideAd();
    }

    /**
     * 顶部原生是否准备好
     * @return
     */
    public boolean IsTopNativeAdReady(){return _topNativeAdapter != null && _topNativeAdapter.IsReady();}

    /**
     * 默认参数展示原生广告
     */
    public void ShowTopNativeAd(){
        if(_topNativeAdapter == null)return;
        _topNativeAdapter.ShowAd();
    }

    /**
     * 自定义参数展示原生广告
     * @param btnup
     * @param showBg
     * @param offset
     */
    public void ShowTopNativeAd(boolean btnup, boolean showBg, int offset){
        if(_topNativeAdapter == null)return;
        _topNativeAdapter.ShowAd(btnup,showBg,offset);
    }

    /**
     * 自定义参数展示原生广告
     * @param btnup
     * @param showBg
     * @param offset
     */
    public void ShowTopNativeAd(boolean btnup, boolean showBg, int offset,boolean switchClose){
        if(_topNativeAdapter == null)return;
        _topNativeAdapter.ShowAd(btnup,showBg,offset,switchClose);
    }

    public void HideTopNativeAd(){
        if(_topNativeAdapter == null)return;
        _topNativeAdapter.HideAd();
    }

    /**
     * 判断原生插屏广告是否准备好
     * @return
     */
    public boolean IsNativeInterReady(){return _nativeInterAdapter != null && _nativeInterAdapter.IsReady();}

    /**
     * 展示原生插屏广告
     */
    public void ShowNativeInterAd(){
        if(_nativeInterAdapter == null) return;
        _nativeInterAdapter.ShowAd();
    }

    /**
     * 展示原生插屏广告
     */
    public void ShowNativeInterAd(boolean switchClose){
        if(_nativeInterAdapter == null) return;
        _nativeInterAdapter.ShowAd(switchClose);
    }

    /**
     * 关闭原生插屏广告
     */
    public void HideNativeInterAd(){
        if(_nativeInterAdapter == null) return;
        _nativeInterAdapter.HideAd();
    }

    /**
     * 接受按键事件
     * @param keyCode
     * @param event
     * @return
     */
    public boolean OnExitKeyEvent(int keyCode, KeyEvent event){
        if(_adCoreAdapter == null) return false;
        return _adCoreAdapter.OnExitKeyEvent(keyCode,event);
    }


    /**
     * 通过反射获取广告核心
     */
    public void GetCoreAdapter(){
        AdCoreBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWAdCore","com.jiuqu.tools.ad.miad.MIAdCore"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],AdCoreBase.class);
            if(adapter != null){
                break;
            }
        }
        _adCoreAdapter = adapter;
    }

    /**
     * 通过反射获取广告Adapter
     */

    public void GetRewardAdapter(){
        Log.d(REWARD_AD_TAG,"Start Get Adapter");
        RewardAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWRewardAd","com.jiuqu.tools.ad.miad.MIRewardAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],RewardAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _rewardAdapter = adapter;
    }

    public void GetInterAdapter(){
        Log.d(INTER_AD_TAG,"Start Get Adapter");
        InterAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWInterAd","com.jiuqu.tools.ad.miad.MIInterAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],InterAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _interAdapter = adapter;
    }

    public void GetImageAdapter(){
        Log.d(IMAGE_AD_TAG,"Start Get Adapter");
        ImageAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWImageAd","com.jiuqu.tools.ad.miad.MIImageAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],ImageAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _imageAdapter = adapter;
    }

    public void GetBannerAdapter(){
        Log.d(BANNER_AD_TAG,"Start Get Adapter");
        BannerAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWBannerAd","com.jiuqu.tools.ad.miad.MIBannerAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],BannerAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _bannerAdapter = adapter;
    }

    public void GetNativeAdapter(){
        Log.d(NATIVE_AD_TAG,"Start Get Adapter");
        NativeAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWNativeAd","com.jiuqu.tools.ad.miad.MINativeAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],NativeAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _nativeAdapter = adapter;
    }

    public void GetTopNativeAdapter(){
        Log.d(NATIVE_AD_TAG,"Start Get Adapter");
        TopNativeAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWTopNativeAd","com.jiuqu.tools.ad.miad.MITopNativeAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],TopNativeAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _topNativeAdapter = adapter;
    }

    public void GetNativeInterAdapter(){
        Log.d(NATIVE_INTER_AD_TAG,"Start Get Adapter");
        NativeInterAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWNativeInterAd","com.jiuqu.tools.ad.miad.MINativeInterAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],NativeInterAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _nativeInterAdapter = adapter;
    }

    public void GetSplashAdapter(){
        Log.d(SPLASH_AD_TAG,"Start Get Adapter");
        SplashAdBase adapter = null;
        String[] strs = new String[]{"com.jiuqu.tools.ad.hwad.HWSplashAd"};
        for(int i = 0;i<strs.length;i++){
            adapter = GetAdapterByType(strs[i],SplashAdBase.class);
            if(adapter != null){
                break;
            }
        }
        _splashAdapter = adapter;
    }

    /**
     * 通过反射获取广告layout，防止使用R的问题
     */
    public int GetLayoutByName(String layoutName){
        int i = -1;
        try {
            Class<?> cls = Class.forName(_context.getPackageName() + ".R$layout");
            i = cls.getField(layoutName).getInt(null);
        } catch (Exception e) {
            Log.d("AdUtils Exception" ,"找不到Layout ：" + layoutName);
            return i;
        }
        return i;
    }
    /**
     * 通过反射获取界面控件id，防止使用R的问题
     */
    public int GetViewIdByName(String idName){
        int i = -1;
        try {
            Class<?> cls = Class.forName(_context.getPackageName() + ".R$id");
            i = cls.getField(idName).getInt(null);
        } catch (Exception e) {
            Log.d("AdUtils Exception" ,"找不到Id ：" + idName);
            return i;
        }
        return i;
    }

    /**
     * 通过反射获取颜色，防止使用R的问题
     */
    public int GetColorByName(String colorName){
        int i = -1;
        try {
            Class<?> cls = Class.forName(_context.getPackageName() + ".R$color");
            i = cls.getField(colorName).getInt(null);
        } catch (Exception e) {
            Log.d("AdUtils Exception" ,"找不到Color ：" + colorName);
            return i;
        }
        return i;
    }

    /**
     * 通过反射获取drawable，防止使用R的问题
     */
    public int GetDrawableByName(String drawableName){
        int i = -1;
        try {
            Class<?> cls = Class.forName(_context.getPackageName() + ".R$drawable");
            i = cls.getField(drawableName).getInt(null);
        } catch (Exception e) {
            Log.d("AdUtils Exception" ,"找不到Drawable ：" + drawableName);
            return i;
        }
        return i;
    }

    /**
     * 根据给定的url 下载bitmap
     * @param url
     * @return
     */
    public Bitmap GetBitmapByUrl(String url){
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);//读取图像数据
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    /**
     * 根据名字及类型返回适配器
     * @param name
     * @param tClass
     * @param <T>
     * @return
     */
    private <T extends Object> T GetAdapterByType(String name,Class<T> tClass){
        T t = null;
        try {
            t = (T)Class.forName(name).newInstance();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return t;
    }
}