package sharif.dagger2experiment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sharif.dagger2experiment.di.GithubAppComponent;
import sharif.dagger2experiment.di.home.DaggerHomeActivityComponent;
import sharif.dagger2experiment.di.home.HomeActivityComponent;
import sharif.dagger2experiment.di.home.HomeActivityModule;
import sharif.dagger2experiment.model.GithubRepo;
import sharif.dagger2experiment.network.GithubService;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    Call<List<GithubRepo>> reposCall;

    @Inject
    GithubService githubService;

    @Inject
    AdapterRepos adapterRepos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listView = (ListView) findViewById(R.id.repo_home_list);

        MyApplication application = (MyApplication) getApplication();

        HomeActivityComponent component = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .githubAppComponent(application.getComponent())
                .build();

        component.injectHomeActivity(this);

        listView.setAdapter(adapterRepos);
        reposCall = githubService.getAllRepos();

        reposCall.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                adapterRepos.swapData(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error in getting response " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (reposCall != null){
            reposCall.cancel();
        }
    }
}
