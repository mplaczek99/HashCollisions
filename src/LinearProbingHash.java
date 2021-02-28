import java.util.Arrays;

public class LinearProbingHash<Key, Value> implements HashInterface<Integer, Integer> {
    Record[] table;
    int collisions;

    private class Record { // I think the class should be private
        Integer key;
        Integer value;

        public Record(final Integer key1, final Integer value1) {
            key = key1;
            value = value1;
        }
    }

    public LinearProbingHash(int initialSize) { // constructor
        initialSize = 191;

        table = (Record[]) new LinearProbingHash.Record[initialSize]; // Workaround of Generic array creation error

        collisions = 0;
    }

    @Override
    public Integer get(final Integer key) {
        final int index = lookUp(key);

        if (index > table.length) {
            return null;
        }

        Record p = table[index];

        return p.value;
    }

    @Override
    public void put(final Integer key, final Integer value) throws RuntimeException {
        final int index = lookUp(key);

        if (index > table.length) {
            throw new RuntimeException("Table is full");
        }

        Record p = table[index];

        if (p == null) {
            table[index] = new Record(key, value);
        } else {
            p.value = value;
        }
    }

    @Override
    public final int getCollisions() {
        return collisions;
    }

    private static int hash(final Integer key) {
        // scramble the key via bitwise operations

        return (key >> 8) | ((key&0xff)<<16);
    }

    private final int hashIndex(final Integer key) {
        final int index = hash(key);

        return index % table.length;
    }

    private final int lookUp(Integer key) {
        final int startIndex = hashIndex(key);
        int index = startIndex;

        while (true) {
            final Record p = table[index];

            if (p == null || p.key.equals(key)) {
                return index;
            }

            collisions ++;
            index ++;
            index %= table.length;

            if (index == startIndex) {
                return table.length + 1;
            }
        }
    }
}
