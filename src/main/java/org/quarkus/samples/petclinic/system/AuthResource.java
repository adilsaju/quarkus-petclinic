package org.quarkus.samples.petclinic.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.quarkus.samples.petclinic.owner.User;

import com.fasterxml.jackson.annotation.JsonProperty;

@Path("/auth")
public class AuthResource {

    @Inject
    EntityManager em;


    @POST
    @Transactional
    @Path("/login")
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)


    // public Response login(LoginRequest request) {
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {

        User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
            .setParameter("email", email)
            .getSingleResult();

        if (user != null && password.equals(user.getPassword()) ) {
            // return "Authentication successful";
            return Response.seeOther(UriBuilder.fromPath("/").build()).build();

        } else {
            // return "Authentication failed";
            return Response.ok(getClass().getClassLoader().getResourceAsStream("META-INF/resources/login.html"))
            .entity("Authentication failed. Please try again.")
            .build(); 
        }
    }

//
    @GET
    @Path("/login-page")
    @Produces(MediaType.TEXT_HTML)
    public Response loginPage() throws FileNotFoundException {
        System.out.println("");
        // return Response.ok(new FileInputStream("./resources/login.html")).build();
        return Response.ok(getClass().getClassLoader().getResourceAsStream("META-INF/resources/login.html")).build();

    }

    @GET
    @Path("/register-page")
    @Produces(MediaType.TEXT_HTML)
    public Response registerPage() throws FileNotFoundException {
        return Response.ok(new FileInputStream("META-INF/resources/register.html")).build();
    }

    @GET
    @Path("/")
    public Response redirectToLoginPage() {
        return Response.seeOther(UriBuilder.fromPath("/auth/login-page").build()).build();
    }

}

// Define the LoginRequest class outside the AuthResource class
class LoginRequest {
    private String email;
    private String password;

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
}