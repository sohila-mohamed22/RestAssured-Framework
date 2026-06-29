package com.platziapi.utils.datamanager;

// Library used to query JSON using path expressions (like XPath but for JSON)

import com.jayway.jsonpath.JsonPath;
import com.platziapi.utils.manager.LogsManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {

    // Base folder where all test JSON files are stored
    private final String TEST_DATA_PATH = "src/test/resources/test-data/";

    // Holds full JSON content as a STRING after reading file
    String jsonReader;

    // Stores file name passed from constructor (without .json extension)
    String jsonFileName;

    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;

        try {
            // Step 1: Read JSON file from path
            JSONObject data =
                    (JSONObject) new JSONParser()
                            .parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));

            // Step 2: Convert JSON object to string (used by JsonPath later)
            jsonReader = data.toJSONString();

        } catch(Exception e) {
            // If file not found or invalid JSON format
            LogsManager.error("Error reading JSON file:" , e.getMessage());
            jsonReader = "{}" ;
        }
    }

    public String getJsonData(String jsonPath){
        try {
            // Reads value from JSON string using JsonPath query
            return JsonPath.read(jsonReader, jsonPath);

        } catch(Exception e) {
            // If path is invalid or key doesn't exist
            LogsManager.error("Error extracting data from JSON: " + e.getMessage());
            return "";
        }
    }
}

