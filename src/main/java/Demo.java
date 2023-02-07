import services.application.PriceInWordsApplication;
import services.io.IOConsoleService;
import services.io.IOStreamsService;
import services.priceInWords.PriceInWordsImpl;
import services.validator.*;

public class Demo {
    public static void main(String[] args) {
        IOConsoleService ioService = new IOStreamsService(System.out, System.in);
        PriceInWordsImpl priceInWords = new PriceInWordsImpl();
        Validator[] twoPartStringValidators = new Validator[]{new TwoPartStringValidator()};    // Переработать на списки (Дженерики)   - не понял...
        final Integer minValue = 1;
        final Integer maxValue = 1000;
        Validator[] amountArrayValidators = new Validator[]{new MinValueValidator(minValue), new MaxValueValidator(maxValue)};
        Validator[] curruncyArrayValidators = new Validator[]{new CurrencyCodeValidator()};
        PriceInWordsApplication application = new PriceInWordsApplication(ioService, priceInWords, twoPartStringValidators, amountArrayValidators, curruncyArrayValidators);
        application.run();
    }
}
