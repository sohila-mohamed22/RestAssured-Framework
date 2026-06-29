package com.platziapi.utils.helper;

import java.util.HashMap;
import java.util.Map;

public class QueryParams {

    public static HashMap<String, String> createQueryParams(String... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Parameters must be provided as key-value pairs");
        }

        HashMap<String, String> queryParams = new HashMap<>();

        for (int i = 0; i < params.length; i += 2) {
            queryParams.put(params[i], params[i + 1]);
        }

        return queryParams;
    }
}
