package House;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class HouseReader {

    public static Map<String,House> getHouses() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file into a Map
            return objectMapper.readValue(
                    new File("src/main/java/House/house.json"),
                    objectMapper.getTypeFactory().constructMapType(Map.class, String.class, House.class)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
