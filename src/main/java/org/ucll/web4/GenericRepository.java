package org.ucll.web4;

import java.util.List;

public interface GenericRepository<T,K> {

    void create(T entity,K key);

    T get(K key);

    void update(T entity);

    List<T> getAll();

    boolean exists(K key);
}
