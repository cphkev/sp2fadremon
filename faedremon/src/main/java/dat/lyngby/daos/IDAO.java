package dat.lyngby.daos;

import java.util.List;

public interface IDAO <T>{
    T create(T t);
    T getById(int id);
    T update(T t,T n);
    void delete(int id);

    List<T> getAll();
}
