package com.yss.datamiddle.dao.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.BiFieldjoin;
import com.yss.datamiddle.entities.QBiFieldjoin;
import com.yss.datamiddle.util.BeanUtils;
import com.yss.datamiddle.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BiFieldjoinDaoImpl extends AbstractDataMiddleBaseDao<BiFieldjoin> {

    public JPAQuery selectFieldjoinVo(QBiFieldjoin qBiFieldjoin) {
        /*select FID, FDS_ID, FTAR_DS_ID, FSOURCE_TABLE,
                FSOURCE_FIELD, FTARGET_TABLE,
                FTARGET_FIELD, FRELATION, FOPERATION,
        FCREATE_TIME from t_bi_fieldjoin*/
        return this.queryFactory
                .select(
                        Projections.bean(
                                com.yss.datamiddle.vo.datamodel.BiFieldjoin.class,
                                qBiFieldjoin.id,
                                qBiFieldjoin.dsId,
                                qBiFieldjoin.tarDsId,
                                qBiFieldjoin.sourceTable,
                                qBiFieldjoin.sourceField,
                                qBiFieldjoin.targetTable,
                                qBiFieldjoin.targetField,
                                qBiFieldjoin.relation,
                                qBiFieldjoin.operation
                        )
                )
                .from(qBiFieldjoin);
    }

    /**
     * 查询关联字段关系列表
     *
     * @param fieldjoin 关联字段关系信息
     * @return 关联字段关系集合
     */
    public List<com.yss.datamiddle.vo.datamodel.BiFieldjoin> selectFieldjoinList(com.yss.datamiddle.vo.datamodel.BiFieldjoin fieldjoin) {
        /*<if test="id != null  and id != '' "> and FID = #{id}</if>
        <if test="dsId != null  and dsId != '' "> and FDS_ID = #{dsId}</if>
        <if test="tarDsId != null  and tarDsId != '' "> and FTAR_DS_ID = #{tarDsId}</if>
        <if test="sourceTable != null  and sourceTable != '' "> and FSOURCE_TABLE = #{sourceTable}</if>
        <if test="sourceField != null  and sourceField != '' "> and FSOURCE_FIELD = #{sourceField}</if>
        <if test="targetTable != null  and targetTable != '' "> and FTARGET_TABLE = #{targetTable}</if>
        <if test="targetField != null  and targetField != '' "> and FTARGET_FIELD = #{targetField}</if>
        <if test="relation != null  and relation != '' "> and FRELATION = #{relation}</if>
        <if test="operation != null  and operation != '' "> and FOPERATION = #{operation}</if>
        <if test="createTime != null  and createTime != '' "> and FCREATE_TIME = #{createTime}</if>*/
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        JPAQuery jpaQuery = this.selectFieldjoinVo(qBiFieldjoin);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(fieldjoin.getId())) {
            booleanBuilder.and(qBiFieldjoin.id.eq(fieldjoin.getId()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getDsId())) {
            booleanBuilder.and(qBiFieldjoin.dsId.eq(fieldjoin.getDsId()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getTarDsId())) {
            booleanBuilder.and(qBiFieldjoin.tarDsId.eq(fieldjoin.getTarDsId()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getSourceTable())) {
            booleanBuilder.and(qBiFieldjoin.sourceTable.eq(fieldjoin.getSourceTable()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getSourceField())) {
            booleanBuilder.and(qBiFieldjoin.sourceField.eq(fieldjoin.getSourceField()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getTargetTable())) {
            booleanBuilder.and(qBiFieldjoin.targetTable.eq(fieldjoin.getTargetTable()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getTargetField())) {
            booleanBuilder.and(qBiFieldjoin.targetField.eq(fieldjoin.getTargetField()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getRelation())) {
            booleanBuilder.and(qBiFieldjoin.relation.eq(fieldjoin.getRelation()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getOperation())) {
            booleanBuilder.and(qBiFieldjoin.operation.eq(fieldjoin.getOperation()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getCreateTime())) {
            booleanBuilder.and(qBiFieldjoin.createTime.eq(fieldjoin.getCreateTime()));
        }
        jpaQuery.where(booleanBuilder);
        return jpaQuery.fetch();
    }

    /**
     * 查询关联字段关系信息
     *
     * @param fID 关联字段关系ID
     * @return 关联字段关系信息
     */
    public com.yss.datamiddle.vo.datamodel.BiFieldjoin selectFieldjoinById(String fID) {
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        JPAQuery<com.yss.datamiddle.vo.datamodel.BiFieldjoin> jpaQuery = this.selectFieldjoinVo(qBiFieldjoin);
        jpaQuery.where(qBiFieldjoin.id.eq(fID));
        return jpaQuery.fetchOne();
    }

    /**
     * 新增关联字段关系
     *
     * @param fieldjoin 关联字段关系信息
     * @return 结果
     */
    public int insertFieldjoin(com.yss.datamiddle.vo.datamodel.BiFieldjoin fieldjoin) {
        BiFieldjoin biFieldjoin = new BiFieldjoin();
        BeanUtils.copyBeanProp(biFieldjoin, fieldjoin);
        this.persist(biFieldjoin);
        return 1;
    }

    /**
     * 修改关联字段关系
     *
     * @param fieldjoin 关联字段关系信息
     * @return 结果
     */
    public int updateFieldjoin(com.yss.datamiddle.vo.datamodel.BiFieldjoin fieldjoin) {
        BiFieldjoin biFieldjoin = new BiFieldjoin();
        BeanUtils.copyBeanProp(biFieldjoin, fieldjoin);
        this.merge(biFieldjoin);
        return 1;
    }

    /**
     * 删除关联字段关系集
     *
     * @param fieldjoin 关联字段关系信息
     * @return 结果
     */
    public int deleteFieldjoins(com.yss.datamiddle.vo.datamodel.BiFieldjoin fieldjoin) {
        /*delete from t_bi_fieldjoin where 1=1
                <if test="dsId != null  and dsId != ''  "> AND FDS_ID = #{dsId}</if>
		<if test="tarDsId != null  and tarDsId != ''  "> AND FTAR_DS_ID = #{tarDsId}</if>
		<if test="sourceTable != null  and sourceTable != ''  "> AND FSOURCE_TABLE = #{sourceTable}</if>
		<if test="targetTable != null  and targetTable != ''  "> AND FTARGET_TABLE = #{targetTable}</if>*/
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(fieldjoin.getDsId())) {
            booleanBuilder.and(qBiFieldjoin.dsId.eq(fieldjoin.getDsId()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getTarDsId())) {
            booleanBuilder.and(qBiFieldjoin.tarDsId.eq(fieldjoin.getTarDsId()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getSourceTable())) {
            booleanBuilder.and(qBiFieldjoin.sourceTable.eq(fieldjoin.getSourceTable()));
        }
        if (!StringUtils.isEmpty(fieldjoin.getTargetTable())) {
            booleanBuilder.and(qBiFieldjoin.targetTable.eq(fieldjoin.getTargetTable()));
        }
        return (int) this.getQueryFactory()
                .delete(qBiFieldjoin)
                .where(booleanBuilder)
                .execute();
    }

    /**
     * 删除关联字段关系集
     *
     * @param dsIds 数据源id集
     * @return 结果
     */
    public int deleteFieldjoinsByDsId(String[] dsIds) {
        /*delete from t_bi_fieldjoin where 1=1
        and (FDS_ID in
                        <foreach item="dsId" collection="array" open="(" separator=","
                close=")">
                #{dsId}
		</foreach>
                or FTAR_DS_ID in
		<foreach item="dsId" collection="array" open="(" separator=","
        close=")">
			#{dsId}
		</foreach>)*/
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        return (int) this.getQueryFactory()
                .delete(qBiFieldjoin)
                .where(
                        qBiFieldjoin.dsId.in(dsIds)
                        .or(qBiFieldjoin.tarDsId.in(dsIds)
                        )
                )
                .execute();
    }

    /**
     * 删除关联字段关系
     *
     * @param fID 关联字段关系ID
     * @return 结果
     */
    public int deleteFieldjoinById(String fID) {
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        return (int) this.getQueryFactory()
                .delete(qBiFieldjoin)
                .where(qBiFieldjoin.id.eq(fID))
                .execute();
    }

    /**
     * 批量删除关联字段关系
     *
     * @param fIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteFieldjoinByIds(String[] fIDs) {
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        return (int) this.getQueryFactory()
                .delete(qBiFieldjoin)
                .where(qBiFieldjoin.id.in(fIDs))
                .execute();
    }

    public List<com.yss.datamiddle.vo.datamodel.BiFieldjoin> selectFieldjoinsByTableName(String dsId,
                                                                                         String tableName) {
        /*<where>
                FDS_ID = #{dsId} and (FSOURCE_TABLE = #{tableName} or
                FTARGET_TABLE =
			#{tableName}) order by FCREATE_TIME desc
		</where>*/
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        JPAQuery jpaQuery = this.selectFieldjoinVo(qBiFieldjoin);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder
                .and(qBiFieldjoin.dsId.eq(dsId))
                .and(
                        qBiFieldjoin.sourceTable.eq(tableName)
                                .or(qBiFieldjoin.targetTable.eq(tableName)
                                )
                );
        jpaQuery.where(booleanBuilder).orderBy(qBiFieldjoin.createTime.desc());
        return jpaQuery.fetch();
    }

    /**
     * 获取数据表相关的连接关系列表
     *
     * @param dsId 源数据源或目标数据源
     * @param tableName 表名
     * @param dsIdList 数据源ID列表,格式:id,id
     * @param tableList 数据表名列表,格式：name,name
     * @return 连接关系列表
     */
    public List<com.yss.datamiddle.vo.datamodel.BiFieldjoin> selectByMultiDsTableName(String dsId,
                                                                                      String tableName,
                                                                                      Set<String> dsIdList,
                                                                                      Set<String> tableList) {
        QBiFieldjoin qBiFieldjoin = QBiFieldjoin.biFieldjoin;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanBuilder booleanBuilder1 = new BooleanBuilder();
        booleanBuilder1
                .and(
                        qBiFieldjoin.dsId.eq(dsId)
                                .and(qBiFieldjoin.sourceTable.eq(tableName))
                )
                .or(
                        qBiFieldjoin.tarDsId.eq(dsId)
                                .and(qBiFieldjoin.targetTable.eq(tableName))
                );
        BooleanBuilder booleanBuilder2 = new BooleanBuilder();
        if (dsIdList != null && dsIdList.size() > 0) {
            booleanBuilder2
                    .and(
                            qBiFieldjoin.dsId.in(dsIdList)
                                    .or(qBiFieldjoin.tarDsId.in(dsIdList))
                    );
        }
        BooleanBuilder booleanBuilder3 = new BooleanBuilder();
        if (tableList != null && tableList.size() > 0) {
            booleanBuilder3
                    .and(
                            qBiFieldjoin.sourceTable.in(tableList)
                                    .or(qBiFieldjoin.targetTable.in(tableList))
                    );
        }
        booleanBuilder.and(booleanBuilder1).and(booleanBuilder2).and(booleanBuilder3);
        JPAQuery jpaQuery = this.selectFieldjoinVo(qBiFieldjoin);
        jpaQuery.where(booleanBuilder).orderBy(qBiFieldjoin.createTime.desc());
        return jpaQuery.fetch();
    }
}
























