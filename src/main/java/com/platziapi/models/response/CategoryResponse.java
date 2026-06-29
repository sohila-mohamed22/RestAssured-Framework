package com.platziapi.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private int id ;
    private String name ;
    private String image ;
    private String slug ;
    private String creationAt;
    private String updatedAt;
}
