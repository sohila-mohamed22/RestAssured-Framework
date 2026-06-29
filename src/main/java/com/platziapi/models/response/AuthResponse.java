package com.platziapi.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private String access_token;
    private String refresh_token;

    // User details
    private String id ;
    private String email ;
    private String password ;
    private String name ;
    private String role ;
    private String avatar ;
    private String creationAt ;
    private String updatedAt ;

    // Valid response body
    private String message;
    private String statusCode;

}
