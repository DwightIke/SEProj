package main.repository;

import java.util.*;

/**
 * Created by Dragos on 5/8/2017.
 */

//CRUD REPO sdsds

public interface ICRUDRepository<E> {
    void add(E e);
    E delete(int id);
    E findOne(int id);
    void update(int id, E e);
    Iterable<E> getAll();
}
