public interface HashInterface<I extends Number, I1 extends Number> {
    public Integer get(Integer key);
    public void put(Integer key, Integer value);
    public int getCollisions();
}
