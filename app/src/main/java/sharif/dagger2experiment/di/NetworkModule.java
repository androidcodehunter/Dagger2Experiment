package sharif.dagger2experiment.di;

import android.content.Context;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by User on 7/8/2017.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @GithubServiceScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
    }

    @Provides
    @GithubServiceScope
    public Cache cache(File cacheFiles){
       return new Cache(cacheFiles, 10 * 1000* 1000); //10mb cache
    }

    @Provides
    @GithubServiceScope
    public File cacheFiles(@Named("app_context") Context context){
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @GithubServiceScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }
}
