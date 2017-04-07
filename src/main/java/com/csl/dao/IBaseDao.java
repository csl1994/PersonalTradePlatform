package com.csl.dao;

import java.util.List;

/**
 * Created by csl on 2017/3/7.
 */
public interface IBaseDao<T> {
    int save(final T t);

    int remove(final T t);

    int update(final T t);

    T find(final String ID);

    List<T> getAll();
}
