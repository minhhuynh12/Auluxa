package net.dirox.auluxa.common;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.dagger.AppComponent;
import net.dirox.auluxa.common.dagger.AppModule;
import net.dirox.auluxa.common.dagger.DaggerAppComponent;
import net.dirox.auluxa.data.GeneratorSampleData;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Fonts/Lato/Lato-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        GeneratorSampleData.initData(getApplicationContext());

        // ImageLoader
        initImageLoader(getApplicationContext());

    }

    public AppComponent getComponent() {
        return appComponent;
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
