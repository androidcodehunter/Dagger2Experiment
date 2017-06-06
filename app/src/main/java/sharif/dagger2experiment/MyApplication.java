package sharif.dagger2experiment;

import android.app.Application;

import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sharif.dagger2experiment.network.GithubService;
import timber.log.Timber;

/**
 * Created by Sharif-PC on 6/6/2017.
 */

public class MyApplication extends Application {

    private GithubService githubService;
    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());

        Gson gson = gsonBuilder.create();


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        Retrofit githubRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .build();

        githubService = githubRetrofit.create(GithubService.class);
    }

    public GithubService getGithubService() {
        return githubService;
    }

    public Picasso getPicasso() {
        return picasso;
    }

}
