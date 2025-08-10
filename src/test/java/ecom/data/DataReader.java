package ecom.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

    /**
     * Reads a JSON file and returns it as a List of HashMaps.
     * @param relativeFilePath Path to the JSON file relative to project root
     * @return List of HashMaps containing the data
     */
    public List<HashMap<String, String>> getJsonDataToMap(String relativeFilePath) throws IOException {
        // Build full path dynamically
        String fullPath = System.getProperty("user.dir") + File.separator + relativeFilePath;

        // Read JSON file content into a string
        String jsonContent = FileUtils.readFileToString(new File(fullPath), StandardCharsets.UTF_8);

        // Convert JSON string into List<HashMap<String, String>>
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }
}
