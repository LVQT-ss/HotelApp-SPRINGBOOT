package com.thinhle.lakesidehotel.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JwtResponse {
    private Long id;
    private String email;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse(Long id, String email, String token, String type, List<String> roles) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.type = type;
        this.roles = roles;
    }
}
