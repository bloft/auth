package dk.lbloft.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Principal {
    private String name;
    private String salt;
    private String hash;
    private Set<String> roles;

    @Override
    public String getName() {
        return name;
    }
}
