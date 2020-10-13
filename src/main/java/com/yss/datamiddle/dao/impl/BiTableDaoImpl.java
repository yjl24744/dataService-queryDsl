package com.yss.datamiddle.dao.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.BiTable;
import com.yss.datamiddle.entities.QBiTable;
import com.yss.datamiddle.util.BeanUtils;
import com.yss.datamiddle.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiTableDaoImpl extends AbstractDataMiddleBaseDao<BiTable> {

    public JPAQuery selectTableVo(QBiTable qBiTable) {
        // select FID, FTABLE_NAME, FTABLE_ALIAS, FDS_ID, FISFACT, FTITLE_ROW,FGROUP from t_bi_table
        return this.queryFactory.select(
                Projections.bean(
                        com.yss.datamiddle.vo.datamodel.BiTable.class,
                        qBiTable.id,
                        qBiTable.tableName,
                        qBiTable.tableAlias,
                        qBiTable.dsId,
                        qBiTable.isfact,
                        qBiTable.titleRow,
                        qBiTable.group
                )
        ).from(qBiTable);
    }

    /**
     * 查询数据主题表列表
     *
     * @param table 数据主题表信息
     * @return 数据主题表集合
     */
    public List<com.yss.datamiddle.vo.datamodel.BiTable> selectTableList(com.yss.datamiddle.vo.datamodel.BiTable table) {
        QBiTable qBiTable = QBiTable.biTable;
        JPAQuery jpaQuery = this.selectTableVo(qBiTable);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(table.getId())) {
            booleanBuilder.and(qBiTable.id.eq(table.getId()));
        }
        if (!StringUtils.isEmpty(table.getTableName())) {
            BooleanBuilder booleanBuilder1 = new BooleanBuilder();
            if (!StringUtils.isEmpty(table.getDsId())) {
                booleanBuilder1.and(qBiTable.dsId.in(table.getDsIds()));
            }
            booleanBuilder.and(qBiTable.tableName.toUpperCase().like("%" + table.getTableName().toUpperCase() + "%")
                    .or(qBiTable.tableAlias.toUpperCase().like("%" + table.getTableName() + "%"))
                    .or(booleanBuilder1));
        }
        if (!StringUtils.isEmpty(table.getTableAlias())) {
            booleanBuilder.and(qBiTable.tableAlias.eq(table.getTableAlias()));
        }
        if (!StringUtils.isEmpty(table.getDsId())) {
            booleanBuilder.and(qBiTable.tableAlias.eq(table.getDsId()));
        }
        if (!StringUtils.isEmpty(table.getIsfact())) {
            booleanBuilder.and(qBiTable.isfact.eq(table.getIsfact()));
        }
        if (!StringUtils.isEmpty(table.getTitleRow())) {
            booleanBuilder.and(qBiTable.titleRow.eq(table.getTitleRow()));
        }
        if (!StringUtils.isEmpty(table.getTableNameExpr())) {
            booleanBuilder.and(qBiTable.group.eq(table.getTableNameExpr()));
        }
        jpaQuery
                .where(booleanBuilder)
                .orderBy(qBiTable.id.asc());
        return jpaQuery.fetch();
    }

    /**
     * 查询数据主题表信息
     *
     * @param fID 数据主题表ID
     * @return 数据主题表信息
     */
    public com.yss.datamiddle.vo.datamodel.BiTable selectTableById(String fID) {
        QBiTable qBiTable = QBiTable.biTable;
        JPAQuery<com.yss.datamiddle.vo.datamodel.BiTable> jpaQuery = this.selectTableVo(qBiTable);
        jpaQuery.where(qBiTable.id.eq(fID));
        return jpaQuery.fetchOne();
    }

    /**
     * 查询数据主题表信息
     *
     * @param fids 数据主题表ID
     * @return 数据主题表信息
     */
    public List<com.yss.datamiddle.vo.datamodel.BiTable> selectTableByIds(String[] fids) {
        QBiTable qBiTable = QBiTable.biTable;
        JPAQuery jpaQuery = this.selectTableVo(qBiTable);
        jpaQuery.where(qBiTable.id.in(fids));
        return jpaQuery.fetch();
    }

    /**
     * 新增数据主题表
     *
     * @param table 数据主题表信息
     * @return 结果
     */
    public int insertTable(com.yss.datamiddle.vo.datamodel.BiTable table) {
        BiTable biTable = new BiTable();
        BeanUtils.copyBeanProp(biTable, table);
        this.persist(biTable);
        return 1;
    }

    /**
     * 修改数据主题表
     *
     * @param table 数据主题表信息
     * @return 结果
     */
    public int updateTable(com.yss.datamiddle.vo.datamodel.BiTable table) {
        BiTable biTable = new BiTable();
        BeanUtils.copyBeanProp(biTable, table);
        this.merge(biTable);
        return 1;
    }

    /**
     * 删除数据主题表
     *
     * @param fID 数据主题表ID
     * @return 结果
     */
    public int deleteTableById(String fID) {
        QBiTable qBiTable = QBiTable.biTable;
        return (int) this.getQueryFactory()
                .delete(qBiTable)
                .where(qBiTable.id.eq(fID))
                .execute();
    }

    /**
     * 批量删除数据主题表
     *
     * @param fIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteTableByIds(String[] fIDs) {
        QBiTable qBiTable = QBiTable.biTable;
        return (int) this.getQueryFactory()
                .delete(qBiTable)
                .where(qBiTable.id.in(fIDs))
                .execute();
    }

    /**
     * 删除数据源表信息
     *
     * @param dsId 数据源id
     * @param tableName 分组名(表名)
     * @return 结果
     */
    public int deleteTableByDsTableName(String dsId,
                                        String tableName) {
        QBiTable qBiTable = QBiTable.biTable;
        return (int) this.getQueryFactory()
                .delete(qBiTable)
                .where(qBiTable.dsId.eq(dsId)
                        .and(qBiTable.tableName.eq(tableName)))
                .execute();
    }

    /**
     * 根据数据源ID查询数据主题表
     *
     * @param dsId 数据源ID
     * @return 结果
     */
    public List<com.yss.datamiddle.vo.datamodel.BiTable> selectTablesByDsId(String dsId) {
        QBiTable qBiTable = QBiTable.biTable;
        JPAQuery jpaQuery = this.selectTableVo(qBiTable);
        jpaQuery.where(qBiTable.dsId.eq(dsId));
        return jpaQuery.fetch();
    }

    /**
     * 根据表名和数据源ID，查找表记录
     *
     * @param tableName 表名
     * @param srcDsId 源数据源ID
     * @param tarDsId 目标数据源ID
     * @return 表列表
     */
    public List<com.yss.datamiddle.vo.datamodel.BiTable> selectTablesByDsIdAndTableName(String tableName,
                                                                                        String srcDsId,
                                                                                        String tarDsId) {
        QBiTable qBiTable = QBiTable.biTable;
        JPAQuery jpaQuery = this.selectTableVo(qBiTable);
        jpaQuery.where(qBiTable.tableName.eq(tableName)
                .and(qBiTable.dsId.eq(srcDsId)
                        .or(qBiTable.dsId.eq(tarDsId))));
        return jpaQuery.fetch();
    }

    /**
     * 根据表名和数据源ID，查找表记录
     *
     * @param tableName 表名
     * @param srcDsId 源数据源ID
     * @return 表列表
     */
    public List<com.yss.datamiddle.vo.datamodel.BiTable> selectTableByDsIdAndTableName(String tableName,
                                                                                       String srcDsId) {
        /*where upper(FTABLE_NAME) = upper(#{tableName})
        and FDS_ID = #{srcDsId}*/
        QBiTable qBiTable = QBiTable.biTable;
        JPAQuery jpaQuery = this.selectTableVo(qBiTable);
        jpaQuery
                .where(
                        qBiTable.tableName.toUpperCase().eq(tableName.toUpperCase())
                                .and(qBiTable.dsId.eq(srcDsId)));
        return jpaQuery.fetch();
    }

    /**
     * 根据数据源ID，查找数据源过滤分组记录
     *
     * @param dsId 数据源ID
     * @return 表列表
     */
    public List<com.yss.datamiddle.vo.datamodel.BiTable> selectGroupTablesByDsId(String dsId) {
        QBiTable qBiTable = QBiTable.biTable;
        JPAQuery jpaQuery = this.selectTableVo(qBiTable);
        jpaQuery
                .where(
                        qBiTable.dsId.eq(dsId)
                                .and(qBiTable.group.isNotNull()));
        return jpaQuery.fetch();
    }
}














