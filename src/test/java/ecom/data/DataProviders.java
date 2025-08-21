package ecom.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;


public class DataProviders {  

    @DataProvider(name = "getData") 
    public Object[][] getData() throws IOException {
        // Read JSON dynamically using DataReader
        DataReader reader = new DataReader();
        List<HashMap<String, String>> data = reader.getJsonDataToMap("src/test/java/ecom/data/PurchaseOrder.json");

        // Dynamically fill Object[][]
        Object[][] returnData = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            returnData[i][0] = data.get(i);
        }
        return returnData;
    }
    
    @DataProvider(name = "registrationData")
    public Object[][] registrationData() throws IOException {
        // Read JSON dynamically using DataReader
        DataReader reader = new DataReader();
        List<HashMap<String, String>> data = reader.getJsonDataToMap("src/test/java/ecom/data/RegistrationTestData.json");

        // Dynamically fill Object[][]
        Object[][] returnData = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            returnData[i][0] = data.get(i);
        }
        return returnData;
    }
}
