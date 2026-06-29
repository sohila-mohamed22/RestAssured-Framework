package com.platziapi.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    // Valid Response Fields
    private int id;
    private String title ;
    private String slug ;
    private int price ;
    private String description ;
    private CategoryResponse category ;
    private List<String> images ;
    private String creationAt;
    private String updatedAt;

    // Invalid Response Fields
    private String path;
    private String timestamp;
    private String name;
    private String message;
    private String code;
}
