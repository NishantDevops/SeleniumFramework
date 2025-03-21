package frameworkDesign.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {
	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException {

		// 1. Read json file data to String
		String jsonData = Files.readString(
				Paths.get(System.getProperty("user.dir") + "//src//test//java//frameworkDesign//data//testData.json"));
		// System.out.println("JSON Data is :"+jsonData);

		// 2. Convert String data to HashMap using dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<>() {
		});

		return data;

	}

}
