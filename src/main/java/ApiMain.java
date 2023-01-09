import CoinPackage.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiMain {
    public static void main(String[] args)  {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ArrayList<LocalDateTime> times = new ArrayList<>();
        times.add(now);
        System.out.println(dtf.format(times.get(0)));
        String a = "Result 1: 227.95480500000002 USD to NIS 01/08/2023 15:58:46";
        System.out.println(a.length());


        }
}
