package org.apikhairullina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        Operation[] operations = new Operation[10];
        Customer[] customers = new Customer[10];

        while (true) {
            System.out.println("Введите данные по транзакции: id amount date customer");
            System.out.println("Пример ввода: 3456723 24000,00 2024.02.24 1");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            int id = Integer.parseInt(parts[0]);
            double amount = Double.parseDouble(parts[1]);
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate date = LocalDate.parse(parts[2], f);
            int customerIndex = Integer.parseInt(parts[3]);

            Operation op = new Operation(id, amount, date, customerIndex);
            operations[i] = op;
            i++;

            if (customers[customerIndex] == null) {
                System.out.println("Введите данные клиента: имя, номер телефона");
                System.out.println("Пример ввода: Иван +79991234567");
                String customerInput = scanner.nextLine();
                String[] customerParts = customerInput.split(" ");
                String name = customerParts[0];
                String phonnum = customerParts[1];

                Customer customer = new Customer(name, customerIndex, "", phonnum);
                customers[customerIndex] = customer;
            }
        }

        // Вывод транзакций и соответствующих им данных клиентов
        System.out.println("Транзакции и данные клиентов:");
        for (int j = 0; j < i; j++) {
            Operation operation = operations[j];
            Customer customer = customers[operation.getCustomer()];
            System.out.println(operation + " | Клиент: " + customer.getName() + ", Телефон: " + customer.getPhonnum());
        }
    }
}
