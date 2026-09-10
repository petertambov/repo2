package work2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

enum Job{
    ENGINEER, MANAGER, DIRECTOR
}

class Employee {

    private final String name;
    private final int age;
    private final Job job;

    public Employee(String name, int age, Job job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Job getJob() {
        return job;
    }

    public String toString() {
        return String.format("name = '%s', age = '%d', job = '%s'",name, age ,job.toString());
    }
}

public class Main {

    public static void main(String[] args) {

        // peek  все же используем для красоты
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1);

        System.out.print("Finding third max in list:");
        Optional<Integer> resultInteger = numList.stream()
                .peek(e -> System.out.print(" " + e.toString()))
                .sorted()
                .skip(2)
                .findFirst();
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("result: "  + (resultInteger.isPresent() ? resultInteger.get().toString() : "no"));
        System.out.println();


        System.out.print("Finding distinct third max in list:");
        resultInteger = numList.stream()
                .peek(n -> System.out.print(" " + n))
                .distinct()
                .sorted()
                .skip(2)
                .findFirst();
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("result: "  + (resultInteger.isPresent() ? resultInteger.get().toString() : "no"));
        System.out.println();

        List<Employee> employees = Arrays.asList(
                new Employee("Peter", 20, Job.ENGINEER),
                new Employee("Donald", 25, Job.ENGINEER),
                new Employee("Tramp", 30, Job.MANAGER),
                new Employee("Barak", 40, Job.ENGINEER),
                new Employee("Obama", 25, Job.ENGINEER),
                new Employee("Robinson", 40, Job.MANAGER),
                new Employee("Cruzo", 45, Job.DIRECTOR)
        );

        System.out.println ("Finding three oldest enginear in list:");
        List<String> engineers = employees.stream()
               .peek(System.out::println)
               .filter(e -> Job.ENGINEER.equals(e.getJob()))
               .sorted((e1, e2) -> Integer.compare(e2.getAge(), e1.getAge()))
               .limit(3)
               .map(Employee::getName)
               .collect(Collectors.toList());
        System.out.println("-----------------------------------------------------");
        System.out.println("result: " + engineers);
        System.out.println();

        System.out.println ("Finding average age of enginears in list:");
        OptionalDouble resultDouble = employees.stream()
                .peek(System.out::println)
                .filter(e -> Job.ENGINEER.equals(e.getJob()))
                .mapToInt(Employee::getAge)
                .average();
        System.out.println("-----------------------------------------------------");
        System.out.println("result: "  + (resultDouble.isPresent() ? resultDouble.getAsDouble() : "no"));
        System.out.println();

        String str = "one two two three four four five six seven";

        /*
        ALL WORDS
        System.out.println(String.format("Finding words with max length in line '%s': ", str));
        List<String> strListReesult = Arrays.stream(str.split("\\s+"))
                .distinct()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.toList()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
        System.out.println("-----------------------------------------------------");
        System.out.println("result: "  + (strListReesult.isEmpty() ? "no words" : strListReesult.toString()));
        System.out.println();*/

        //ANY WORD
        System.out.println(String.format("Finding any words with max length in line '%s': ", str));
        Optional<String> strResult = Arrays.stream(str.split("\\s+"))
                .distinct()
                .max(Comparator.comparingInt(String::length));
        System.out.println("-----------------------------------------------------");
        System.out.println("result: "  + (strResult.isPresent() ? strResult.get() : "no words"));
        System.out.println();

        System.out.println(String.format("Creating hashMap for line '%s': ", str));
        Map<String, Long> resultMap = Arrays.stream(str.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
        System.out.println("-----------------------------------------------------");
        System.out.println("result: " + resultMap);
        System.out.println();

        String[] strAr = {
                "a bb ccc dddd ee",
                "fff g  hhhhh ii jj",
                "kkk l mmmmmmm nnn ooo jjj"
        };


        System.out.println();
        System.out.println("Finding any word with max length in lines (exclude <> 5 words):");
        strResult = Arrays.stream(strAr)
                .peek(System.out::println)
                .filter(line ->  line.trim().split("\\s+").length == 5)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .max(Comparator.comparingInt(String::length));
        System.out.println("-----------------------------------------------------");
        System.out.println("result: " + (strResult.isPresent() ? strResult.get() : "no words"));




    }


}

