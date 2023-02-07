import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import services.io.IOConsoleService;
import services.io.IOStreamsService;
import services.priceInWords.Currency;
import services.priceInWords.PriceInWordsImpl;
import services.validator.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;


public class Test {
    // 1) priceInWords (Число + Валюта --> Прописью)
    @ParameterizedTest
    @CsvSource({"1, 'RUB', 'один рубль'", "3, 'RUB', 'три рубля'", "7, 'RUB', 'семь рублей'",
                "11, 'RUB', 'одиннадцать рублей'", "111, 'RUB', 'сто одиннадцать рублей'",
                "1, 'USD', 'один доллар'", "3, 'USD', 'три доллара'", "7, 'USD', 'семь долларов'",
                "11, 'USD', 'одиннадцать долларов'" , "111, 'USD', 'сто одиннадцать долларов'"})
    void priceInWordsTest(int amount, String currency, String expected){
        PriceInWordsImpl priceInWords = new PriceInWordsImpl();
        Currency instanceCurrency = Currency.valueOf(currency);
        Assertions.assertEquals(priceInWords.transformation(amount, instanceCurrency), expected);
    }

    // 2) Валидатор (3 части)
    @ParameterizedTest
    @CsvSource({"'1 RUB', 'true'", "'1 f', 'true'", "'1 RUB UDS', 'false'", "'1', 'false'", "'', 'false'", "' ', 'false'", "'1   ', 'false'", "'1   f', 'false'"})
    void TwoPartStringValidatorsTest(String inputString, boolean expected){
        Validator[] twoPartStringValidators = new Validator[]{new TwoPartStringValidator()};
        for (Validator validator: twoPartStringValidators){
            boolean actual = validator.check(inputString);
            Assertions.assertEquals(actual, expected);
        }
    }
    @ParameterizedTest
    @CsvSource({"1, 'true'", "1000, 'true'", "0, 'false'", "1001, 'false'"})
    void amountArrayValidatorsTest(int inputString, boolean expected){
        Validator[] amountArrayValidators = new Validator[]{new MinValueValidator(1), new MaxValueValidator(1000)};
        for (Validator validator: amountArrayValidators){
            if (validator instanceof MinValueValidator && inputString < 1) {
                boolean actual = validator.check(inputString);
                Assertions.assertEquals(actual, expected);
            }
            if (validator instanceof MaxValueValidator && inputString > 1000) {
                boolean actual = validator.check(inputString);
                Assertions.assertEquals(actual, expected);
            }
        }
    }
    @ParameterizedTest
    @CsvSource({"'RUB', 'true'", "'USD', 'true'", "'rub', 'false'", "'usd', 'false'", "'', 'false'", "' ', 'false'", "'f', 'false'"})
    void curruncyArrayValidatorsTest(String inputString, boolean expected){
        Validator[] curruncyArrayValidators = new Validator[]{new CurrencyCodeValidator()};
        for (Validator validator: curruncyArrayValidators){
            boolean actual = validator.check(inputString);
            Assertions.assertEquals(actual, expected);
        }
    }

    // 3) Ввод-Вывод
    /*@ParameterizedTest
    @CsvSource({"'RUB', 'RUB'"})
    void IoServiceTest(String inputString, boolean expected){
        IOConsoleService ioService = new IOStreamsService(System.out, System.in);
        System.setIn(new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8)));
        String actual = ioService.inputString();
        Assertions.assertEquals(actual, expected);
    }*/
}
