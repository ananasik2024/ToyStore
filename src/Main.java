import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          System.out.print("Сколько игрушек вы хотите добавить? ");
          int numberOfToys = scanner.nextInt();
          scanner.nextLine(); // Очистка буфера после ввода числа

          ToyStore toyStore = new ToyStore(numberOfToys);

          for (int i = 1; i <= numberOfToys; i++) {
               System.out.println("Введите данные для игрушки №" + i);

               System.out.print("ID (целое число): ");
               int id = scanner.nextInt();
               scanner.nextLine(); 

               System.out.print("Название: ");
               String name = scanner.nextLine();

               System.out.print("Вес (частота выпадения, целое число): ");
               int weight = scanner.nextInt();
               scanner.nextLine(); 

               Toy toy = new Toy(id, name, weight);
               toyStore.addToy(toy); // Добавляем игрушку в магазин
          }

          // Выводим массивы с данными
          toyStore.printToyDetails();

          // Розыгрыш игрушек
          String[] results = new String[10];
          for (int i = 0; i < 10; i++) {
               Toy toy = toyStore.getToy();
               results[i] = "Выдана игрушка: " + toy.getName();
               System.out.println(results[i]);
          }

          // Записываем результаты в файл
          String filename = "/Users/anastasiyashagrai/Desktop/QA_study/ToyStore/results.txt";
          toyStore.writeResultsToFile(results, filename);

     }
}
