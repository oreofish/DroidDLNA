package com.zxt.dlna.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zxt.dlna.dmp.ContentItem;
import com.zxt.dlna.dmp.DeviceItem;

import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.support.model.DIDLContent;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseApplication extends MultiDexApplication {

    public static DeviceItem deviceItem;
    public static DeviceItem dmrDeviceItem;
    public static boolean isLocalDmr = true;
    public static AndroidUpnpService upnpService;
    public static Context mContext;
    private static InetAddress inetAddress;
    private static String hostAddress;
    private static String hostName;
    public DIDLContent didl;
    public ArrayList<ContentItem> listMusic;

    // public MediaUtils mediaUtils;
    public ArrayList<ContentItem> listPhoto;
    public ArrayList<ContentItem> listPlayMusic = new ArrayList();
    public ArrayList<ContentItem> listVideo;
    public ArrayList<ContentItem> listcontent;
    public HashMap<String, ArrayList<ContentItem>> map;
    public int position;

    public static Context getContext() {
        return mContext;
    }

    public static InetAddress getLocalIpAddress() {
        return inetAddress;
    }

    public static void setLocalIpAddress(InetAddress inetAddr) {
        inetAddress = inetAddr;

    }

    public static String getHostAddress() {
        return hostAddress;
    }

    public static void setHostAddress(String hostAddress) {
        BaseApplication.hostAddress = hostAddress;
    }

    public static String getHostName() {
        return hostName;
    }

    public static void setHostName(String hostName) {
        BaseApplication.hostName = hostName;
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .enableLogging() // Not necessary in common
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initImageLoader(getApplicationContext());
    }
}
