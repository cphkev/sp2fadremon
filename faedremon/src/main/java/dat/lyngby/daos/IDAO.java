package dat.lyngby.daos;

public interface IDAO <T>{
    T create(T t);
    T read(int id);
    T update(T t);
    boolean delete(int id);
}
