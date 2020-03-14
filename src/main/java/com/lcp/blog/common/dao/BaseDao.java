package com.lcp.blog.common.dao;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public abstract class BaseDao<T> {
    protected static final String CACHE_NAME = "dao.select";

    /**
     * 子类实现方法，传递注入的mapper
     *
     * @return
     */
    abstract protected Mapper<T> getMapper();

    public int deleteByPrimaryKey(Object key) {
        return this.getMapper().deleteByPrimaryKey(key);
    }

    public int delete(T entity) {
        return this.getMapper().delete(entity);
    }

    public int insert(T entity) {
        return this.getMapper().insert(entity);
    }

    public int insertSelective(T entity) {
        return this.getMapper().insertSelective(entity);
    }

    public boolean existsWithPrimaryKey(Object entity) {
        return this.getMapper().existsWithPrimaryKey(entity);
    }

    public List<T> selectAll() {
        return this.getMapper().selectAll();
    }

    public T selectByPrimaryKey(Object key) {
        return this.getMapper().selectByPrimaryKey(key);
    }

    public int selectCount(T entity) {
        return this.getMapper().selectCount(entity);
    }

    public List<T> select(T entity) {
        return this.getMapper().select(entity);
    }

    public T selectOne(T entity) {
        return this.getMapper().selectOne(entity);
    }

    public int updateByPrimaryKey(T entity) {
        return this.getMapper().updateByPrimaryKey(entity);
    }

    public int updateByPrimaryKeySelective(T entity) {
        return this.getMapper().updateByPrimaryKeySelective(entity);
    }

    public int deleteByExample(Object o) {
        return this.getMapper().deleteByExample(o);
    }

    public List<T> selectByExample(Object o) {
        return this.getMapper().selectByExample(o);
    }

    public int selectCountByExample(Object o) {
        return this.getMapper().selectCountByExample(o);
    }

    public T selectOneByExample(Object o) {
        return this.getMapper().selectOneByExample(o);
    }

    public int updateByExample(T entity, Object o) {
        return this.getMapper().updateByExample(entity, o);
    }

    public int updateByExampleSelective(T entity, Object o) {
        return this.getMapper().updateByExampleSelective(entity, o);
    }

    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return this.getMapper().selectByExampleAndRowBounds(o, rowBounds);
    }

    public List<T> selectByRowBounds(T entity, RowBounds rowBounds) {
        return this.getMapper().selectByRowBounds(entity, rowBounds);
    }
}
