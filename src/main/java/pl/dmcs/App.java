package pl.dmcs;

import java.time.Instant;

import static pl.dmcs.Serializator.deserialize;
import static pl.dmcs.Serializator.serialize;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Person person = createPerson();

        System.out.println(person);
        System.out.println("Serialization\n");

        serialize(person, "serialization.ser");
        Person deserializedPerson = deserialize("serialization.ser");
        System.out.println(deserializedPerson);


        System.out.println("\n\nExternalization\n");
        Externalizator.externalize(person, "externalization");
        Person externalization = Externalizator.deexternalize(new Person(), "externalization");
        System.out.println(externalization);
    }

    private static Person createPerson() {
        Instant date = Instant.ofEpochSecond(1231231293L);
        return new Person("Jan", "Kowalski", date, 3L);
    }
}
