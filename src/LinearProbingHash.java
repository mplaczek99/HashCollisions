import java.util.ArrayList;

public class LinearProbingHash<Key, Value> implements HashInterface<Integer, Integer> {
    ArrayList<Record> table; // vector or array type
    int collisions;

    private class Record {
        Integer key;
        Integer value;

        public Record(final Integer key1, final Integer value1) {
            key = key1;
            value = value1;
        }
    }

    public LinearProbingHash(int initialSize) { // constructor
        initialSize = 191;

        table = new ArrayList<>(initialSize);
        System.out.println(table);
        collisions = 0;
    }

    @Override
    public Integer get(final Integer key) {
        final int index = lookUp(key);

        if (index > table.size()) {
            return null;
        }

        Record p = table.get(index);

        return p.value;
    }

    @Override
    public void put(final Integer key, final Integer value) throws RuntimeException {
        final int index = lookUp(key);

        if (index > table.size()) {
            throw new RuntimeException("Table is full");
        }

        Record p = table.get(index);

        if (p == null) {
            table.set(index, new Record(key, value));
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
        // for simplicity, it is assumed that Key is some kind of number

        return (key >> 8) | ((key&0xff)<<16);
    }

    private final int hashIndex(final Integer key) {
        final int index = hash(key);

        // Stops here? , table is not a thing
        System.out.println(index % table.size());
        return index % table.size();
    }

    private final int lookUp(Integer key) {
        final int startIndex = hashIndex(key);
        int index = startIndex;

        while (true) {
            final Record p = table.get(index);

            if (p == null || p.key.equals(key)) {
                return index;
            }

            collisions ++;
            index ++;
            index %= table.size();

            if (index == startIndex) {
                return table.size() + 1;
            }
        }
    }
}
