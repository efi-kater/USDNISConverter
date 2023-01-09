package CoinPackage;

public class EUR extends Coin{
    private final double value = 4.23;
    @Override
    public double getValue() {
        return this.value;
    }//gets the conversion ratio//

    @Override
    public void setValue(double value) {

    }

    @Override
    public double calculate(double number) {
        double result = number*getValue();
        return result;
    }//calculates the amount multiply by the conversion ratio//
}
