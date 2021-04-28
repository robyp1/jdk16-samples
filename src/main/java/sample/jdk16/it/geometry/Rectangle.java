package sample.jdk16.it.geometry;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

//qui non si riesce ad usare record perchè Jackson vuole il costruttore di default che nel record non esiste essendo che le proprietà di classe ( width , length, attribute) per il record
//devono essere final quindi vengono inizializzate nel costruttore (record è immutabile), ho lasciato cmq  i metodi che non iniziano con get  come se fosse record
//https://blog.jetbrains.com/idea/2021/03/java-16-and-intellij-idea/
public final class Rectangle {
    @JsonProperty
    private  int width;
    @JsonProperty
    private  int length;
    @JsonProperty
    private  List<String> attribute;

    public Rectangle() {
    }

    public Rectangle(@JsonProperty int width, @JsonProperty int length,
                     @JsonProperty List<String> attribute) {
        this.width = width;
        this.length = length;
        this.attribute = attribute;
    }

    @JsonProperty
    public int width() {
        return width;
    }

    @JsonProperty
    public int length() {
        return length;
    }

    @JsonProperty
    public List<String> attribute() {
        return attribute;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rectangle) obj;
        return this.width == that.width &&
                this.length == that.length &&
                Objects.equals(this.attribute, that.attribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, length, attribute);
    }

    @Override
    public String toString() {
        return "Rectangle[" +
                "width=" + width + ", " +
                "length=" + length + ", " +
                "attribute=" + attribute + ']';
    }


}
