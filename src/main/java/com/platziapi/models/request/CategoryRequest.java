package com.platziapi.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {
    private String name;
    private String image;
}
