package com.wang.database.mybatis.unit.dao;

import java.util.List;

public interface DaoBase<T> {

	public void insert(T t);

	public void delete(Long id);

	public void update(T t);

	public void updateWithNullValue(T t);

	public T queryById(Long id);

	public List<T> queryByExample(T t);
}
