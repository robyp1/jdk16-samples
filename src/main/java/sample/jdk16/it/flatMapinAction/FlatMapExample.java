package sample.jdk16.it.flatMapinAction;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class FlatMapExample {

    public static void main(String[] args) {

        Developer o1 = new Developer();
        o1.setName("mkyong");
        o1.addBook("Java 8 in Action");
        o1.addBook("Spring Boot in Action");
        o1.addBook("Effective Java (3nd Edition)");

        Developer o2 = new Developer();
        o2.setName("zilap");
        o2.addBook("Learning Python, 5th Edition");
        o2.addBook("Effective Java (3nd Edition)");

        List<Developer> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);

        //restituisco il primo libro a caso nella lista
        Optional<Set<String>> first = list.stream().map(Developer::getBook).findFirst();//Stream<Set<String>> ->.findFirst torna: Optional<Set<String>>, findFirst è terminale

        //devo togliere dal set i libri non python
        Set<Set<String>> collect = list.stream().map(Developer::getBook)
                //.filter() ho un Set<String> qui dentro come faccio  afiltrarne senza dovermelo scorrere???
                .collect(Collectors.toSet()); //Stream<Set<String>> -> collect a Set torna: Set<Set<String>
        //TODO: hmm....Set of Set...how to process?

        //fare il flattern del primo set ovvero estraggo dal Set lo String del book e lo inserisco in uno stream
        Set<String> books = list.stream()
                .flatMap(book -> book.getBook().stream())
                .filter(bookname -> !bookname.toLowerCase().contains("python")) // lavoro con il contenuto di Stream ovvero String e filtro tutti i book non python
                .collect(Collectors.toSet());//qui ottengo Set<String>

        System.out.println(books);


        // EXAMPLE CAR2 con option  :
        Employee employee = new Employee();
        getInsuranceName(Optional.of(employee));//of perchè employee non può essere nullo, è mandatory



    }


    public static String getInsuranceName(Optional<Employee> employee) {
        // il map confeziona un elemento alla volta o l'elemento in un altro oggetto contenitore (stream o optional o altro)
        Optional<Optional<Car2>> car21 = employee.map(Employee::getCar);
        //Optional of Optional how to? flatten!
        Optional<Car2> car2 = employee.flatMap(Employee::getCar);  //ok

        Optional<Insurance> insurance = employee.flatMap(Employee::getCar).flatMap(Car2::getInsurance);
        //se insurance non è vuoto (empty) allora prelevo il getName altrimenti il map non viene invocato e viene restituito Unknown name
        //ho due modi:
        //qui invece si passa un supplier con or dello stesso tipo del risultato di map cioè Optional<String>
        employee.flatMap(Employee::getCar).flatMap(Car2::getInsurance).map(x-> x.getName()).or(() -> Optional.of("Unknown name"));
        //meglio usare orElse che restituisce direttaemente la stringa in casi di insurance vuota
        return employee.flatMap(Employee::getCar).flatMap(Car2::getInsurance).map(x-> x.getName()).orElse("Unknown name");
    }
}
