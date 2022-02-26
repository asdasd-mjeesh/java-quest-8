package UI;

import domain.store.Store;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Example {
    private Store store;
    private ExecuteManager executeManager;
    private ShowManager showManager;

    public Example() {
        store = new Store();
        executeManager = new ExecuteManager(store);
        showManager = new ShowManager(store);
    }

    public static void main(String[] args) {
        Example asd = new Example();
        asd.run();
    }

    private void run() {
        Scanner in = new Scanner(System.in);
        String choice;
        String nameFilter;
        int costFilter;
        LocalDate dateFilter;

        while (true) {
            showManager.showMenu();
            System.out.print("CHOICE:\t");
            choice = in.nextLine();

            try {
                switch (choice)
                {
                    case "a":
                        System.out.print("name:\t");
                        nameFilter = in.nextLine();
                        showManager.showProductsWithNameAndSortedByShelfLife(nameFilter);
                        choice = "0";
                        break;
                    case "b":
                        System.out.print("name:\t");
                        nameFilter = in.nextLine();
                        System.out.print("\nmax cost:\t");
                        costFilter = in.nextInt();
                        showManager.showProductsWithNameAndCostALess(nameFilter, costFilter);
                        choice = "0";
                        break;
                    case "c":
                        dateFilter = executeManager.createDate();
                        showManager.showProductWithShelfLifeAlong(dateFilter);
                        break;
                    case "d":
                        showManager.showAllProductsSortedByPrice();
                        break;
                    case "e":
                        showManager.showAllProducers();
                        break;
                    case "f":
                        showManager.showAllProducersWithThemProducts();
                        break;
                    case "1":
                        showManager.showAll();
                        break;
                    case "2":
                        executeManager.addProduct();
                        break;
                    case "3":
                        System.out.print("id:\t");
                        int id = in.nextInt();
                        executeManager.deleteProduct(id);
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                choice = "";
                System.out.println("input invalid value");
            }
        }
    }
}
