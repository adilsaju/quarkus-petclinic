package org.quarkus.samples.petclinic.system;

import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import io.quarkus.qute.TemplateInstance;

@Path("/")
public class WelcomeResource {
    
    @Inject
    TemplatesLocale templates;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return templates.welcome();
    }
    // @GET
    // @Produces(MediaType.TEXT_HTML)
    // public Response redirectToLoginPage() {
    //     return Response.seeOther(UriBuilder.fromPath("/auth/login-page").build()).build();
    // }

}
