package sharif.dagger2experiment.di.home;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import sharif.dagger2experiment.AdapterRepos;
import sharif.dagger2experiment.MainActivity;

/**
 * Created by User on 7/9/2017.
 */

@Module
public class HomeActivityModule {

    private final MainActivity activity;

    public HomeActivityModule(MainActivity mainActivity){
        activity = mainActivity;
    }

    @Provides
    @HomeActivityScope
    public AdapterRepos adapterRepos(Picasso picasso){
        return new AdapterRepos(activity, picasso);
    }
}
