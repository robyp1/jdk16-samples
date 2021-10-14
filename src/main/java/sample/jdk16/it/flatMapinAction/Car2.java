package sample.jdk16.it.flatMapinAction;

import java.util.Optional;

public class Car2 {

        private Insurance insurance;

        public Optional< Insurance > getInsurance() {
            return Optional.ofNullable(insurance); //ofNullable perchè una macchina può non avere una assicurazione (essere insurance = null)
        }
}
