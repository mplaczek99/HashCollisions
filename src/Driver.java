import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    final int tableSize = 191; // hash table size
    final int doubleFactor = 181; // factor R to be used in double hashing

    FileOutputStream fout; // declare and create an output stream
    ArrayList<Integer> data = new ArrayList<>(); // all keys from the input file

    public Driver(int number) {
        try {
            testFile("src/in" + number + ".txt", "src/out" + number + ".txt");
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    public void testKeyValue(String description, HashInterface<Integer, Integer> hashTable, final Integer key, final Integer value) throws RuntimeException {
        final int previousCollisions = hashTable.getCollisions();

        hashTable.put(key, value);

        final Integer retrievedValue = hashTable.get(key);
        final String retrievedText = retrievedValue.toString(); // email / discord someone about this

        System.out.print(key + " : " + value + " --> " + retrievedText + ", collisions " + (hashTable.getCollisions() - previousCollisions) + "\n");

        if (retrievedValue == null || !(retrievedValue.equals(value))) {
            System.out.print("Retrieved value " + retrievedText + " does not match stored value"  + value + " for key " + key + "\n");
            throw new RuntimeException("value mismatch");
        }
    }

    public void testInputKey(final Integer key, HashInterface<Integer, Integer> lph) { // Removed other hashing
        final Integer value = key * 2;

        testKeyValue("Linear", lph, key, value);
        // testKeyValue("Quadratic", qph, key, value);
        // testKeyValue("Double", lph, key, value);

        // Removed other hashing

        System.out.print("\n");
    }

    public void testData(final String description) {
        System.out.print("*** " + description + " Start ***" + "\n\n");

        LinearProbingHash<Integer, Integer> lph = new LinearProbingHash(tableSize);
        // Removed other hashing

        for (Integer key : data) {
            testInputKey(key, lph);
        }

        System.out.print("Linear    " + lph.getCollisions() + " collisions" + "\n");
        // System.out.print("Quadratic " + qph.getCollisions() + " collisions" + "\n");
        // System.out.print("Double    " + dhph.getCollisions() + " collisions" + "\n");

        System.out.print("\n" + "*** " + description + " End ***" + "\n\n");
    }

    public void readData(final String inputFile) throws FileNotFoundException {
        Scanner scanner= new Scanner(new File(inputFile ));

        while (scanner.hasNext()) {
            data.add(scanner.nextInt());
        }

        scanner.close();
    }

    public void testFile(final String inputFilename, final String outputFilename) throws IOException {
        readData(inputFilename);
        fout = new FileOutputStream(outputFilename);
        PrintStream ps = new PrintStream(fout);
        System.setOut(ps);

        //System.out.println("Input file: " + inputFilename + ", output file: " + outputFilename);

        testData("Random Order");

        // SORT_IN_ASCENDING_ORDER(data)
        // testData("Ascending Order");

        // SORT_IN_DESCENDING_ORDER
        // testData("Descending Order");

        fout.close();

        //System.out.println("Done");
    }

    public static void main(String[] args) {
        new Driver(150);
        new Driver(160);
        new Driver(170);
    }
}
