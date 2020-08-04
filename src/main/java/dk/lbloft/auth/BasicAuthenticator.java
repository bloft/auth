package dk.lbloft.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {
    private final AuthConfiguration configuration;

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        log.info("Auth: " + credentials.getUsername());
        User user = findUser(credentials.getUsername());
        if(user != null) {
            String hash = getPasswordHash(credentials.getPassword(), user.getSalt());
            if(user.getHash().equals(hash)) {
                return Optional.of(user);
            } else {
                log.info("Invalid hash: " + hash);
            }
        } else {
            log.info("Unknown user: " + credentials.getUsername());
        }
        return Optional.empty();
    }

    @SneakyThrows
    private String getPasswordHash(String password, String salt) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((salt + password).getBytes(StandardCharsets.UTF_8));

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private User findUser(String username) {
        for (User user : configuration.getUsers()) {
            if(user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
