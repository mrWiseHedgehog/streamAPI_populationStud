import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // less than 18 years
        long lessThan18Years = persons.stream().filter(p -> p.getAge() < 18).count();


        // get list the surname conscripts
        List<String> conscripts = persons.stream().filter(p -> p.getAge() >= 18
                        && p.getAge() <= 27 && p.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());


        // Get sorted list potentially workable people of higher education from 18 to 60 for female
        // and from 18 to 65 for male
        List<Person> workable = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> p.getAge() >= 18)
                .filter(p -> (p.getSex() == Sex.MAN && p.getAge() <= 65) || (p.getSex() == Sex.WOMAN && p.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workable);
    }
}