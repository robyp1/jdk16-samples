package sample.jdk16.it.writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import sample.jdk16.it.geometry.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class JackSonWriter {

    public static void write(Rectangle rect) {
        try {
            new ObjectMapper()
                    .writeValue(new FileOutputStream(getFile()),
                            rect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile() {
        return new File("jsonProva.json");
    }

    public static Optional<Rectangle> read(String filename) {
        Object retValue = null;
        try {
            retValue = new ObjectMapper()
                    .readValue( new FileInputStream(filename),
                            Rectangle.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional<Rectangle> r = Optional.empty();
        if (retValue instanceof Rectangle rectVal){
            r = Optional.of(rectVal);
        }
        return r;
    }
}
