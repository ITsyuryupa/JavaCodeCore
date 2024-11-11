package StreamAPI.Task2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        //Используйте Parallel Stream для обработки данных и создания Map,
        // где ключ - предмет, а значение - средняя оценка по всем студентам.

        // Используем параллельный поток для агрегации оценок по предметам
        Map<String, Double> averageGrades = students.parallelStream()
                .flatMap(student -> student.getGrades().entrySet().stream()) // Преобразуем каждую карту оценок в поток
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey, // ключ - это предмет
                        Collectors.averagingInt(Map.Entry::getValue) // среднее значение по каждому предмету
                ));
        // Выводим результат
        averageGrades.forEach((subject, avgGrade) ->
                System.out.println(subject + ": " + avgGrade));


    }
}
