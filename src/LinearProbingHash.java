public class LinearProbingHash<key, value> implements HashInterface {
    private class Record {
        public Integer key;
        public Integer value;

        public Record(Integer key1, Integer value1) {
            key = key1;
            value = value1;
        }
    }

    public LinearProbingHash(int initialSize) {
        initialSize = 191;
        //table = new
    }

    @Override
    public Integer get(Integer key) {
        return 1;
    }

    @Override
    public void put(Integer key, Integer value) {

    }

    @Override
    public int getCollisions() {
        return 0;
    }
}
