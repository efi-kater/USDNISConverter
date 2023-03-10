import CoinPackage.Coin;
import CoinPackage.CoinFactory;
import CoinPackage.CoinsTypes;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Scanner;

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
    public void TestEURCalculate() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        double number =1;
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.EUR));
        //Act
        double result =coin.calculate(number);
        //assert
        Assert.assertEquals(result,4.23);

    }

    @Test
    public void TestNISGetValue() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.NIS));
        //Act
        double result =coin.getValue();
        //assert
        Assert.assertEquals(result,0.28);

    }

    @Test
    public void TestUSDGetValue() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.USD));
        //Act
        double result =coin.getValue();
        //assert
        Assert.assertEquals(result,3.52);

    }

    @Test
    public void TestEURGetValue() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.EUR));
        //Act
        double result =coin.getValue();
        //assert
        Assert.assertEquals(result,4.23);

    }

    @Test
    public void TestFileAfterWrite() throws Exception {
        //setup
        CoinFactory c = new CoinFactory();
        Coin coin =c.getCoinType(String.valueOf(CoinsTypes.USD));
        String fileName = "test.txt";
        double result =coin.calculate(10.0);
        //Act
        Main.writeToFile(fileName,String.valueOf(result));
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        //assert
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine().substring(0,4);
            Assert.assertEquals(data,"35.2");

        }
    }
}
