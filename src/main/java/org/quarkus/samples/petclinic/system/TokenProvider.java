package org.quarkus.samples.petclinic.system;

import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenProvider {

    public String generateToken(String username) {
        return Jwt.issuer("your-issuer")
                .upn(username)
                .groups("user")
                .sign();
    }
}
