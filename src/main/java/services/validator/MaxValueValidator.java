package services.validator;

public class MaxValueValidator implements Validator <Integer> {
    private final Integer maxValue;
    public MaxValueValidator(Integer maxValue){
        this.maxValue = maxValue;
    }

    @Override
    public boolean check(Integer value) {
        return (value <= maxValue);
    }
    @Override
    public String errMesage(Integer value) {
        return "Ошибка_2.2. Введенное Число \"" + value + "\" БОЛЬШЕ Максимально допустимого Значения \"" + maxValue + "\".";
    }
}
