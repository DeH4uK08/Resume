package ua.tasks.den4uk.service;

import java.util.List;

public interface Service<T> {

    T getById(int id);

    List<T> getAll();

    T create(T t);

    T update(T t);

    boolean delete(int id);

}
