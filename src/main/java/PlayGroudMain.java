import java.io.IOException;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PlayGroudMain {
    public static void main(String[] args) throws IOException {

        nisLiveRatio();

    }

    public static double nisLiveRatio() throws IOException {
        //https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx
        double newValue = 0;
        URL url = new URL("https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();
        if (code == 200) {
            StringBuilder infoString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                infoString.append(scanner.nextLine());
            }
            scanner.close();
            int anchor = infoString.indexOf("EUR");
            newValue = 1 / Double.parseDouble(infoString.substring(anchor + 5, anchor + 13));

            DecimalFormat df = new DecimalFormat("#.######");
            df.setRoundingMode(RoundingMode.CEILING);
            newValue = Double.parseDouble((df.format(newValue)));
            System.out.println(newValue);

        }
        return newValue;
    }
}
