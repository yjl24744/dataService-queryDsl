package com.yss.datamiddle.dao.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.BiField;
import com.yss.datamiddle.entities.QBiField;
import com.yss.datamiddle.entities.QBiTable;
import com.yss.datamiddle.util.BeanUtils;
import com.yss.datamiddle.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiFieldDaoImpl extends AbstractDataMiddleBaseDao<BiField> {

    public JPAQuery selectFieldVo(QBiField qBiField) {
        // select FID, FTABLE_ID, FFIELD_NAME, FFIELD_ALIAS, FFIELD_TYPE, FLENGTH, FSCALE, FPUBLIC_DIM, FLAG, FSHOW_FLAG, FSRC_FIELD, FGROUP_NAME from t_bi_field
        return this.queryFactory
                .select(
                        Projections.bean(
                                com.yss.datamiddle.vo.datamodel.BiField.class,
                                qBiField.id,
                                qBiField.tableId,
                                qBiField.fieldName,
                                qBiField.fieldAlias,
                                qBiField.fieldType,
                                qBiField.length,
                                qBiField.scale,
                                qBiField.publicDim,
                                qBiField.lag,
                                qBiField.showFlag,
                                qBiField.srcField,
                                qBiField.groupName
                        )
                )
                .from(qBiField);
    }

    /**
     * 查询数据主题表字段信息列表
     *
     * @param field 数据主题表字段信息信息
     * @return 数据主题表字段信息集合
     */
    public List<com.yss.datamiddle.vo.datamodel.BiField> selectFieldList(com.yss.datamiddle.vo.datamodel.BiField field) {
        /*<if test="id != null  and id != '' "> and FID = #{id}</if>
         <if test="tableId != null  and tableId != '' "> and FTABLE_ID = #{tableId}</if>
         <if test="fieldName != null  and fieldName != '' "> and FFIELD_NAME = #{fieldName}</if>
         <if test="fieldAlias != null  and fieldAlias != '' "> and FFIELD_ALIAS = #{fieldAlias}</if>
         <if test="publicDim != null  and publicDim != '' "> and FPUBLIC_DIM = #{publicDim}</if>
         <if test="flag != null  and flag != '' "> and FLAG = #{flag}</if>
         <if test="showFlag != null  and showFlag != '' "> and FSHOW_FLAG = #{showFlag}</if>
         <if test="srcField != null  and srcField != '' "> and FSRC_FIELD = #{srcField}</if>
         <if test="groupName != null  and groupName != '' "> and FGROUP_NAME = #{groupName}</if>*/
        QBiField qBiField = QBiField.biField;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(field.getId())) {
            booleanBuilder.and(qBiField.id.eq(field.getId()));
        }
        if (!StringUtils.isEmpty(field.getTableId())) {
            booleanBuilder.and(qBiField.tableId.eq(field.getTableId()));
        }
        if (!StringUtils.isEmpty(field.getFieldName())) {
            booleanBuilder.and(qBiField.fieldName.eq(field.getFieldName()));
        }
        if (!StringUtils.isEmpty(field.getFieldAlias())) {
            booleanBuilder.and(qBiField.fieldAlias.eq(field.getFieldAlias()));
        }
        if (!StringUtils.isEmpty(field.getPublicDim())) {
            booleanBuilder.and(qBiField.publicDim.eq(field.getPublicDim()));
        }
        if (!StringUtils.isEmpty(field.getFlag())) {
            booleanBuilder.and(qBiField.lag.eq(field.getFlag()));
        }
        if (!StringUtils.isEmpty(field.getShowFlag())) {
            booleanBuilder.and(qBiField.showFlag.eq(field.getShowFlag()));
        }
        if (!StringUtils.isEmpty(field.getSrcField())) {
            booleanBuilder.and(qBiField.srcField.eq(field.getSrcField()));
        }
        if (!StringUtils.isEmpty(field.getGroupName())) {
            booleanBuilder.and(qBiField.groupName.eq(field.getGroupName()));
        }
        JPAQuery jpaQuery = this.selectFieldVo(qBiField);
        jpaQuery.where(booleanBuilder);
        return jpaQuery.fetch();
    }

    /**
     * 查询数据主题表字段信息信息
     *
     * @param id 数据主题表字段信息ID
     * @return 数据主题表字段信息信息
     */
    public com.yss.datamiddle.vo.datamodel.BiField selectFieldById(String id) {
        QBiField qBiField = QBiField.biField;
        JPAQuery<com.yss.datamiddle.vo.datamodel.BiField> jpaQuery = this.selectFieldVo(qBiField);
        jpaQuery.where(qBiField.id.eq(id));
        return jpaQuery.fetchOne();
    }

    /**
     * 新增数据主题表字段信息
     *
     * @param field 数据主题表字段信息信息
     * @return 结果
     */
    public int insertField(com.yss.datamiddle.vo.datamodel.BiField field) {
        BiField biField = new BiField();
        BeanUtils.copyBeanProp(biField, field);
        this.persist(biField);
        return 1;
    }

    /**
     * 修改数据主题表字段信息
     *
     * @param field 数据主题表字段信息信息
     * @return 结果
     */
    public int updateField(com.yss.datamiddle.vo.datamodel.BiField field) {
        BiField biField = new BiField();
        BeanUtils.copyBeanProp(biField, field);
        this.merge(biField);
        return 1;
    }

    /**
     * 删除数据主题表字段信息
     *
     * @param id 数据主题表字段信息ID
     * @return 结果
     */
    public int deleteFieldById(String id) {
        QBiField qBiField = QBiField.biField;
        return (int) this.getQueryFactory()
                .delete(qBiField)
                .where(qBiField.id.eq(id))
                .execute();
    }

    /**
     * 批量删除数据主题表字段信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFieldByIds(String[] ids) {
        QBiField qBiField = QBiField.biField;
        return (int) this.getQueryFactory()
                .delete(qBiField)
                .where(qBiField.id.in(ids))
                .execute();
    }

    /**
     * @param tableId
     * @return
     */
    public int deleteFieldByTableId(String tableId) {
        QBiField qBiField = QBiField.biField;
        return (int) this.getQueryFactory()
                .delete(qBiField)
                .where(qBiField.tableId.eq(tableId))
                .execute();
    }

    /**
     * 查询数据主题表字段信息列表，数据权限
     *
     * @return 数据主题表字段信息集合
     */
    public List<com.yss.datamiddle.vo.datamodel.BiField> selectFields() {
        /*SELECT
        f.FID,
                f.FFIELD_NAME,
                f.FFIELD_ALIAS,
                t.FTABLE_NAME FTABLE_ID
                FROM
        t_bi_table t
        JOIN t_bi_field f ON t.FID = f.FTABLE_ID
        WHERE
        f.FSHOW_FLAG = 1 and t.table_flag = 0*/
        QBiField qBiField = QBiField.biField;
        QBiTable qBiTable = QBiTable.biTable;
        return this.queryFactory
                .select(
                        Projections.bean(
                                com.yss.datamiddle.vo.datamodel.BiField.class,
                                qBiField.id,
                                qBiField.fieldName,
                                qBiField.fieldAlias,
                                qBiTable.tableName.as("FTABLE_ID")
                        )
                )
                .from(qBiField)
                .join(qBiTable)
                .on(qBiField.tableId.eq(qBiTable.id))
                .where(
                        qBiField.showFlag.eq("1")
                                .and(qBiTable.tableFlag.eq("0")
                                )
                )
                .fetch();
    }
}





















