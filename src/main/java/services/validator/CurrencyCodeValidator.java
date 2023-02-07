package services.validator;

import services.priceInWords.Currency;

public class CurrencyCodeValidator implements Validator <String> {
        @Override
        public boolean check(String value) {
            int count = 0;
            for (int i = 0; i < Currency.values().length; i++){
                if(value.equals(String.valueOf(Currency.values()[i]))){
                    count += 1;
                }
            }
            return count != 0;
        }
        @Override
        public String errMesage(String value) {
            return "Ошибка_3. Введенный код валюты НЕ найден.";
        }
}
