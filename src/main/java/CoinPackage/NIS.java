package CoinPackage;


public class NIS extends Coin{
    private double value = 0.28;
    @Override
    public double getValue() {
        return this.value;
    }//gets the conversion ratio//

    @Override
    public void setValue(double value) {
        this.value=value;
    }

    @Override
    public double calculate(double number) {
        double result = number*getValue();
        return result;
    }//calculates the amount multiply by the conversion ratio//
}
