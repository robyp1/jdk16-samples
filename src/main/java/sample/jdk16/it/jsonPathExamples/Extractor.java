package sample.jdk16.it.jsonPathExamples;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.JsonPath;
import sample.jdk16.it.geometry.Rectangle;

import java.util.List;

public class Extractor {

    //esempio di String text-block, nuova scrittura per string
    // la riga con \ -> serve per indicare che non c'è a capo, mentre per le altre righe \n è implicito
    // https://www.baeldung.com/java-text-blocks
    public static final String JSON_TO_TEST = """
            {
                    a: {"width": 20,
                            "length": 60,
                            "attribute": [
                    "solid",
                            "%s"
                    ]},
                    b: {
                    "width": 40,
                            "length": 60,
                            "attribute": [
                    "dotted",
                            "re \
                            d"
                    ]}
                    
            }
            """.formatted("red");//è come String.format

    public static void extractFromJson(Rectangle rectangle){
        System.out.println(rectangle);
        String rect = JSON_TO_TEST;
        //enable IntelliLang plugin
        //alt + invio "inject language or reference"
        //alt+ invio "evaluate json path"
        //copia input sopra e dai invio uscirà "solid"
        //https://www.jetbrains.com/help/idea/using-language-injections.html#inject_language
        String pathFilterAllAttr = "$.*.attribute[*]";//raccolgo  tutti gli attribute di tutti i nodi
        String pathFilterAllAttr0= "$.*[?].attribute[0]";//? per il filtro, leggo tutti i nodi e in ogni nodo filtro per "?" e prendo la posizione ' dell0'array attribute
        String pathFilterFirstAttr = "$.a.attribute[0]";//nodo a prendo valore posizione 0 nell'array attribute
        List<String> attributes = JsonPath.parse(rect).read(pathFilterAllAttr);
        System.out.println(attributes);
        String attributes2 = JsonPath.parse(rect).read(pathFilterFirstAttr);
        System.out.println(attributes2);
        //qui passo il filtro che verrà applicato ai nodi * secondo il Criteria query widht = 40 (di tutti prendi solo quello con width =40)
        //per cui estrai poi la posizione 0 dell'array attribute
        List<String> attributesDotted = JsonPath.parse(rect).read(pathFilterAllAttr0, Criteria.where("width").eq(40));
        System.out.println(attributesDotted);
    }
}
