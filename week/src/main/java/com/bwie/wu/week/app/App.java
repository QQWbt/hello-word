package com.bwie.wu.week.app;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * @Auther: 武丙涛
 * @Date: 2019/1/10 13:35:13
 * @Description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String path = Environment.getExternalStorageDirectory().getPath() + "/RiKao13";
        File file = new File (path);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(200, 200)//配置内存缓存图片的尺寸
                .memoryCacheSize(2 * 1024 * 1024)//配置内存缓存的大小
                .threadPoolSize(3)//配置加载图片的线程数
                .threadPriority(1000)//配置线程的优先级
                .diskCache(new UnlimitedDiskCache(file))//UnlimitedDiskCache 限制这个图片的缓存路径
                .diskCacheFileCount(50)//配置sdcard缓存文件的数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator ())//MD5这种方式生成缓存文件的名字
                .diskCacheSize(50 * 1024 * 1024)//在sdcard缓存50MB
                .build();//完成

        ImageLoader.getInstance().init(config);
    }
}
