import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyStore {
     private PriorityQueue<Toy> toyQueue;
     private Random random;

     private int[] toyIds; // Массив для ID игрушек
     private String[] toyNames; // Массив для названий игрушек
     private int[] toyWeights; // Массив для весов игрушек

     private int toyCount; // Количество игрушек, которые мы добавили

     public ToyStore(int maxToys) {
          toyQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t1.getId(), t2.getId()));
          random = new Random();

          toyIds = new int[maxToys];
          toyNames = new String[maxToys];
          toyWeights = new int[maxToys];

          toyCount = 0; // Изначально игрушек нет
     }

     // Метод добавления игрушки с учетом веса
     public void addToy(Toy toy) {
          toyIds[toyCount] = toy.getId();
          toyNames[toyCount] = toy.getName();
          toyWeights[toyCount] = toy.getWeight();

          // Добавляем игрушку в очередь с учетом веса
          for (int i = 0; i < toy.getWeight(); i++) {
               toyQueue.offer(toy);
          }
          toyCount++; // Увеличиваем счетчик добавленных игрушек
     }

     // Метод получения игрушки (случайный выбор)
     public Toy getToy() {
          if (toyQueue.isEmpty()) {
               return null;
          }

          // Сначала рассчитываем суммарный вес
          int totalWeight = 0;
          for (Toy toy : toyQueue) {
               totalWeight += toy.getWeight();
          }

          int randomWeight = random.nextInt(totalWeight);
          int accumulatedWeight = 0;

          // Поочередно проверяем, к какой игрушке попадет случайный вес
          for (Toy toy : toyQueue) {
               accumulatedWeight += toy.getWeight();
               if (accumulatedWeight > randomWeight) {
                    return toy;
               }
          }
          return null;
     }

     // Метод записи результатов в файл
     public void writeResultsToFile(String[] results, String filename) {
          try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
               for (String line : results) {
                    writer.write(line);
                    writer.newLine();
               }
               System.out.println("Результаты записаны в файл: " + filename);
          } catch (IOException e) {
               System.out.println("Ошибка при записи в файл: " + e.getMessage());
          }
     }

     public void printToyDetails() {
          System.out.println("ID игрушек: ");
          for (int i = 0; i < toyCount; i++) {
               System.out.println(toyIds[i]);
          }

          System.out.println("Названия игрушек: ");
          for (int i = 0; i < toyCount; i++) {
               System.out.println(toyNames[i]);
          }

          System.out.println("Вес игрушек: ");
          for (int i = 0; i < toyCount; i++) {
               System.out.println(toyWeights[i]);
          }
     }
}
 