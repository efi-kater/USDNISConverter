package CoinPackage;

import java.util.Scanner;

public class USD extends Coin {
    private final double value = 3.52;
    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public double calculate(double number) {
        double result = number*getValue();
        return result;
    }
}
