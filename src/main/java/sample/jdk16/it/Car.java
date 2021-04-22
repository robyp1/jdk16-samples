package sample.jdk16.it;


public record Car(String manufacturId, String model, String modelType) {


    public Car {
        if (manufacturId == null && model == null){
            throw new IllegalArgumentException("invalid manufacturId and model!");
        }
    }
}
