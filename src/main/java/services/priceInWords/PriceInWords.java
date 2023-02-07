package services.priceInWords;

public interface PriceInWords {
    String transformation(int amount, Currency currency);
}
