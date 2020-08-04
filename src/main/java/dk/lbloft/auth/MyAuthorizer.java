package dk.lbloft.auth;

import io.dropwizard.auth.Authorizer;
import lombok.extern.java.Log;

@Log
public class MyAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        log.info("User: " + user);
        log.info("Role: " + role);
        log.info("Auth: " + (user.getRoles().contains(role) ? "true" : "false"));
        return user.getRoles().contains(role);
    }
}
