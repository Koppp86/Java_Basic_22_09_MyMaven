package services.validator;

public class TwoPartStringValidator implements Validator <String>{
    @Override
    public boolean check(String value){
        String[] parametrs = value.split(" ");
        int countSpace = parametrs.length;
        return (value.contains(" ") && countSpace == 2);
    }
    @Override
    public String errMesage(String value) {
        return "Ошибка_1. Отсутствуют пробелы (Или пробелов больше чем нужно).";
    }
}
