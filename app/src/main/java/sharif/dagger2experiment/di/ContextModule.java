package sharif.dagger2experiment.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 7/8/2017.
 */

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context){
        this.context = context.getApplicationContext();
    }

    @Provides
    @GithubServiceScope
    @Named("app_context")
    public Context context(){
        return context;
    }

}
