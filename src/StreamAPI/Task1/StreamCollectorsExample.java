package StreamAPI.Task1;

import java.util.List;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        //Создайте список заказов с разными продуктами и их стоимостями.
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("Headphones", 100.0),
                new Order("Headphones", 50.0),
                new Order("Headphones", 200.0)
        );
//        orders.stream().forEach((order) -> {
//            System.out.println(order.getProduct() + ": " + order.getCost());
//        });

//        //Группируйте заказы по продуктам.
//        orders.stream().collect(Collectors.groupingBy(Order::getProduct)).forEach((product, orderList) ->
//        {
//            System.out.println(product + ": ");
//            orderList.forEach(order -> System.out.println("Cost: " + order.getCost()));
//        });

//        //Для каждого продукта найдите общую стоимость всех заказов.
//        orders.stream()
//                .collect(Collectors.groupingBy(Order::getProduct))
//                .forEach((product, orderList) -> {
//                    double totalCost = orderList.stream()
//                            .mapToDouble(Order::getCost)  // Преобразуем в поток чисел (стоимости)
//                            .sum();  // Суммируем стоимости
//                    System.out.println(product + ": " + totalCost);
//                });

//        orders.stream()
//                .collect(Collectors.groupingBy(
//                        Order::getProduct, // группировка по продукту
//                        Collectors.summingDouble(Order::getCost) // суммирование стоимости
//                ))
//                .forEach((product, cost) -> System.out.println(product + ": " + cost));


//        //Отсортируйте продукты по убыванию общей стоимости
//        orders.stream()
//                .collect(Collectors.groupingBy(
//                        Order::getProduct, // группировка по продукту
//                        Collectors.summingDouble(Order::getCost) // суммирование стоимости
//                ))
//                .entrySet().stream()
//                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // сортировка по убыванию
//                .forEach(entry ->
//                        System.out.println("Product: " + entry.getKey() + ", Total Cost: " + entry.getValue())
//                );


//        //Выберите три самых дорогих продукта
//        orders.stream()
//                .sorted((order1, order2) -> Double.compare(order2.getCost(), order1.getCost()))
//                .limit(3)
//                .forEach(order -> System.out.println(order.getProduct() + ": " + order.getCost()));


        //Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
        double totalCostOfTop3 = orders.stream()
                .sorted((order1, order2) -> Double.compare(order2.getCost(), order1.getCost())) // сортировка по убыванию стоимости
                .limit(3) // ограничение результатами топ-3
                .peek(order -> System.out.println(order.getProduct() + ": " + order.getCost())) // выводим каждый заказ
                .mapToDouble(Order::getCost) // преобразуем в стоимость для суммирования
                .sum();

        System.out.println("Total cost: " + totalCostOfTop3);


    }
}
