package sharif.dagger2experiment.di.home;

import com.squareup.picasso.Picasso;

import dagger.Component;
import dagger.Provides;
import sharif.dagger2experiment.AdapterRepos;
import sharif.dagger2experiment.MainActivity;
import sharif.dagger2experiment.di.GithubAppComponent;
import sharif.dagger2experiment.network.GithubService;

/**
 * Created by User on 7/9/2017.
 */

@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = GithubAppComponent.class)
public interface HomeActivityComponent {

    void injectHomeActivity(MainActivity mainActivity);
}
