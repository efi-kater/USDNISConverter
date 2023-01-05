import CoinPackage.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to currency converter");
        ArrayList<Double> results = new ArrayList<Double>();
        try {
            int userSelection;
            try {
                userSelection=converterSelection();
            }catch (InputMismatchException e){
                System.out.println("Invalid Choice, please try again");
                userSelection=converterSelection();
            }
            Coin a = CalcType(userSelection);
            int status =0;
            while (status==0){
                double amount;
                if (a!=null){
                    try {
                       amount =getConvertAmount();
                    } catch (InputMismatchException e){
                        System.out.println("Invalid Choice, please try again");
                        amount=getConvertAmount();
                    }
                    double result =a.calculate(amount);
                    System.out.println(result);
                    results.add(result);
                    String selectDoOver = doAgain();
                    while (!selectDoOver.equalsIgnoreCase("y")&&!selectDoOver.equalsIgnoreCase("n")){
                        System.out.println("Invalid Choice, please try again");
                        selectDoOver=doAgain();
                    }
                    if (selectDoOver.equalsIgnoreCase("y")){
                        userSelection =converterSelection();
                        a = CalcType(userSelection);
                    } else if (selectDoOver.equalsIgnoreCase("n")) {
                        System.out.println("Thanks for using our currency converter");
                        System.out.println(results);
                        for (int i=0;i<results.size();i++){
                            writeToFile("Results.txt","Result "+(i+1)+": ");
                            writeToFile("Results.txt",results.get(i).toString());
                            writeToFile("Results.txt",System.lineSeparator());
                        }
                        status=1;

                    }
                 }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    public static int converterSelection() throws Exception{
        System.out.println("Please choose an option(1/2)");
        System.out.println("1. Dollars to Shekels");
        System.out.println("2. Shekels to Dollars");
        Scanner scanner = new Scanner(System.in);
        int userSelection = scanner.nextInt();
        return userSelection;
    }//prints screen 1 gets user input (which converter to use) and returns it//
    public static double getConvertAmount()throws Exception{
        System.out.println("Please Enter amount to convert");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        return amount;
    }//prints screen 2 gets user input (amount to convert) and returns it//

    public static Coin CalcType(int userSelection) throws Exception {
        CoinFactory coinFactory = new CoinFactory();
        Coin a=null;
        if (userSelection==1){
            a = coinFactory.getCoinType(String.valueOf(CoinsTypes.USD));
            return a;
        } else if (userSelection==2) {
            a = coinFactory.getCoinType(String.valueOf(CoinsTypes.NIS));
            return a;
        }else {
            System.out.println("Invalid Choice, please try again");
            try {
                converterSelection();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return a;
    }//gets the user input from screen 1 and build the relevant coin//

    public static String doAgain() throws Exception{
        System.out.println("Start Over? Y/N");
        Scanner scanner = new Scanner(System.in);
        String userSelection = scanner.next();
        return userSelection;

    }//prints screen 3 gets user input(if to use again or close app) and returns it//

    public static void writeToFile (String fileName, String content) throws IOException {
        File myObj = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(content);
        writer.close();

    }//gets the desired file name and content and creates/appends to existing file//

}
