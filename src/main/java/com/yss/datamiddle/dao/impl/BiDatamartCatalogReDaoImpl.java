package com.yss.datamiddle.dao.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.BiDatamartCatalogPo;
import com.yss.datamiddle.entities.BiDatamartCatalogRe;
import com.yss.datamiddle.entities.QBiDatamartCatalogRe;
import com.yss.datamiddle.util.BeanUtils;
import com.yss.datamiddle.util.StringUtils;
import com.yss.datamiddle.vo.datamart.DatamartCatalogRe;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BiDatamartCatalogReDaoImpl extends AbstractDataMiddleBaseDao<BiDatamartCatalogRe> {

    /**
     * 查询集市分类数据关联信息
     *
     * @param datamartCatalogRe 关联信息
     * @return 数据关联信息列表
     */
    public List<DatamartCatalogRe> selectDatamartCatalogRe(DatamartCatalogRe datamartCatalogRe) {
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        JPAQuery jpaQuery = this.queryFactory
                .select(
                        Projections.bean(
                                DatamartCatalogRe.class,
                                qBiDatamartCatalogRe.id,
                                qBiDatamartCatalogRe.datamartId,
                                qBiDatamartCatalogRe.dataType,
                                qBiDatamartCatalogRe.relId
                        )
                )
                .from(qBiDatamartCatalogRe);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(datamartCatalogRe.getId())) {
            booleanBuilder.and(qBiDatamartCatalogRe.id.eq(datamartCatalogRe.getId()));
        }
        if (!StringUtils.isEmpty(datamartCatalogRe.getDatamartId())) {
            booleanBuilder.and(qBiDatamartCatalogRe.datamartId.eq(datamartCatalogRe.getDatamartId()));
        }
        if (!StringUtils.isEmpty(datamartCatalogRe.getDataType())) {
            booleanBuilder.and(qBiDatamartCatalogRe.dataType.eq(datamartCatalogRe.getDataType()));
        }
        if (!StringUtils.isEmpty(datamartCatalogRe.getRelId())) {
            booleanBuilder.and(qBiDatamartCatalogRe.relId.eq((datamartCatalogRe.getRelId())));
        }
        jpaQuery.where(booleanBuilder);
        return jpaQuery.fetch();
    }

    /**
     * 添加数据集市数据关联信息
     *
     * @param datamartCatalogRe 数据集市数据关联信息
     * @return 结果
     */
    public int insertDatamartCatalogRe(DatamartCatalogRe datamartCatalogRe) {
        BiDatamartCatalogPo biDatamartCatalog = new BiDatamartCatalogPo();
        BeanUtils.copyBeanProp(biDatamartCatalog, datamartCatalogRe);
        this.persist(biDatamartCatalog);
        return 1;
    }

    /**
     * 添加数据集市数据关联信息
     *
     * @param datamartCatalogRes 数据集市数据关联信息列表
     * @return 结果
     */
    public int insertDatamartCatalogReByList(List<DatamartCatalogRe> datamartCatalogRes) {
        for (DatamartCatalogRe datamartCatalogRe : datamartCatalogRes) {
            BiDatamartCatalogPo biDatamartCatalog = new BiDatamartCatalogPo();
            BeanUtils.copyBeanProp(biDatamartCatalog, datamartCatalogRe);
            this.persist(biDatamartCatalog);
        }
        return datamartCatalogRes.size();
    }

    /**
     * 更新数据集市数据关联信息
     *
     * @param datamartCatalogRe 数据集市分类信息
     * @return 结果
     */
    public int updateDatamartCatalogRe(DatamartCatalogRe datamartCatalogRe) {
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        List<StringPath> columnList = new LinkedList<>();
        List<String> valueList = new LinkedList<>();
        if (!StringUtils.isEmpty(datamartCatalogRe.getDatamartId())) {
            columnList.add(qBiDatamartCatalogRe.datamartId);
            valueList.add(datamartCatalogRe.getDatamartId());
        }
        if (!StringUtils.isEmpty(datamartCatalogRe.getDataType())) {
            columnList.add(qBiDatamartCatalogRe.dataType);
            valueList.add(datamartCatalogRe.getDataType());
        }
        if (!StringUtils.isEmpty(datamartCatalogRe.getRelId())) {
            columnList.add(qBiDatamartCatalogRe.relId);
            valueList.add(datamartCatalogRe.getRelId());
        }
        return (int) this.getQueryFactory()
                .update(qBiDatamartCatalogRe)
                .set(columnList, valueList)
                .where(qBiDatamartCatalogRe.id.eq(datamartCatalogRe.getId()))
                .execute();
    }

    /**
     * 删除数据主题数据集市数据关联信息
     * @param dataType 数据类型
     * @param relIds 关联数据ID
     * @return 结果
     */
    public int deleteDataModelReByRelids(String dataType, List<String> relIds) {
        /*DELETE
                FROM
        t_bi_datamart_catalog_re WHERE FDATA_TYPE =#{dataType} AND
        FREL_ID IN
		<foreach item="item" collection="relIds" open="(" separator=","
        close=")">
			#{item}
		</foreach>*/
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        return (int) this.getQueryFactory()
                .delete(qBiDatamartCatalogRe)
                .where(qBiDatamartCatalogRe.dataType.eq(dataType)
                        .and(qBiDatamartCatalogRe.relId.in(relIds)))
                .execute();
    }
}






















