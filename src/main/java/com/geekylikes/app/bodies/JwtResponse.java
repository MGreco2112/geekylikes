package com.geekylikes.app.bodies;

public class JwtResponse {
    private final String jwtoken;

    public JwtResponse(String jwtoken) {
        this.jwtoken = jwtoken;
    }

    public String getJwtoken() {
        return jwtoken;
    }
}
