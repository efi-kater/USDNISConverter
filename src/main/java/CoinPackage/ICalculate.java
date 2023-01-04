package CoinPackage;

public interface ICalculate {
     default double calculate(double number) throws Exception {
        return 0;
    };
}
