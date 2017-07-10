package sharif.dagger2experiment;

import android.app.Application;
import com.squareup.picasso.Picasso;

import sharif.dagger2experiment.di.ContextModule;
import sharif.dagger2experiment.di.DaggerGithubAppComponent;
import sharif.dagger2experiment.di.GithubAppComponent;
import sharif.dagger2experiment.network.GithubService;
import timber.log.Timber;

/**
 * Created by Sharif-PC on 6/6/2017.
 */

public class MyApplication extends Application {

    private GithubAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        component = DaggerGithubAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public GithubAppComponent getComponent() {
        return component;
    }
}
