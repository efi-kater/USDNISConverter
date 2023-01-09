package CoinPackage;

public abstract class Coin implements ICalculate{
    public abstract double getValue();
    public abstract void setValue(double value);

    @Override
    public double calculate(double number) throws Exception {
        return 0;
    }
}
