package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SuppressWarnings("EndlessStream")
public class Main {
    public static void main(String[] args) {

        /**
         * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
         * +
         * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
         */

//        1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000

        List<Integer> randomNumbers = ThreadLocalRandom.current().ints(1, 1000000).boxed().limit(1000).collect(Collectors.toList());
        System.out.println(randomNumbers);

//       получить из потока Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать

        randomNumbers.stream().filter(i -> i > 500000).map(i -> ((i * 5) - 150)).reduce(Integer::sum).ifPresent(System.out::println);

//        2.3 Найти количество чисел, квадрат которых меньше, чем 100_000

        System.out.println(randomNumbers.stream().map(i -> (i * i)).filter(i -> i < 100_000).count());


        /**
         *
         *  2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
         *          * 2.1 Создать список из 10-20 сотрудников
         *          * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
         *          * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
         *          * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
         *          * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
         * */

//            * 2.1 Создать список из 10-20 сотрудников

        List<Employee> randomEmployees = Stream.of(
                        new Employee(
                                "Ivan", 18, 400000 * 1.5, "Frontend"), new Employee(
                                "Kavan", 19, 300000 * 1.5, "Frontend"), new Employee(
                                "VIvan", 18, 200000 * 1.5, "Frontend"), new Employee(
                                "Vavan", 21, 300000 * 1.5, "Frontend"), new Employee(
                                "Sulvan", 20, 500000 * 1.5, "Frontend"), new Employee(
                                "Kolyan", 28, 800000 * 1.5, "Backend"), new Employee(
                                "Valeriy", 33, 1000000 * 1.5, "Backend"), new Employee(
                                "Daniil", 38, 500000 * 1.5, "Backend"), new Employee(
                                "Lex", 16, 400000 * 1.5, "Frontend"), new Employee(
                                "Iva", 22, 300000 * 1.5, "Frontend"), new Employee(
                                "Ivashka", 25, 200000 * 1.5, "Frontend"), new Employee(
                                "Frodo", 24, 700000 * 1.5, "Frontend"), new Employee(
                                "Zoom", 27, 800000 * 1.5, "Backend"), new Employee(
                                "Santa", 29, 800000 * 1.5, "Backend"), new Employee(
                                "Nikolya", 33, 500000 * 1.5, "Frontend"), new Employee(
                                "Vova", 32, 300000 * 1.5, "Frontend"), new Employee(
                                "Tolya", 20, 5000 * 1.5, "Frontend"), new Employee(
                                "Misha", 20, 3000 * 1.5, "Frontend"), new Employee(
                                "Chip", 39, 450000 * 1.5, "Backend"))
                .peek(System.out::println).collect(Collectors.toList());

//        * 2.2 Вывести список всех различных отделов (department) по списку сотрудников

        randomEmployees.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

//        *2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20 %
// для наглядности изменения добавил peek
        randomEmployees.stream().filter(employee -> employee.getSalary() < 10_000).peek(System.out::println).map(employee -> {
            employee.setSalary(employee.getSalary() * 1.2);
            return employee;
        }).forEach(System.out::println);


//        Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела

        Map<String, List<Employee>> employeeMap = randomEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        for (Map.Entry<String, List<Employee>> entry : employeeMap.entrySet()) {
            System.out.println("Отдел: " + entry.getKey());
            System.out.println("Сотрудники: " + entry.getValue());
            System.out.println();
        }

//        * 2.5 * Из списка сотрудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
        Map<String, Double> departmentAverageSalaryMap = randomEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(departmentAverageSalaryMap);

    }
}