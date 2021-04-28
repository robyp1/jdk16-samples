package sample.jdk16.it.cars;

import java.util.*;
import java.util.stream.Collectors;

public class Cars {

    public List<Car> findCarTypeByCosts(List<Car> allCars){
       return  allCars.stream()
                .map(car -> new CarCosts(car, getCost(car.manufacturId(), car.modelType())))
                .filter(carCosts -> Objects.nonNull(carCosts.costs()) )
                .sorted(Comparator.comparingDouble(CarCosts::costs))
                .map(CarCosts::car)
                .collect(Collectors.toList());
    }

    private double getCost(String manufacturId, String modelType) {
        record Search<String>(String ...search){
            @Override
            public java.lang.String toString() {
                return "Search{" +
                        "search=" + Arrays.toString(search) +
                        "} ";
            }
        };
        return search(new Search(manufacturId, modelType)).getAsDouble();
    }

    private <T> OptionalDouble search(T search) {
        OptionalDouble cost = new Random().doubles(15000, 40000).findFirst();
        System.out.println("search costs for " +search  + "... find cost: " + cost.getAsDouble());
        return cost;
    }
}
