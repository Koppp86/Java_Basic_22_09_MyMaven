package services.priceInWords;

import com.ibm.icu.text.RuleBasedNumberFormat;
import java.util.Locale;

public class PriceInWordsImpl implements PriceInWords{
    @Override
    public String transformation(int amount, Currency currency) {
        // Подключил Библиотеку icu4j
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);

        int preLastDigit = Math.abs (amount) % 100 / 10;
        if (preLastDigit == 1)
        {
            return nf.format(amount) + " " + currency.getCurrencyEnding(2);
        }

        return switch (Math.abs (amount) % 10) {
            case 1 -> nf.format(amount) + " " + currency.getCurrencyEnding(0);
            case 2, 3, 4 -> nf.format(amount) + " " + currency.getCurrencyEnding(1);
            default -> nf.format(amount) + " " + currency.getCurrencyEnding(2);
        };
    }
}
