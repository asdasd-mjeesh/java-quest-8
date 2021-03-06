package UI;

import UI.interfaces.CreatingADate;
import domain.store.Store;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExecuteManager implements CreatingADate {
    private final Store store;
    private final Scanner in;

    public ExecuteManager(Store store) {
        this.store = store;
        in = new Scanner(System.in);
    }

    public void addProduct() {
        try {
            System.out.print(" Введите название продукта:\t");
            String name = in.nextLine();

            System.out.print(" Введите имя произодителя:\t");
            String producer = in.nextLine();

            System.out.print(" Введите стоимость продукта:\t");
            int cost = in.nextInt();

            LocalDate shelfLife = createDate();

            System.out.print(" Введите количество продуктов:\t");
            int count = in.nextInt();
            in.nextLine();

            store.addProduct(name, producer, cost, shelfLife, count);
        } catch (InputMismatchException e) {
            System.err.println("ашыпка ввода");
        }
    }

    public void deleteProduct(int id) {
        store.deleteProduct(id);
    }
}
