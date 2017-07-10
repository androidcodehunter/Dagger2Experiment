package sharif.dagger2experiment.di;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import dagger.Component;
import sharif.dagger2experiment.network.GithubService;

/**
 * Created by User on 7/8/2017.
 */
@GithubServiceScope
@Component(modules = {GithubServiceModule.class, PicassoModule.class})
public interface GithubAppComponent {

    public Picasso getPicasso();

    public GithubService getGithubService();
}
