package services.application;

import services.io.IOConsoleService;
import services.priceInWords.Currency;
import services.priceInWords.PriceInWords;
import services.validator.Validator;

public class PriceInWordsApplication implements Application {
    private final IOConsoleService ioService;
    private final PriceInWords priceInWords;
    private final Validator[] twoPartStringValidators;
    private final Validator[] amountArrayValidators;
    private final Validator[] currunctArrayValidators;
    // Метод для проверки - нужно ли применять Валидатор

    public PriceInWordsApplication(IOConsoleService ioService, PriceInWords priceInWords, Validator[] twoPartStringValidators, Validator[] amountArrayValidators, Validator[] currunctArrayValidators) {
        this.ioService = ioService;
        this.priceInWords = priceInWords;
        this.twoPartStringValidators = twoPartStringValidators;
        this.amountArrayValidators = amountArrayValidators;
        this.currunctArrayValidators = currunctArrayValidators;
    }
    @Override
    public void run(){
        while (true) {
            ioService.outputString("Введите <X Y> или <exit>, где X = Целое Число, а Y = Код валюты (RUB или USD)");
            String command = ioService.inputString();

            if (command.equals("exit")) {
                return;
            } else {
                boolean checkRezult = true;

                for (Validator validator: twoPartStringValidators){
                    if (!validator.check(command)){
                        checkRezult = false;
                        ioService.outputString((validator.errMesage("")));
                        break;
                    }
                }
                if (!checkRezult){
                    continue;
                }

                String[] parametrs = command.split(" ");
                int amount = Integer.parseInt(parametrs[0]);

                for (Validator validator: amountArrayValidators){
                    if (!validator.check(amount)){
                        checkRezult = false;
                        ioService.outputString((validator.errMesage(amount)));
                        break;
                    }
                }
                if (!checkRezult){
                    continue;
                }

                String currencyCode = parametrs[1];
                for (Validator validator: currunctArrayValidators){
                    if (!validator.check(currencyCode)){
                        checkRezult = false;
                        ioService.outputString((validator.errMesage("")));
                        break;
                    }
                }
                if (!checkRezult){
                    continue;
                }

                Currency instance = Currency.valueOf(currencyCode);
                String result = priceInWords.transformation(amount, instance);
                ioService.outputString("%d %s = %s", amount, instance, result);
            }
        }
    }
}