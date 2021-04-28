package sample.jdk16.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import sample.jdk16.it.cars.Car;
import sample.jdk16.it.cars.Cars;
import sample.jdk16.it.geometry.Rectangle;
import sample.jdk16.it.jsonPathExamples.Extractor;
import sample.jdk16.it.jsonPathExamples.JsonCollector;
import sample.jdk16.it.writers.JackSonWriter;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *https://www.youtube.com/watch?v=YBmR0J3-r3o&t=224s&ab_channel=IntelliJIDEAbyJetBrains
 *
 */
public class App 
{

    /**
     * esempio di Output:
     * search costs for Search{search=[1d, Giulietta]} ... find cost: 32381.351201457488
     * search costs for Search{search=[2d, Giulia]} ... find cost: 17074.31350520178
     * search costs for Search{search=[3d, Stelvio]} ... find cost: 20935.300666882464
     * search costs for Search{search=[4d, C4]} ... find cost: 38231.30908320521
     * [Car[manufacturId=2d, model=alfa Romeo, modelType=Giulia], Car[manufacturId=3d, model=alfa Romeo, modelType=Stelvio], Car[manufacturId=1d, model=alfa Romeo, modelType=Giulietta], Car[manufacturId=4d, model=alfa Romeo, modelType=C4]]
     * Rectangle[width=20, length=60, attribute=[solid, black]]

     * ["solid","black","dotted","red"]
     * solid
     * ["dotted"]
     * [ {
     *   "style" : "solid"
     * }, {
     *   "style" : "dotted"
     * } ]
     * @param args
     * @throws JsonProcessingException
     */
    public static void main( String[] args ) throws JsonProcessingException {
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

        //creare un jsonColector
        ArrayNode resultArr = Stream.of("solid", "dotted")
                .map(style -> createNode(style)).collect(JsonCollector.toJsonArrayCollector());
        String jsonArrayResultString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resultArr);
        System.out.println(jsonArrayResultString);
    }

    private static Optional<Rectangle> readFromFile( String fileName, Function<String, Optional<Rectangle>> reader) {
        return reader.apply(fileName);
    }

    private static void writeToFile(Rectangle rectangle, Consumer<Rectangle> write) {
        write.accept(rectangle);
    }


    private static ObjectNode createNode(String inputAttribute){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode nodeJson = mapper.createObjectNode();
        nodeJson.put("style", inputAttribute);
        return nodeJson;
    }
}
