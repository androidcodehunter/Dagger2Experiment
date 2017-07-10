package sharif.dagger2experiment.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by User on 7/9/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface GithubServiceScope {
}
