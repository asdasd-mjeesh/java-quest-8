package domain.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ProductFilter {
    private final List<Product> products;

    public ProductFilter(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    // supported method for a and b
    public List<Product> getAllWithName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .collect(Collectors.toList());
    }

    // a
    public List<Product> getAllSortedByShelfLifeWithName(String name) {
        List<Product> result = new ArrayList<>(getAllWithName(name));

        return result.stream()
                .sorted(Comparator.comparing(Product::getShelfLife).reversed())
                .collect(Collectors.toList());
    }

    // b
    public List<Product> getProductsWithNameAndCostALess(String name, int maxCost) {
        var result = getAllWithName(name);

        return result.stream()
                .filter(product -> product.getCost() <= maxCost)
                .collect(Collectors.toList());
    }

    // c
    public List<Product> getAllWithShelfLifeALong(LocalDate minShelfLife) {
        return products.stream()
                .filter(product -> product.getShelfLife().isAfter(minShelfLife))
                .collect(Collectors.toList());
    }

    // d
    public List<Product> getAllSortedByPrice() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice)
                        .thenComparing(Product::getCost))
                .collect(Collectors.toList());
    }
}
