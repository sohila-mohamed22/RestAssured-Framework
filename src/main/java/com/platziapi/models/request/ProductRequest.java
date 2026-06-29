package com.platziapi.models.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductRequest {
    private String title ;
    private int price ;
    private String description ;
    private int categoryId ;
    private List<String> images ;
}
