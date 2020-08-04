package dk.lbloft.auth;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @GET
    @PermitAll
    public String basicAuth() {
        return "basic";
    }

    @GET
    @Path("role/user")
    @RolesAllowed("USER")
    public String roleUser() {
        return "role";
    }

    @GET
    @Path("role/admin")
    @RolesAllowed("ADMIN")
    public String roleAdmin() {
        return "role";
    }
}
