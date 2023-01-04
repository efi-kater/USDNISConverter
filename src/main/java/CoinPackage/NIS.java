package CoinPackage;

import java.util.Scanner;

public class NIS extends Coin{
    private final double value = 0.28;
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
