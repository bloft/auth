package dk.lbloft.auth;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class AuthApplication extends Application<AuthConfiguration> {
    public static void main(String[] args) throws Exception {
        new AuthApplication().run(args);
    }

    @Override
    public String getName() {
        return "Auth-Service";
    }

    @Override
    public void initialize(Bootstrap<AuthConfiguration> bootstrap) {
    }

    @Override
    public void run(AuthConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(AuthResource.class);

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new BasicAuthenticator(configuration))
                .setAuthorizer(new MyAuthorizer())
                .setRealm("MyAuth")
                .buildAuthFilter()
        ));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        System.out.println(configuration.users);
    }
}
