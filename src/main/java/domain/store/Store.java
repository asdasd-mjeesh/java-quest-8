package domain.store;

import data.util.BinaryFileExecutor;

import java.time.LocalDate;
import java.util.*;

public final class Store {
    private final List<Product> products;
    private final Set<String> producers;
    private final Map<String, ArrayList<Product>> producersAndProducts;
    private final ProductFilter productsFilter;

    public Store() {
        products = new ArrayList<>(BinaryFileExecutor.readFile());
        producers = new HashSet<>();
        producersAndProducts = new HashMap<>();
        productsFilter = new ProductFilter(products);

        update();
        initProductId();
    }

    public void addProduct(String name, String producer, int cost, LocalDate shelfLife, int count) {
        products.add(new Product(name, producer, cost, shelfLife, count));
        save();
        update();
    }

    public void deleteProduct(int id) {
        products.removeIf(s -> s.getId() == id);
        BinaryFileExecutor.deleteProduct(id);
        update();
        save();
    }

    private void initProductId() {
        try {
            Product.setCountOfProducts(
                    products.stream().max(
                            Comparator.comparing(Product::getId)).get().getId());
        } catch (NoSuchElementException ignored) {
            // ignored
        }
    }

    private void update() {
        searchProducers();
        searchProducersWithProducts();
    }

    private void searchProducers() {
        for (Product product : products) {
            producers.add(product.getProducer());
        }
    }

    private void searchProducersWithProducts() {
        producersAndProducts.clear();
        for (Product product : products) {
            producersAndProducts.put(product.getProducer(), searchProducts(product.getProducer()));
        }
    }

    private ArrayList<Product> searchProducts(String producerName) {
        ArrayList<Product> filterProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getProducer().equals(producerName)) {
                filterProducts.add(product);
            }
        }

        return filterProducts;
    }

    private void save() {
        BinaryFileExecutor.saveProductsToFile(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    // a
    public List<Product> getAllSortedByShelfLifeWithName(String name) {
        return productsFilter.getAllSortedByShelfLifeWithName(name);
    }

    // b
    public List<Product> getProductsWithNameAndCostALess(String name, int maxCost) {
        return productsFilter.getProductsWithNameAndCostALess(name, maxCost);
    }

    // c
    public List<Product> getAllWithShelfLifeALong(LocalDate minShelfLife) {
        return productsFilter.getAllWithShelfLifeALong(minShelfLife);
    }

    // d
    public List<Product> getAllSortedByPrice() {
        return productsFilter.getAllSortedByPrice();
    }

    // e
    public Set<String> getProducers() {
        return producers;
    }

    // f
    public Map<String, ArrayList<Product>> getProducersWithThemProducts() {
        return producersAndProducts;
    }
}