import CoinPackage.Coin;
import CoinPackage.CoinFactory;
import CoinPackage.CoinsTypes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CoverterTest {
    @Test
    public void TestNISCalculate() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        double number =1;
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.NIS));
        //Act
        double result =coin.calculate(number);
        //assert
        Assert.assertEquals(result,0.28);

    }

    @Test
    public void TestUSDCalculate() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        double number =10;
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.USD));
        //Act
        double result =coin.calculate(number);
        //assert
        Assert.assertEquals(result,35.2);

    }

    @Test
    public void TestNISCGetValue() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.NIS));
        //Act
        double result =coin.getValue();
        //assert
        Assert.assertEquals(result,0.28);

    }

    @Test
    public void TestUSDCGetValue() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.USD));
        //Act
        double result =coin.getValue();
        //assert
        Assert.assertEquals(result,3.52);

    }
}
