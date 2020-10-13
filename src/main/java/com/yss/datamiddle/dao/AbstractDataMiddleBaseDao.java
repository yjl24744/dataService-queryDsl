package com.yss.datamiddle.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yss.datamiddle.vo.PageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
public abstract class AbstractDataMiddleBaseDao<T> implements DataMiddleBaseDao<T, Long> {

    @Autowired
    protected EntityManager entityManager;
    protected JPAQueryFactory queryFactory;
    protected ObjectMapper objectMapper;

    @PostConstruct
    void initFactory() {
        log.info("开始实例化JPAQueryFactory");
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @PostConstruct
    void objectMapperConfig() {
        if (objectMapper == null) objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获得查询工厂实例
     *
     * @return JPAQueryFactory
     */
    public JPAQueryFactory getQueryFactory() {
        if (queryFactory == null) initFactory();
        return queryFactory;
    }

    /**
     * 分页 返回 PageResult
     */
    public static <R, T> PageModel<T> retPage(QueryResults<R> queryResults) {
        PageModel<T> page = new PageModel<T>();
        page.setTotal(queryResults.getTotal());
        page.setPageNum((queryResults.getOffset() / queryResults.getLimit()) + 1);
        page.setPageSize(queryResults.getLimit());
        page.setList((List<T>) queryResults.getResults());
        return page;
    }


    /**
     * query all
     * @param entity
     * @return
     */
    protected <T> JPAQuery<T> selectFrom(EntityPath<T> entity) {
        return select(entity).from(entity);
    }

    protected <T> JPAQuery<T> select(Expression<T> select) {
        return new JPAQuery<>(entityManager, HQLTemplates.DEFAULT).select(select);
    }


    /**
     * 单个查找
     * @param type
     * @param id
     * @return
     */
    protected <E> E find(Class<E> type, Long id) {
        return entityManager.find(type, id);
    }

    /**
     * 插入
     */
    protected void persist(Object entity) {
        entityManager.persist(entity);
    }
    /**
     * 修改
     */
    protected <E> E merge(E entity) {
        return entityManager.merge(entity);
    }

    /**
     * 删除
     * @param entity
     */
    protected void remove(Object entity) {
        entityManager.remove(entity);
    }
}
