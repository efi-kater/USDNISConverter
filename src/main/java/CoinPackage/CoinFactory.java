package CoinPackage;

public class CoinFactory {
    public Coin getCoinType(String coinType){
        if (coinType==null){
            return null;
        }
        if(coinType.equalsIgnoreCase(String.valueOf(CoinsTypes.NIS))){
            return new NIS();
        }
        if (coinType.equalsIgnoreCase(String.valueOf(CoinsTypes.USD))){
            return new USD();
        }
        return null;
    }
}

