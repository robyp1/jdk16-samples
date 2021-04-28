package sample.jdk16.it;

import sample.jdk16.it.cars.Car;
import sample.jdk16.it.cars.Cars;
import sample.jdk16.it.geometry.Rectangle;
import sample.jdk16.it.jsonPathExamples.Extractor;
import sample.jdk16.it.writers.JackSonWriter;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *https://www.youtube.com/watch?v=YBmR0J3-r3o&t=224s&ab_channel=IntelliJIDEAbyJetBrains
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

        //JSON
        Rectangle rectangle = new Rectangle(20, 60, List.of("solid", "black"));
        //functional lambda with loan pattern/passing block
        //https://dzone.com/articles/functional-programming-patterns-with-java-8
        writeToFile(rectangle, JackSonWriter::write);
        String filename = "jsonProva.json";
        Optional<Rectangle> rectangle1 = readFromFile(filename, JackSonWriter::read);
        rectangle1.ifPresent(Extractor::extractFromJson);
    }

    private static Optional<Rectangle> readFromFile( String fileName, Function<String, Optional<Rectangle>> reader) {
        return reader.apply(fileName);
    }

    private static void writeToFile(Rectangle rectangle, Consumer<Rectangle> write) {
        write.accept(rectangle);
    }


}
