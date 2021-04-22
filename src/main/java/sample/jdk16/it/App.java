package sample.jdk16.it;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Car c1= new Car("1d","alfa Romeo", "Giulietta");
        Car c2= new Car("2d","alfa Romeo", "Giulia");
        Car c3= new Car("3d","alfa Romeo", "Stelvio");
        Car c4= new Car("4d","alfa Romeo", "C4");
        List<Car> carTypeByCosts = new Cars().findCarTypeByCosts(List.of(c1, c2, c3, c4));
        System.out.println(carTypeByCosts);
    }
}
