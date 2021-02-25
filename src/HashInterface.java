public interface HashInterface<I extends Number, I1 extends Number> {
    Integer get(Integer key);
    void put(Integer key, Integer value);
    int getCollisions();
}
