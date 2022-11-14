package main.java.DAO;

import java.util.List;

public interface DAO<T> {
    DataSource ds = DataSource.getInstance();

    public void Insert (T t);
    public void update (T t);
    public void delete (int id);
    public T findByID (int id);
    public List<T> findAll ();
    default public void close(){ds.closeConnection();}
}
