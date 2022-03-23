package domain.store;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable, Comparable<Product> {
    @Serial
    private static final long serialVersionUID = 1337;
    private static int countProducts = 0;
    private final int id;
    private final String name;
    private final String producer;
    private final int cost;
    private final LocalDate shelfLife;
    private final int count;
    private final int price;

    public Product(String name, String producer, int cost, LocalDate shelfLife, int count) {
        countProducts++;
        this.id = countProducts;
        this.name = name;
        this.producer = producer;
        this.cost = cost;
        this.shelfLife = shelfLife;
        this.count = count;
        this.price = cost * count;
    }

    @Override
    public int compareTo(Product o) {
        // == return 0
        // > return 1
        // < return - 1

        if (price > o.price) {
            return 1;
        } else if (price < o.price) {
            return -1;
        }

        if (cost > o.cost) {
            return 1;
        } else if (cost < o.cost) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return new String
                ("id: " + id + "\n" +
                        "name: " + name + "\n" +
                        "producer: " + producer + "\n" +
                        "cost: " + cost + "\n" +
                        "shelf life: " + shelfLife + "\n" +
                        "count: " + count + "\n" +
                        "price: " + price + "\n"
                );
    }

    public static void setCountOfProducts(int count) {
        countProducts = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public int getCost() {
        return cost;
    }

    public LocalDate getShelfLife() {
        return shelfLife;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }
}
