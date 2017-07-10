package sharif.dagger2experiment.di;

import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sharif.dagger2experiment.network.GithubService;

/**
 * Created by User on 7/8/2017.
 */

@Module(includes = NetworkModule.class)
public class GithubServiceModule {

    @Provides
    @GithubServiceScope
    GithubService getGithubService(Retrofit retrofit){
        return retrofit.create(GithubService.class);
    }


    @Provides
    @GithubServiceScope
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @GithubServiceScope
    Retrofit retrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .build();
    }

}
