import CoinPackage.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to currency converter");
        ArrayList<Double> results = new ArrayList<>();
        String typeOfCoin ="";
        double valueForResult=0;
        ArrayList<String>resultList = new ArrayList<>();
        Result resultObj = new Result(valueForResult,typeOfCoin);
        ArrayList<LocalDateTime> times = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM/dd/yyyy HH:mm:ss");
        try {
            int userSelection=0;
            while (userSelection!=1&&userSelection!=2&&userSelection!=3) {
                try {
                    userSelection = converterSelection();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Choice, please try again");
                }
            }
            Coin a = CalcType(userSelection);
            int status = 0;
            while (status == 0) {
                double amount=0;
                if (a != null) {
                    try {
                        amount = getConvertAmount();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Choice, please try again");
                        amount = getConvertAmount();
                    }
                    double result = a.calculate(amount);
                    System.out.println(result);
                    results.add(result);
                    times.add(getActionTime());
                    typeOfCoin=resultObj.mergeToString(result,coinType(a));
                    resultList.add((typeOfCoin));
                    String selectDoOver = doAgain();
                    while (!selectDoOver.equalsIgnoreCase("y") && !selectDoOver.equalsIgnoreCase("n")) {
                        System.out.println("Invalid Choice, please try again");
                        selectDoOver = doAgain();
                    }
                    if (selectDoOver.equalsIgnoreCase("y")) {
                        userSelection=0;
                        while (userSelection!=1&&userSelection!=2&&userSelection!=3) {
                            try {
                                userSelection = converterSelection();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Choice, please try again");
                            }
                        }
                        a = CalcType(userSelection);
                    } else if (selectDoOver.equalsIgnoreCase("n")) {
                        System.out.println("Thanks for using our currency converter");
                        System.out.println(results);
                        System.out.println(resultList);
                        for (int i = 0; i < results.size(); i++) {
                            if (i==0){
                                writeToFile("Results.txt", System.lineSeparator());
                                writeToFile("Results.txt", "Session's Results End at: "+ dtf.format(getActionTime()));
                                writeToFile("Results.txt", System.lineSeparator());
                                writeToFile("Results.txt", "There were "+ results.size() + " conversions made");
                                writeToFile("Results.txt", System.lineSeparator());
                                writeToFile("Results.txt", "Result " + (i + 1) + ": ");
                                writeToFile("Results.txt", resultList.get(i));
                                writeToFile("Results.txt", " ");
                                writeToFile("Results.txt", dtf.format(times.get(i)));
                                writeToFile("Results.txt", System.lineSeparator());
                            } else {
                                writeToFile("Results.txt", System.lineSeparator());
                                writeToFile("Results.txt", "Result " + (i + 1) + ": ");
                                writeToFile("Results.txt", resultList.get(i));
                                writeToFile("Results.txt", " ");
                                writeToFile("Results.txt", dtf.format(times.get(i)));
                                writeToFile("Results.txt", System.lineSeparator());
                            }

                        }
                        status = 1;

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static int converterSelection() throws Exception {
        System.out.println("Please choose an option(1/2)");
        System.out.println("1. Dollars to Shekels");
        System.out.println("2. Shekels to Dollars");
        System.out.println("3. Euros to Shekels");
        Scanner scanner = new Scanner(System.in);
        int userSelection = scanner.nextInt();
        return userSelection;
    }//prints screen 1 gets user input (which converter to use) and returns it//

    public static double getConvertAmount() throws Exception {
        System.out.println("Please Enter amount to convert");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        return amount;
    }//prints screen 2 gets user input (amount to convert) and returns it//

    public static Coin CalcType(int userSelection) throws Exception {
        CoinFactory coinFactory = new CoinFactory();
        Coin a = null;
        if (userSelection == 1) {
            a = coinFactory.getCoinType(String.valueOf(CoinsTypes.USD));
            double liveRatio = usdLiveRatio();
            if (liveRatio!=0){
                a.setValue(liveRatio);
            }
            return a;
        } else if (userSelection == 2) {
            a = coinFactory.getCoinType(String.valueOf(CoinsTypes.NIS));
            double liveRatio = nisLiveRatio();
            if (liveRatio!=0){
                a.setValue(liveRatio);
            }
            return a;
        } else if (userSelection == 3) {
            a = coinFactory.getCoinType(String.valueOf(CoinsTypes.EUR));
            double liveRatio = eurLiveRatio();
            if (liveRatio!=0){
                a.setValue(liveRatio);
            }
            return a;
        } else {
            System.out.println("Invalid Choice, please try again");
            try {
                converterSelection();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return a;
    }//gets the user input from screen 1 and build the relevant coin//

    public static String doAgain() throws Exception {
        System.out.println("Start Over? Y/N");
        Scanner scanner = new Scanner(System.in);
        String userSelection = scanner.next();
        return userSelection;

    }//prints screen 3 gets user input(if to use again or close app) and returns it//

    public static void writeToFile(String fileName, String content) throws IOException {
        File myObj = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(content);
        writer.close();

    }//gets the desired file name and content and creates/appends to existing file//

    public static String coinType(Coin coin) throws IOException {
        String typeOfCoin="";
        if (coin.getValue()==usdLiveRatio()){
            typeOfCoin = " USD to NIS";
        } else if (coin.getValue()==0.28) {
            typeOfCoin = " NIS to USD";
        } else if (coin.getValue()==4.23) {
            typeOfCoin= " EUR to NIS";
        }
        return typeOfCoin;
    } // gets coin type and return a string for the results file//

    public static double usdLiveRatio() throws IOException {
        //https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx
        double newValue=0;
        URL url = new URL("https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();
        if (code==200) {
            StringBuilder infoString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                infoString.append(scanner.nextLine());
            }
            scanner.close();
            int anchor = infoString.indexOf("ILS");
            newValue = Double.parseDouble(infoString.substring(anchor + 5, anchor + 13));

        }
        return newValue;
    }// get the USD to shekel ratio and returns it//

    public static LocalDateTime getActionTime(){
        LocalDateTime now = LocalDateTime.now();
        return now;
    } //get current time//

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
            int anchor = infoString.indexOf("ILS");
            newValue = 1 / Double.parseDouble(infoString.substring(anchor + 5, anchor + 13));

            DecimalFormat df = new DecimalFormat("#.######");
            df.setRoundingMode(RoundingMode.CEILING);
            newValue = Double.parseDouble((df.format(newValue)));
        }
        return newValue;
    }// get the USD to shekel ratio calculate the reverse ratio and returns it//

    public static double eurLiveRatio() throws IOException {
        //https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx
        double newValue=0;
        URL url = new URL("https://api.freecurrencyapi.com/v1/latest?apikey=N4fMpJz1St09zCtMcZ1qJBWVwe4zMWmipNCTCvqx");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();
        if (code==200) {
            StringBuilder infoString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                infoString.append(scanner.nextLine());
            }
            scanner.close();
            int anchor = infoString.indexOf("EUR");
            newValue = (1/Double.parseDouble(infoString.substring(anchor + 5, anchor + 13)))*usdLiveRatio();

        }
        return newValue;
    }// get the EUR to shekel ratio and returns it//
}