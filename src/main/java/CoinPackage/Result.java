package CoinPackage;


import java.util.HashMap;
import java.util.Map;

public class Result {

    private double resultValue=0;
    private String type="";


    public Result (Double value, String type){
        this.resultValue=value;
        this.type=type;
    }

    public String mergeToString(Double value, String string){
        String mergedString = value+string;
        return mergedString;
    }


}
