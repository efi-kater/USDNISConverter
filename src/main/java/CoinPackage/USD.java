package CoinPackage;


public class USD extends Coin {
    private double value = 3.52;
    @Override
    public double getValue() {
        return this.value;
    }//gets the conversion ratio//

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double calculate(double number) {
        double result = number*getValue();
        return result;
    }//calculates the amount multiply by the conversion ratio//
}
