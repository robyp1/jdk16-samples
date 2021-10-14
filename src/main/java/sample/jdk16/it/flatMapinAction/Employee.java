package sample.jdk16.it.flatMapinAction;

import java.util.Optional;

public class Employee {
    private Car2 car;

    public Optional<Car2> getCar() {
        return Optional.ofNullable(car); ////ofNullable perchè  un employee può non avere una macchina (essere car = null)
    }
}

