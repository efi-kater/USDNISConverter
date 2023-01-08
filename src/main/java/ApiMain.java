import CoinPackage.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiMain {
    public static void main(String[] args) throws IOException {
        //https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx

        URL url = new URL("https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();
        if (code==200){
            StringBuilder infoString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()){
                infoString.append(scanner.nextLine());
            }
            scanner.close();
            int anchor = infoString.indexOf("ILS");
            Double newValue = Double.parseDouble(infoString.substring(anchor+5,anchor+13));

//            System.out.println(infoString);
//            System.out.println(infoString.indexOf("ILS"));
//            System.out.println(infoString.substring(230,238));





        }


    }
}
