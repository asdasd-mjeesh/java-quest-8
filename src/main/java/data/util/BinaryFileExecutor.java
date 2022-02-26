package data.util;

import domain.store.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class BinaryFileExecutor {
    private final static String FILE_PATH = "file.path";

    private BinaryFileExecutor() {  }

    public static ArrayList<Product> readFile() {
        ArrayList<Product> productList = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(PropertiesUtil.get(FILE_PATH))))
        {
            productList = (ArrayList<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PropertiesUtil.get(FILE_PATH)))) {

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return productList;
    }

    public static void saveProductsToFile(List<Product> products) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PropertiesUtil.get(FILE_PATH)))) {
            out.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        var items = readFile();
        items.removeIf(s -> s.getId() == id);

        final String SECOND_FILE = "asd.dat";

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SECOND_FILE))) {
            out.writeObject(items);
            File mainlyFile = new File(PropertiesUtil.get(FILE_PATH));
            mainlyFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File secondFile = new File(SECOND_FILE);
        File newFile = new File(PropertiesUtil.get(FILE_PATH));

        secondFile.renameTo(newFile);

        /*             ещё один способ переименовать файл
        try {
            Path source = Paths.get("asd.dat");
            Files.move(source, source.resolveSibling(FILE_PATH));
        }catch(Exception e) {
            System.out.println(e);
        }*/
    }
}
