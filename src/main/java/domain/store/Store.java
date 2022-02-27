package domain.store;

import data.util.BinaryFileExecutor;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class Store extends ProductFilter {
    private final Map<String, List<Product>> producersAndProducts;
    private final Set<String> producers;

    public Store() {
        super(BinaryFileExecutor.readFile());
        producersAndProducts = new HashMap<>();
        producers = new HashSet<>(producersAndProducts.keySet());
        update();
        initProductId();
    }

    public void addProduct(String name, String producer, int cost, LocalDate shelfLife, int count) {
        super.getProducts().add(new Product(name, producer, cost, shelfLife, count));
        save();
        update();
    }

    public void deleteProduct(int id) {
        super.getProducts().removeIf(s -> s.getId() == id);
        BinaryFileExecutor.deleteProduct(id);
        update();
        save();
    }

    private void initProductId() {
        try {
            Product.setCountOfProducts(
                    super.getProducts().stream()
                            .max(Comparator.comparing(Product::getId)).get().getId());
        } catch (NoSuchElementException ignored) {
            // ignored
        }
    }

    private void update() {
        searchProducersAndProducts();
        producers.addAll(producersAndProducts.keySet());
    }

    private void searchProducersAndProducts() {
        var products = super.getProducts();

        producersAndProducts.clear();
        for (Product product : products) {
            producersAndProducts.put(product.getProducer(), searchProducts(product.getProducer()));
        }
    }

    private List<Product> searchProducts(String producerName) {
        List<Product> allProducts = super.getProducts();
        return allProducts.stream()
                .filter(product -> product.getProducer().equals(producerName))
                .collect(Collectors.toList());
    }

    private void save() {
        BinaryFileExecutor.saveProductsToFile(super.getProducts());
    }

    public Map<String, List<Product>> getProducersWithThemProducts() {
        return producersAndProducts;
    }

    public Set<String> getProducers() {
        return producers;
    }
}
