package sharif.dagger2experiment.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sharif.dagger2experiment.model.GithubRepo;
import sharif.dagger2experiment.model.GithubUser;

/**
 * Created by Sharif-PC on 6/6/2017.
 */

public interface GithubService {

    @GET("users/{username}/repos")
    Call<List<GithubRepo>> getReposForUser(@Path("username") String username);

    @GET("repositories")
    Call<List<GithubRepo>> getAllRepos();

    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);

}
