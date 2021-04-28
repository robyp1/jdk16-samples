package sample.jdk16.it.jsonPathExamples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class JsonCollector implements Collector<ObjectNode, ArrayNode, ArrayNode> {

    private final Supplier<ArrayNode> jsonSupplier;
    private final BiConsumer<ArrayNode, ObjectNode> jsonAccumulator;
    private final BinaryOperator<ArrayNode> jsonCombiner;
    private Function<ArrayNode, ArrayNode> jsonFinisher;
    private final Set<Characteristics> jsonCharacteristic;

    public JsonCollector(Supplier<ArrayNode> jsonSupplier, BiConsumer<ArrayNode, ObjectNode> jsonAccumulator, BinaryOperator<ArrayNode> jsonCombiner, Function<ArrayNode, ArrayNode> jsonFinisher, Set<Characteristics> jsonCharacteristic) {
        this.jsonSupplier = jsonSupplier;
        this.jsonAccumulator = jsonAccumulator;
        this.jsonCombiner = jsonCombiner;
        this.jsonFinisher = jsonFinisher;
        this.jsonCharacteristic = jsonCharacteristic;
    }

    public JsonCollector(Supplier<ArrayNode> jsonSupplier, BiConsumer<ArrayNode, ObjectNode> jsonAccumulator, BinaryOperator<ArrayNode> jsonCombiner, Set<Characteristics> jsonCharacteristic) {
        this.jsonSupplier = jsonSupplier;
        this.jsonAccumulator = jsonAccumulator;
        this.jsonCombiner = jsonCombiner;
        this.jsonCharacteristic = jsonCharacteristic;
    }

    /**
     * Creo un collector per creare un ArrayNode -> [] che contiene una lista di oggetti ObjectNode  ->"{}"
     * @return
     */
    //<oggetto, valore di output, valore di output>
    public static Collector<ObjectNode, ?, ArrayNode> toJsonArrayCollector() {
        ObjectMapper mapper = new ObjectMapper();
        return new JsonCollector(mapper::createArrayNode, //supplier - parto da un ArrayNode
                ArrayNode::add,  //accumulator - chiamo Arraynode metodo add per aggiungere l'ObjectNode
                (left, right) -> left.addAll(right), //combiner //dati due ArrayNode li unisco
                Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH)));
    }


    @Override
    public Supplier<ArrayNode> supplier() {
        return jsonSupplier;
    }

    @Override
    public BiConsumer<ArrayNode, ObjectNode> accumulator() {
        return jsonAccumulator;
    }

    @Override
    public BinaryOperator<ArrayNode> combiner() {
        return jsonCombiner;
    }

    @Override
    public Function<ArrayNode, ArrayNode> finisher() {
        return jsonFinisher;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return jsonCharacteristic;
    }
}
