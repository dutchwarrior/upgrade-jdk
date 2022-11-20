import org.example.Merchant;
import org.example.MerchantExample;
import org.example.Person;
import org.example.Sale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoreJava14Test {


    /*
    *
    * In the case of the ordered stream, the longest prefix is a contiguous sequence of elements of this stream
    * that match the predicate passed as a parameter to this method.
    * The first element of the sequence is the first element of this stream,
    * and the element immediately following the last element of the sequence does not match the given predicate.
    * In the case of the unordered stream, some elements of this stream match the given predicate and behaviour
    * of this operation becomes nondeterministic; so this method is free to drop any subset of matching elements.
    * */
    @Test
    public void takeWhile() {
        Stream<String> stream
                = Stream.of("aman", "amar", "suraj",
                "suvam", "Zahafuj");

        List<String> result = stream.takeWhile(name -> name.startsWith("a")).collect(Collectors.toList());
        Assertions.assertEquals(List.of("aman", "amar"),result);
    }

    @Test
    public void dropWhile() {
        Stream<String> stream
                = Stream.of("aman", "amar", "suraj",
                "suvam", "Zahafuj");

        List<String> result = stream.dropWhile(name -> name.startsWith("a")).collect(Collectors.toList());
        Assertions.assertEquals(List.of("suraj", "suvam", "Zahafuj"),result);
    }

    @Test
    public void record() {
        Merchant sneha = new Merchant("Sneha");
        Merchant raj = new Merchant("Raj");
        Merchant florence = new Merchant("Florence");
        Merchant leo = new Merchant("Leo");

        List<Merchant> merchantList = List.of(sneha, raj, florence, leo);

        List<Sale> salesList = List.of(
                new Sale(sneha,    LocalDate.of(2020, Month.NOVEMBER, 13), 11034.20),
                new Sale(raj,      LocalDate.of(2020, Month.NOVEMBER, 20),  8234.23),
                new Sale(florence, LocalDate.of(2020, Month.NOVEMBER, 19), 10003.67),
                new Sale(leo,      LocalDate.of(2020, Month.NOVEMBER,  4),  9645.34));

        MerchantExample app = new MerchantExample();

        List<Merchant> topMerchants =
                app.findTopMerchants(salesList, merchantList, 2020, Month.NOVEMBER);

        Assertions.assertEquals(List.of(sneha,florence, leo, raj), topMerchants);
    }

    @Test
    public void negatePredicate() {
        List<Person> people = Arrays.asList(
                new Person(1),
                new Person(18),
                new Person(2)
        );
        List<Person> peopleNotAdult = people.stream()
                .filter(Predicate.not(Person::isAdult))
                .collect(Collectors.toList());

        Assertions.assertEquals(2, peopleNotAdult.size());
    }
}
