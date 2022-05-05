package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов,
 * на которые подписаны студенты.
 * 2. Написать функцию, принимающую на вход список Student и возвращающую список из трех самых
 * любознательных (любознательность определяется количеством курсов).
 * 3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
 * которые посещают этот курс.
 */
public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Egor", Arrays.asList(new Course("Physics"),
                new Course("Mathematics"))));
        students.add(new Student("Anna", Arrays.asList(new Course("Mathematics"),
                new Course("Geographic"),
                new Course("Physics"),
                new Course("Biology"))));
        students.add(new Student("Antony", Arrays.asList(new Course("Informatics"),
                new Course("Chemistry"),
                new Course("Testing"))));
        students.add(new Student("Konstantin", Arrays.asList(new Course("Biology"),
                new Course("Mathematics"),
                new Course("Informatics"))));
        students.add(new Student("Stanislav", Arrays.asList(new Course("Chemistry"),
                new Course("Physics"))));

        //1
        System.out.println(students.stream()
                .map(s -> s.getCourses())
                .flatMap(f -> f.stream())
                .collect(Collectors.toSet()));

        //2
        System.out.println(students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                .limit(3)
                .collect(Collectors.toList()));

        //3
        Course course = new Course("Testing");
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(course))
                .collect(Collectors.toSet()));
    }
}