package dk.lbloft.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthConfiguration extends Configuration {

    @JsonProperty
    List<User> users;
}
