import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    final int tableSize = 191;
    final int doubleFactor = 181;

    OutputStream fout;

    ArrayList<Integer> data = new ArrayList<>();

    public Driver(int number) {
        try {
            testFile("src/in" + number + ".txt", "src/out" + number + ".txt");
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    public void testKeyValue(String description, HashInterface<Integer, Integer> hashTable, Integer key, Integer value) throws RuntimeException {
        final int previousCollisions = hashTable.getCollisions();

        hashTable.put(key, value);

        final Integer retrievedValue = hashTable.get(key);
        final String retrievedText = retrievedValue != null ? toString() : null; // I do not know if typed correctly

        if (retrievedValue == null || !(retrievedValue.equals(value))) {
            throw new RuntimeException("value mismatch");
        }
    }

    public void testInputKey(Integer key, HashInterface<Integer, Integer> lph) {
        Integer value = key * 2;
        testKeyValue("Linear", lph, key, value);
    }

    public void testData(String description) {
        LinearProbingHash<Integer, Integer> lph = new LinearProbingHash(tableSize);

        for (Integer key : data) {
            testInputKey(key, lph);
        }
    }

    public void readData(String inputFile) throws FileNotFoundException {
        // InputStream fin = new FileInputStream(inputFile);
        // Integer key

        Scanner scanner= new Scanner(new File(inputFile ));

        while (scanner.hasNext()) {
            data.add(scanner.nextInt());
        }

        scanner.close();
    }

    public void testFile(String inputFilename, String outputFilename) throws FileNotFoundException {
        System.out.println("Input file: " + inputFilename + ", output file: " + outputFilename);

        readData(inputFilename);
        fout = new FileOutputStream(outputFilename);

        testData("Random Order");

        // SORT_IN_ASCENDING_ORDER(data)
        // testData("Ascending Order");

        // SORT_IN_DESCENDING_ORDER
        // testData("Descending Order");

        // fout.close();

        System.out.println("Done");
    }

    public static void main(String[] args) {
        new Driver(150);
        new Driver(160);
        new Driver(170);
    }
}
