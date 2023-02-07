package services.priceInWords;

public enum Currency {
    // "рубль", "рубля", "рублей"
    // "доллар", "доллара", "долларов"
    RUB(new String[]{"рубль", "рубля", "рублей"}){
    },
    USD(new String[]{"доллар", "доллара", "долларов"}){
    };

    private final String[] endings;

    Currency(String[] endings){
        this.endings = endings;
    }
    public String getCurrencyEnding (int i){
        return endings[i];
    }
}
