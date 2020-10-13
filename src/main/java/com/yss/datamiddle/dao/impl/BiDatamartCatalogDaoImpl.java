package com.yss.datamiddle.dao.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.BiDatamartCatalog;
import com.yss.datamiddle.entities.QBiDatamartCatalog;
import com.yss.datamiddle.entities.QBiDatamartCatalogRe;
import com.yss.datamiddle.util.BeanUtils;
import com.yss.datamiddle.vo.datamart.DatamartCatalog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiDatamartCatalogDaoImpl extends AbstractDataMiddleBaseDao<BiDatamartCatalog> {

    public JPAQuery selectDatamartCatalogVo(QBiDatamartCatalog qBiDatamartCatalog) {
        return this.queryFactory
                .select(
                        Projections.bean(
                                DatamartCatalog.class,
                                qBiDatamartCatalog.id,
                                qBiDatamartCatalog.name,
                                qBiDatamartCatalog.desc,
                                qBiDatamartCatalog.path,
                                qBiDatamartCatalog.creatorId,
                                qBiDatamartCatalog.createTime,
                                qBiDatamartCatalog.lastEditorId,
                                qBiDatamartCatalog.lastEditTime,
                                qBiDatamartCatalog.deleteTime,
                                qBiDatamartCatalog.deleteState,
                                qBiDatamartCatalog.checkState,
                                qBiDatamartCatalog.checkerId,
                                qBiDatamartCatalog.checkTime
                        )
                )
                .from(qBiDatamartCatalog);
    }

    /**
     * 查询所有数据集市分类列表信息
     *
     * @return 数据集市分类集
     */
    public List<DatamartCatalog> selectDatamartCatalogList() {
        /*SELECT FID, FNAME, FDESC, FPATH, FCREATOR_ID, FCREATE_TIME,
                FLAST_EDITOR_ID,
                FLAST_EDIT_TIME, FDELETE_TIME,
                FDELETE_STATE,FCHECK_STATE,
        FCHECKER_ID, FCHECK_TIME from
                t_bi_datamart_catalog ORDER BY FCREATE_TIME*/
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        JPAQuery jpaQuery = this.selectDatamartCatalogVo(qBiDatamartCatalog);
        jpaQuery.orderBy(qBiDatamartCatalog.createTime.asc());
        return jpaQuery.fetch();
    }

    /**
     * 查询所有子类数据集市列表信息
     *
     * @parentPath 父路径
     * @name 集市名称
     * @return 数据集市分类集
     */
    public List<DatamartCatalog> selectDatamartCatalogChildList(String parentPath, String name) {
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        JPAQuery jpaQuery = this.selectDatamartCatalogVo(qBiDatamartCatalog);
        jpaQuery.where(
                qBiDatamartCatalog.path.like("%" + parentPath + "%")
                        .and(qBiDatamartCatalog.name.eq(name)));
        return jpaQuery.fetch();
    }

    /**
     * 查询所有数据集市分类ID列表信息
     *
     * @param path 分类路径
     * @return 数据集市分类集
     */
    public List<String> selectDatamartCatalogIdListByPath(String path) {
        /*SELECT FID FROM
                t_bi_datamart_catalog
        WHERE FPATH like '${path}'
        ORDER BY FCREATE_TIME*/
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        return this.queryFactory
                .select(qBiDatamartCatalog.id)
                .from(qBiDatamartCatalog)
                .where(
                        qBiDatamartCatalog.path.like("%" + path + "%")
                )
                .orderBy(qBiDatamartCatalog.createTime.asc())
                .fetch();
    }

    /**
     * 查询数据集市分类信息
     *
     * @param id 编码
     * @return 数据集市分类信息
     */
    public DatamartCatalog selectDatamartCatalogById(String id) {
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        JPAQuery<DatamartCatalog> jpaQuery = this.selectDatamartCatalogVo(qBiDatamartCatalog);
        jpaQuery.where(qBiDatamartCatalog.id.eq(id));
        return jpaQuery.fetchOne();
    }

    /**
     * 校验集市目录下是否存在数据
     *
     * @param path 分类路径
     * @return true:存在/false:不存在
     */
    public Boolean validateDataExistByPath(String path) {
        /*SELECT COUNT(1) FROM t_bi_datamart_catalog_re WHERE
        FDATAMART_ID IN (SELECT FID FROM t_bi_datamart_catalog WHERE FPATH
        LIKE '${path}')*/
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        long result = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiDatamartCatalogRe)
                .where(
                        qBiDatamartCatalogRe.datamartId.in(
                                JPAExpressions
                                        .select(qBiDatamartCatalog.id)
                                        .from(qBiDatamartCatalog)
                                        .where(qBiDatamartCatalog.path.like("%" + path + "%")
                                        )
                        )
                ).fetchCount();
        return result > 0 ? true : false;
    }

    /**
     * 添加数据集市分类信息
     *
     * @param datamartCatalog 数据集市分类信息
     * @return 结果
     */
    public int insertDatamartCatalog(DatamartCatalog datamartCatalog) {
        BiDatamartCatalog biDatamartCatalog = new BiDatamartCatalog();
        BeanUtils.copyBeanProp(biDatamartCatalog, datamartCatalog);
        this.persist(biDatamartCatalog);
        return 1;
    }

    /**
     * 更新数据集市分类信息
     *
     * @param datamartCatalog 数据集市分类信息
     * @return 结果
     */
    public void updateDatamartCatalog(DatamartCatalog datamartCatalog) {
        BiDatamartCatalog biDatamartCatalog = new BiDatamartCatalog();
        BeanUtils.copyBeanProp(biDatamartCatalog, datamartCatalog);
        this.merge(biDatamartCatalog);
    }

    /**
     * 更新集市子分类父路径信息
     *
     * @param oldPath 旧父路径
     * @param newPath 新父路径
     * @return 结果
     */
    public int updateDatamartCatalogByPath(String oldPath, String newPath) {
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        return (int) this.getQueryFactory()
                .update(qBiDatamartCatalog)
                .set(qBiDatamartCatalog.path, Expressions.template(String.class, "REPLACE(FPATH, "+oldPath+","+newPath+")"))
                .where(qBiDatamartCatalog.path.like("%" + newPath + "%"))
                .execute();
    }

    /**
     * 校验集市目录下是否存在数据
     *
     * @param path 分类路径
     * @return true:存在/false:不存在
     */
    public int deleteDatamartCatalogByPath(String path) {
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        return (int) this.getQueryFactory()
                .delete(qBiDatamartCatalog)
                .where(qBiDatamartCatalog.path.like("%" + path + "%"))
                .execute();
    }
}

















