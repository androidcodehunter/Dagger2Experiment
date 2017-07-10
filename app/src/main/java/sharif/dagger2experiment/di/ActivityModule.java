package sharif.dagger2experiment.di;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 7/9/2017.
 */

@Module
public class ActivityModule {
    private final Activity context;

    public ActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @GithubServiceScope
    @Named("activity_context")
    public Context context() {
        return context;
    }

}
