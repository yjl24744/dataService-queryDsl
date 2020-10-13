package com.yss.datamiddle.dao.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.*;
import com.yss.datamiddle.util.BeanUtils;
import com.yss.datamiddle.util.StringUtils;
import com.yss.datamiddle.vo.datamodel.DataModel;
import com.yss.datamiddle.vo.datamodel.DataModelVo;
import com.yss.datamiddle.vo.datamodel.ThemeVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BiDatamodelDaoImpl extends AbstractDataMiddleBaseDao<BiDatamodel> {

    public JPAQuery selectDataModelVo(QBiDatamodel qBiDatamodel) {
        /*select a.fid, a.fcode, a.fname, a.fversion,
                a.fdesc,a.fschema,a.fcontent,a.fparameters,a.fdata_type,a.fsrc_ds_id,a.fds_id,a.fdm_state,
                a.fcreator_id, a.fcreate_time, a.flast_editor_id, a.flast_edit_time,
                a.fdelete_time,
                a.fdelete_state, a.fcheck_state, a.fchecker_id,
                a.fcheck_time,a.fextension,a.fsql,a.fserviceparams,dmr.FDATAMART_ID
        from
        t_bi_datamodel a
        left join t_bi_datamart_catalog_re dmr on a.FID = dmr.FREL_ID and
        dmr.FDATA_TYPE=0
        where 1=1*/
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        return this.queryFactory
                .select(
                        Projections.bean(
                                DataModel.class,
                                qBiDatamodel.id,
                                qBiDatamodel.code,
                                qBiDatamodel.name,
                                qBiDatamodel.version,
                                qBiDatamodel.desc,
                                qBiDatamodel.schema,
                                qBiDatamodel.content,
                                qBiDatamodel.parameters,
                                qBiDatamodel.dataType,
                                qBiDatamodel.srcDsId,
                                qBiDatamodel.dsId,
                                qBiDatamodel.dmState,
                                qBiDatamodel.creatorId,
                                qBiDatamodel.createTime,
                                qBiDatamodel.lastEditorId,
                                qBiDatamodel.lastEditTime,
                                qBiDatamodel.deleteTime,
                                qBiDatamodel.deleteState,
                                qBiDatamodel.checkState,
                                qBiDatamodel.checkerId,
                                qBiDatamodel.checkTime,
                                qBiDatamodel.extension,
                                qBiDatamodel.sql,
                                qBiDatamodel.serviceparams,
                                qBiDatamartCatalogRe.datamartId
                        )
                )
                .from(qBiDatamodel)
                .leftJoin(qBiDatamartCatalogRe)
                .on(
                        qBiDatamodel.id.eq(qBiDatamartCatalogRe.relId)
                                .and(qBiDatamartCatalogRe.dataType.eq("0"))
                );
    }

    /**
     * 查询数据主题信息
     *
     * @param id 数据主题id
     * @return 数据主题信息
     */
    public DataModel selectDataModelById(String id) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        JPAQuery<DataModel> jpaQuery = this.selectDataModelVo(qBiDatamodel);
        jpaQuery.where(qBiDatamodel.id.eq(id));
        return jpaQuery.fetchOne();
    }

    /**
     * 根据模型代码和版本查询数据主题
     *
     * @param code 代码
     * @param version 版本
     * @return
     */
    public DataModel selectDataModelByCodeAndVer(String code,
                                                 String version) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        JPAQuery<DataModel> jpaQuery = this.selectDataModelVo(qBiDatamodel);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder
                .and(
                        qBiDatamodel.id.eq(code)
                                .or(qBiDatamodel.code.eq(code))
                );
        if (!StringUtils.isEmpty(version)) {
            booleanBuilder.and(qBiDatamodel.version.eq(version));
        } else {
            booleanBuilder.and(qBiDatamodel.version.isNull());
        }
        jpaQuery.where(booleanBuilder);
        return jpaQuery.fetchOne();
    }

    /**
     * 根据模型代码查询数据主题
     *
     * @param code 代码
     * @return
     */
    public List<DataModel> selectDataModelByCode(String code) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        JPAQuery<DataModel> jpaQuery = this.selectDataModelVo(qBiDatamodel);
        jpaQuery.where(qBiDatamodel.code.eq(code));
        return jpaQuery.fetch();
    }

    /**
     * 查询数据主题列表
     *
     * @param dataModelVo 数据主题页面参数对象
     * @return 数据主题列表
     */
    public List<ThemeVO> selectDataModels(DataModelVo dataModelVo) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        QBiService qBiService = QBiService.biService;

        BooleanExpression booleanExpression = JPAExpressions
                .select(qBiService.id.count())
                .from(qBiService)
                .where(
                        qBiService.type.eq("0")
                                .and(qBiService.relId.eq(qBiDatamodel.id))
                )
                .fetchAll()
                .gt(0L);
        BooleanExpression booleanExpression1 = new CaseBuilder().when(booleanExpression).then(true).otherwise(false);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(dataModelVo.getId())) {
            booleanBuilder.and(qBiDatamodel.id.eq(dataModelVo.getId()));
        }
        if (!StringUtils.isEmpty(dataModelVo.getCode())) {
            booleanBuilder.and(qBiDatamodel.code.like("%" + dataModelVo.getCode() + "%"));
        }
        if (!StringUtils.isEmpty(dataModelVo.getName())) {
            BooleanBuilder booleanBuilder1 = new BooleanBuilder();
            booleanBuilder1
                    .and(
                            qBiDatamodel.name.toUpperCase().like("%" + dataModelVo.getName() + "%")
                                    .or(qBiDatamodel.code.toUpperCase().like("%" + dataModelVo.getName() + "%")));
            if (dataModelVo.getDsIds() != null && dataModelVo.getDsIds().length > 0) {
                booleanBuilder1.or(qBiDatamodel.srcDsId.in(dataModelVo.getDsIds()));
            }
            booleanBuilder.and(booleanBuilder1);
        }
        if (!StringUtils.isEmpty(dataModelVo.getCheckState())) {
            booleanBuilder.and(qBiDatamodel.checkState.eq(dataModelVo.getCheckState()));
        }
        if (!StringUtils.isEmpty(dataModelVo.getDmState())) {
            booleanBuilder.and(qBiDatamodel.dmState.eq(dataModelVo.getDmState()));
        }
        if (dataModelVo.getDataIds() != null && dataModelVo.getDataIds().size() == 0) {
            booleanBuilder.and(Expressions.ONE.eq(2));
        }
        if (dataModelVo.getDataIds() != null && dataModelVo.getDataIds().size() > 0) {
            booleanBuilder.and(qBiDatamodel.id.in(dataModelVo.getDataIds()));
        }
        if (!StringUtils.isEmpty(dataModelVo.getPath())) {
            QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
            QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
            SubQueryExpression subQueryExpression = JPAExpressions
                    .select(qBiDatamartCatalog.id)
                    .from(qBiDatamartCatalog)
                    .where(qBiDatamartCatalog.path.like("%" + dataModelVo.getPath() + "%"));
            SubQueryExpression subQueryExpression1 = JPAExpressions
                    .select(qBiDatamartCatalogRe.relId)
                    .from(qBiDatamartCatalogRe)
                    .where(
                            qBiDatamartCatalogRe.dataType.eq("0")
                                    .and(qBiDatamartCatalogRe.datamartId.in(subQueryExpression))
                    );
            booleanBuilder.and(qBiDatamodel.id.in(subQueryExpression1));
        }
        return this.queryFactory
                .select(
                        Projections.bean(
                                ThemeVO.class,
                                qBiDatamodel.id,
                                qBiDatamodel.code,
                                qBiDatamodel.name,
                                qBiDatamodel.version,
                                qBiDatamodel.desc,
                                qBiDatamodel.parameters,
                                qBiDatamodel.dataType,
                                qBiDatamodel.srcDsId,
                                qBiDatamodel.dmState,
                                qBiDatamodel.creatorId,
                                qBiDatamodel.createTime,
                                qBiDatamodel.lastEditorId,
                                qBiDatamodel.lastEditTime,
                                qBiDatamodel.deleteTime,
                                qBiDatamodel.deleteState,
                                qBiDatamodel.checkState,
                                qBiDatamodel.checkerId,
                                qBiDatamodel.checkTime,
                                qBiDatamodel.serviceparams,
                                ExpressionUtils.as(booleanExpression1, "service")
                        )
                )
                .from(qBiDatamodel)
                .where(booleanBuilder)
                .orderBy(qBiDatamodel.createTime.desc())
                .fetch();
    }

    /**
     * 查询当前用户有权限操作的数据主题
     *
     * @param userId
     * @param dataIds
     * @return
     */
    public List<DataModel> selectReportDataModes(String userId,
                                                 List<String> dataIds) {
        /**select t.fid,t.fname,t.fversion,dmr.fdatamart_id from t_bi_datamodel t
        left join t_bi_datamart_catalog_re dmr on t.FID = dmr.FREL_ID and
        dmr.FDATA_TYPE=0
        where (t.fchecker_id = #{userId} or t.fcreator_id =
		#{userId}) and
        t.fcheck_state = '1'
        UNION
                SELECT
        a.fid,a.fname,a.fversion,dmr.fdatamart_id from t_bi_datamodel a
                left
        join t_bi_datamart_catalog_re dmr on a.FID = dmr.FREL_ID and
        dmr.FDATA_TYPE=0
                <where>
                a.fcheck_state = '1'
                <if test="dataIds != null and dataIds.size == 0 ">
                and a.fid in('')
                </if>
			<if test="dataIds != null and dataIds.size > 0 ">
                and a.fid in
				<foreach item="item" index="index" collection="dataIds"
        open="(" separator="," close=")">
					#{item}
				</foreach>
			</if>
		</where>
                order by fname desc*/
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        List<DataModel> dataModelList = this.queryFactory
                .select(
                        Projections.bean(
                                DataModel.class,
                                qBiDatamodel.id,
                                qBiDatamodel.name,
                                qBiDatamodel.version,
                                qBiDatamartCatalogRe.datamartId
                        )
                )
                .from(qBiDatamodel)
                .leftJoin(qBiDatamartCatalogRe)
                .on(qBiDatamodel.id.eq(qBiDatamartCatalogRe.relId).and(qBiDatamartCatalogRe.dataType.eq("0")))
                .where(qBiDatamodel.checkerId.eq(userId).or(qBiDatamodel.creatorId.eq(userId))).where(qBiDatamodel.checkState.eq("1")).fetch();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBiDatamodel.checkState.eq("1"));
        if (dataIds != null && dataIds.size() > 0) {
            booleanBuilder.and(qBiDatamodel.id.in(dataIds));
        }
        List<DataModel> dataModelList2 = this.queryFactory
                .select(
                        Projections.bean(
                                DataModel.class,
                                qBiDatamodel.id,
                                qBiDatamodel.name,
                                qBiDatamodel.version,
                                qBiDatamartCatalogRe.datamartId
                        )
                )
                .from(qBiDatamodel)
                .leftJoin(qBiDatamartCatalogRe)
                .on(qBiDatamodel.id.eq(qBiDatamartCatalogRe.relId).and(qBiDatamartCatalogRe.dataType.eq("0")))
                .where(booleanBuilder).fetch();
        dataModelList.addAll(dataModelList2);
        return dataModelList;
    }

    /**
     * 查询数据主题列表
     *
     * @param dataIds
     * @return
     */
    public List<DataModel> selectDataModeList(List<String> dataIds) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        JPAQuery jpaQuery = this.selectDataModelVo(qBiDatamodel);
        if (dataIds != null && dataIds.size() > 0) {
            jpaQuery.where(qBiDatamodel.id.in(dataIds));
        }
        return jpaQuery.fetch();
    }

    /**
     * 插入数据主题
     *
     * @param dataModel 数据主题信息
     * @return 插入结果
     */
    public int insertDataModel(DataModel dataModel) {
        BiDatamodel biDatamodel = new BiDatamodel();
        BeanUtils.copyBeanProp(biDatamodel, dataModel);
        this.persist(biDatamodel);
        return 1;
    }

    /**
     * 更新数据主题
     *
     * @param dataModel 数据主题信息
     * @return 更新结果
     */
    public int updateDataModel(DataModel dataModel) {
        BiDatamodel biDatamodel = new BiDatamodel();
        BeanUtils.copyBeanProp(biDatamodel, dataModel);
        this.merge(biDatamodel);
        return 1;
    }

    /**
     * 更新数据审核状态
     *
     * @param checkState 审核状态值
     * @param checkerId 审核用户id
     * @param checkTime 审核时间
     * @param ids id集
     * @return 结果
     */
    public int updateCheckState(String checkState,
                                String checkerId,
                                String checkTime,
                                List<String> ids) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        List<StringPath> columnList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        if (!StringUtils.isEmpty(checkState)) {
            columnList.add(qBiDatamodel.checkState);
            valueList.add(checkState);
        }
        if (!StringUtils.isEmpty(checkerId)) {
            columnList.add(qBiDatamodel.checkerId);
            valueList.add(checkerId);
        }
        if (!StringUtils.isEmpty(checkTime)) {
            columnList.add(qBiDatamodel.checkTime);
            valueList.add(checkTime);
        }
        return (int) this.getQueryFactory()
                .update(qBiDatamodel)
                .set(columnList, valueList)
                .where(qBiDatamodel.id.in(ids))
                .execute();
    }

    /**
     * 删除数据主题
     *
     * @param ids 数据主题id集合
     * @return 删除结果
     */
    public int deleteDataModel(String[] ids) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        return (int) this.getQueryFactory()
                .delete(qBiDatamodel)
                .where(qBiDatamodel.id.in(ids))
                .execute();
    }

    /**
     * 查询数据源是否被数据主题引用
     *
     * @param dsId
     * @return
     */
    public int selectCountDataModelByDsId(String dsId) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        return (int) this.queryFactory
                .select(Expressions.ONE)
                .from(qBiDatamodel)
                .where(
                        qBiDatamodel.dsId.eq(dsId)
                                .or(qBiDatamodel.srcDsId.eq(dsId))
                )
                .fetchCount();
    }

    /**
     * 检查编码
     *
     * @param code
     * @return
     */
    public List<DataModel> checkCodeUnique(String code,
                                           String version) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBiDatamodel.code.eq(code));
        if (!StringUtils.isEmpty(version)) {
            booleanBuilder.and(qBiDatamodel.version.eq(version));
        }
        return this.queryFactory
                .select(
                        Projections.bean(
                                DataModel.class,
                                qBiDatamodel.id,
                                qBiDatamodel.code,
                                qBiDatamodel.name,
                                qBiDatamodel.version,
                                qBiDatamodel.dataType,
                                qBiDatamodel.srcDsId,
                                qBiDatamodel.dsId,
                                qBiDatamodel.dmState
                        )
                )
                .from(qBiDatamodel)
                .where(booleanBuilder)
                .fetch();
    }

    /**
     * 根据模型代码、名称、版本查询数据主题
     *
     * @param code 代码
     * @param name 模型名称
     * @param version 版本
     * @return 数据主题
     */
    public DataModel selectDataModelByCodeNameVer(String code,
                                                  String name,
                                                  String version) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        JPAQuery<DataModel> jpaQuery = this.selectDataModelVo(qBiDatamodel);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder
                .and(
                        qBiDatamodel.code.eq(code)
                                .and(qBiDatamodel.name.eq(name))
                );
        if (!StringUtils.isEmpty(version)) {
            booleanBuilder.and(qBiDatamodel.version.eq(version));
        } else {
            booleanBuilder.and(qBiDatamodel.version.isNull());
        }
        jpaQuery.where(booleanBuilder);
        return jpaQuery.fetchOne();
    }

    /**
     * 校验是否存在非审核数据
     *
     * @param ids id列表
     * @return true:存在非审核数据/false:不存在非审核数据
     */
    public Boolean validateDataNonChecked(List<String> ids) {
       /* SELECT COUNT(1) FROM t_bi_datamodel WHERE FCHECK_STATE=0 AND FID IN
                <foreach item="item" index="index" collection="ids" open="("
        separator="," close=")">
			#{item}
		</foreach>*/
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        long result = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiDatamodel)
                .where(
                        qBiDatamodel.checkState.eq("0")
                                .and(qBiDatamodel.id.in(ids))
                )
                .fetchCount();
        return result > 0 ? true : false;
    }

    /**
     * 判断服务code编码、version版本号是否存在
     *
     * @param code 编码
     * @param version 版本号
     * @return 服务信息
     */
    public Boolean existCodeVersion(String code, String version) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBiDatamodel.code.toUpperCase().eq(code.toUpperCase()));
        if (!StringUtils.isEmpty(version)) {
            booleanBuilder.and(qBiDatamodel.version.toUpperCase().eq(version.toUpperCase()));
        } else {
            booleanBuilder.and(qBiDatamodel.version.isNull());
        }
        long result = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiDatamodel)
                .where(booleanBuilder)
                .fetchCount();
        return result > 0 ? true : false;
    }

    /**
     * 判断服务名称是否存在
     *
     * @param name 名称
     * @return 服务信息
     */
    public Boolean existNameVersion(String name, String version) {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBiDatamodel.code.toUpperCase().eq(name.toUpperCase()));
        if (!StringUtils.isEmpty(version)) {
            booleanBuilder.and(qBiDatamodel.version.toUpperCase().eq(version.toUpperCase()));
        } else {
            booleanBuilder.and(qBiDatamodel.version.isNull());
        }
        long result = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiDatamodel)
                .where(booleanBuilder)
                .fetchCount();
        return result > 0 ? true : false;
    }

    /**
     * 查询存在引用视图集合
     *
     * @param tableviewName 名称
     */
    /**
     * 查询数据主题列表
     *
     * @param parameters
     * @return
     */
    public List<DataModel> selectReference(List<String> parameters) {
        /*select fid, fcode, fname,
                fversion, fdata_type, fsrc_ds_id, fds_id,
        fdm_state, fparameters from t_bi_datamodel
		<where>
			<if test="parameters != null and parameters.size > 0 ">
                and 1=2
                <foreach item="item" index="index" collection="parameters">
                or upper(FPARAMETERS) like '%${item}%'
                </foreach>
			</if>
		</where>*/
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (parameters != null && parameters.size() > 0) {
            for (String str : parameters) {
                booleanBuilder.or(qBiDatamodel.parameters.like("%" + str + "%"));
            }
            booleanBuilder.or(Expressions.ONE.eq(2));
        }
        return this.queryFactory
                .select(
                        Projections.bean(
                                DataModel.class,
                                qBiDatamodel.id,
                                qBiDatamodel.code,
                                qBiDatamodel.name,
                                qBiDatamodel.version,
                                qBiDatamodel.dataType,
                                qBiDatamodel.srcDsId,
                                qBiDatamodel.dsId,
                                qBiDatamodel.dmState,
                                qBiDatamodel.parameters
                        )
                ).from(qBiDatamodel).where(booleanBuilder).fetch();
    }
}



































