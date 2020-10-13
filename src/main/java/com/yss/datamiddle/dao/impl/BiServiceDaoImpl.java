package com.yss.datamiddle.dao.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.*;
import com.yss.datamiddle.utils.BeanUtils;
import com.yss.datamiddle.utils.StringUtils;
import com.yss.datamiddle.vo.standardservice.InnerServiceInfoDO;
import com.yss.datamiddle.vo.standardservice.ServiceInfoDO;
import com.yss.datamiddle.vo.standardservice.ServiceInfoVO;
import com.yss.datamiddle.vo.standardservice.ServicePageVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BiServiceDaoImpl extends AbstractDataMiddleBaseDao<BiService> {

    private JPAQuery selectBiService() {
        /**SELECT
        TSERV.FID,TSERV.FCODE,TSERV.FVERSION,TSERV.FNAME,TSERV.FDESC,TSERV.FTYPE,TSERV.FREL_ID,
                TSERV.FCREATOR_ID,TSERV.FCREATE_TIME,TSERV.FLAST_EDITOR_ID,TSERV.FLAST_EDIT_TIME,TSERV.FCHECK_STATE,
                TSERV.FCHECKER_ID,TSERV.FCHECK_TIME,TUSER.USER_NAME
        FROM t_bi_service
        TSERV LEFT JOIN t_bi_user TUSER ON TSERV.FCREATOR_ID
                = TUSER.USER_ID*/
        QBiService qBiService = QBiService.biService;
        return this.queryFactory
                .select(
                        Projections.bean(
                                ServiceInfoVO.class,
                                qBiService.id,
                                qBiService.code,
                                qBiService.version,
                                qBiService.name,
                                qBiService.desc,
                                qBiService.type,
                                qBiService.relId,
                                qBiService.creatorId,
                                qBiService.createTime,
                                qBiService.lastEditorId,
                                qBiService.lastEditTime,
                                qBiService.checkState,
                                qBiService.checkerId,
                                qBiService.checkTime
                        )
                )
                .from(qBiService);
    }

    /**
     * 查询服务信息
     *
     * @param id 服务id
     * @return 服务信息
     */
    public ServiceInfoVO select(String id) {
        QBiService qBiService = QBiService.biService;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        return this.queryFactory
                .select(
                        Projections.bean(
                                ServiceInfoVO.class,
                                qBiService.id,
                                qBiService.code,
                                qBiService.version,
                                qBiService.name,
                                qBiService.desc,
                                qBiService.type,
                                qBiService.relId,
                                qBiService.creatorId,
                                qBiService.createTime,
                                qBiService.lastEditorId,
                                qBiService.lastEditTime,
                                qBiService.checkState,
                                qBiService.checkerId,
                                qBiService.checkTime,
                                qBiDatamartCatalogRe.datamartId
                        )
                )
                .from(qBiService)
                .leftJoin(qBiDatamartCatalogRe)
                .on(
                        qBiService.id.eq(qBiDatamartCatalogRe.relId)
                                .and(qBiDatamartCatalogRe.dataType.eq("2"))
                )
                .where(qBiService.id.eq(id))
                .fetchOne();
    }

    /**
     * 查询服务信息列表
     *
     * @param servicePageVO 服务参数
     * @param dataIds 数据id集合
     *
     * @return 服务信息列表
     */
    public List<ServiceInfoVO> selectList(ServicePageVO servicePageVO,
                                   List<String> dataIds) {
        /**<where>
         <if test="dataIds != null and dataIds.size == 0 ">
         AND 1=2
         </if>
         <if test="dataIds != null and dataIds.size > 0 ">
         AND FID IN
         <foreach item="item" index="index" collection="dataIds"
         open="(" separator="," close=")">
         #{item}
         </foreach>
         </if>
         <if test="param.keyWord != null and param.keyWord != '' ">
         and (upper(fcode) like '%${param.keyWord}%' or
         upper(fname) like '%${param.keyWord}%')
         </if>
         <if test="param.path != null and param.path !='' ">
         AND TSERV.FID IN (SELECT FREL_ID FROM
         t_bi_datamart_catalog_re
         WHERE FDATA_TYPE='2' AND FDATAMART_ID IN
         (SELECT FID FROM
         t_bi_datamart_catalog WHERE FPATH LIKE
         '${param.path}%'))
         </if>
         </where>
         ORDER BY FCREATE_TIME DESC*/
        QBiService qBiService = QBiService.biService;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (dataIds != null && dataIds.size() > 0) {
            booleanBuilder.and(qBiService.id.in(dataIds));
        }
        if (!StringUtils.isEmpty(servicePageVO.getKeyWord())) {
            booleanBuilder.and(qBiService.code.toUpperCase().like("%" + servicePageVO.getKeyWord() + "%")
                    .or(qBiService.name.toUpperCase().like("%" + servicePageVO.getKeyWord() + "%"))
            );
        }
        if (!StringUtils.isEmpty(servicePageVO.getPath())) {
            booleanBuilder.and(qBiService.id.in(
                    JPAExpressions
                            .select(qBiDatamartCatalogRe.relId)
                            .from(qBiDatamartCatalogRe)
                            .where(
                                    qBiDatamartCatalogRe.dataType.eq("2")
                                            .and(qBiDatamartCatalogRe.datamartId.in(
                                                    JPAExpressions
                                                            .select(qBiDatamartCatalog.id)
                                                            .from(qBiDatamartCatalog)
                                                            .where(
                                                                    qBiDatamartCatalog.path
                                                                            .like("%" + servicePageVO.getPath() + "%")
                                                            )
                                            ))
                            )
            ));
        }
        JPAQuery<ServiceInfoVO> jpaQuery = this.selectBiService();
        jpaQuery
                .where(booleanBuilder)
                .orderBy(qBiService.createTime.desc());
        return jpaQuery.fetch();
    }

    /**
     * 添加服务信息
     *
     * @param serviceDO 服务信息
     * @return 结果
     */
    public int insert(ServiceInfoDO serviceDO) {
        BiService biService = new BiService();
        BeanUtils.copyBeanProp(biService, serviceDO);
        this.persist(biService);
        return 1;
    }

    /**
     * 更新服务信息
     *
     * @param serviceDO 更新服务信息
     * @return 结果
     */
    public int update(ServiceInfoDO serviceDO) {
        BiService biService = new BiService();
        BeanUtils.copyBeanProp(biService, serviceDO);
        this.merge(biService);
        return 1;
    }

    /**
     * 更新CheckState
     *
     * @param param 参数集合
     * @return 结果
     */
    public int updateCheckState(Map<String, Object> param) {
        /**UPDATE t_bi_service SET
        FCHECK_STATE=#{checkState},FCHECKER_ID=#{checkerId},FCHECK_TIME=#{checkTime}
        WHERE FID IN
                <foreach item="id" collection="ids" open="(" separator=","
        close=")">
			#{id}
		</foreach>*/
        QBiService qBiService = QBiService.biService;
        String checkState = param.get("checkState").toString();
        String checkerId = param.get("checkerId").toString();
        String checkTime = param.get("checkTime").toString();
        String[] ids = (String[]) param.get("ids");
        List<StringPath> columnList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        if (!StringUtils.isEmpty(checkState)) {
            columnList.add(qBiService.checkState);
            valueList.add(checkState);
        }
        if (!StringUtils.isEmpty(checkerId)) {
            columnList.add(qBiService.checkerId);
            valueList.add(checkerId);
        }
        if (!StringUtils.isEmpty(checkTime)) {
            columnList.add(qBiService.checkTime);
            valueList.add(checkTime);
        }
        return (int) this.getQueryFactory()
                .update(qBiService)
                .set(columnList, valueList)
                .where(qBiService.id.in(ids))
                .execute();
    }

    /**
     * 批量删除服务信息
     *
     * @param ids 服务id列表
     * @return 结果
     */
    public int delete(String[] ids) {
        QBiService qBiService = QBiService.biService;
        return (int) this.getQueryFactory()
                .delete(qBiService)
                .where(qBiService.id.in(ids))
                .execute();
    }

    /**
     * 查询标注服务信息
     *
     * @param code 编码
     * @param version 版本号
     * @return 服务信息
     */
    public ServiceInfoDO selectByCodeVersion(String code, String version) {
        /*<where>
                AND TSERV.FCODE = #{code}
			<if test="version != null  and version != ''  ">
                AND TSERV.FVERSION = #{version}
			</if>
			<if test="version == null or version == ''  ">
                AND TSERV.FVERSION IS NULL
                </if>
		</where>*/
        QBiService qBiService = QBiService.biService;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBiService.code.eq(code));
        if (StringUtils.isEmpty(version)) {
            booleanBuilder.and(qBiService.version.isNull());
        } else {
            booleanBuilder.and(qBiService.version.eq(version));
        }
        JPAQuery<ServiceInfoDO> jpaQuery = this.selectBiService();
        return jpaQuery.where(booleanBuilder).fetchOne();
    }

    /**
     * 判断服务code编码、version版本号是否存在
     *
     * @param code 编码
     * @param version 版本号
     * @return true:存在/false:不存在
     */
    public Boolean existCodeVersion(String code, String version) {
        /*SELECT COUNT(1) FROM t_bi_service TSERV
                <where>
        AND upper(TSERV.FCODE) = upper(#{code})
			<if test="version != null  and version != ''  ">
                AND upper(TSERV.FVERSION) = upper(#{version})
			</if>
			<if test="version == null or version == ''  ">
                AND TSERV.FVERSION IS NULL
                </if>
		</where>*/
        QBiService qBiService = QBiService.biService;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBiService.code.toUpperCase().eq(code.toUpperCase()));
        if (!StringUtils.isEmpty(code)) {
            booleanBuilder.and(qBiService.version.toUpperCase().eq(version.toUpperCase()));
        } else {
            booleanBuilder.and(qBiService.version.isNull());
        }
        long count = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiService)
                .where(booleanBuilder).fetchCount();
        return count > 0 ? true : false;
    }

    /**
     * 判断服务名称是否存在
     *
     * @param name 名称
     * @return true:存在/false:不存在
     */
    public Boolean existNameVersion(String name, String version) {
        /*SELECT COUNT(1) FROM t_bi_service TSERV
                <where>
        AND upper(TSERV.FNAME) = upper(#{name})
			<if test="version != null  and version != ''  ">
                AND upper(TSERV.FVERSION) = upper(#{version})
			</if>
			<if test="version == null or version == ''  ">
                AND TSERV.FVERSION IS NULL
                </if>
		</where>*/
        QBiService qBiService = QBiService.biService;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBiService.name.toUpperCase().eq(name.toUpperCase()));
        if (!StringUtils.isEmpty(version)) {
            booleanBuilder.and(qBiService.version.toUpperCase().eq(version.toUpperCase()));
        } else {
            booleanBuilder.and(qBiService.version.isNull());
        }
        long count = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiService)
                .where(booleanBuilder)
                .fetchCount();
        return count > 0 ? true : false;
    }

    /**
     * 判断服务来源的数据是否在标准服务中存在引用且已上线
     *
     * @param type 服务来源类型
     * @param relIds 服务来源数据id集
     * @return true:存在/false:不存在
     */
    public Boolean existReference(String type, List<String> relIds) {
       /* SELECT COUNT(1) FROM t_bi_service
		<where>
                AND fcheck_state='1'
        AND ftype = #{type}
			<if test="relIds != null and relIds.size > 0 ">
                AND frel_id IN
				<foreach item="item" index="index" collection="relIds"
        open="(" separator="," close=")">
					#{item}
				</foreach>
			</if>
		</where>*/
       QBiService qBiService = QBiService.biService;
       BooleanBuilder booleanBuilder = new BooleanBuilder();
       booleanBuilder
               .and(qBiService.checkState.eq("1"))
               .and(qBiService.type.eq(type));
       if (relIds != null && relIds.size() > 0) {
           booleanBuilder.and(qBiService.relId.in(relIds));
       }
       long count = this.queryFactory
               .select(Expressions.ONE)
               .from(qBiService)
               .where(booleanBuilder)
               .fetchCount();
       return count > 0 ? true : false;
    }

    /**
     * 查询内部主题服务信息列表
     *
     * @return 主题服务信息列表
     */
	public List<InnerServiceInfoDO> selectInnerDmService() {
        QBiDatamodel qBiDatamodel = QBiDatamodel.biDatamodel;
	    return this.queryFactory
                .select(
                        Projections.bean(
                                InnerServiceInfoDO.class,
                                qBiDatamodel.id,
                                qBiDatamodel.code,
                                qBiDatamodel.name.as("alias")
                        )
                )
                .from(qBiDatamodel)
                .where(qBiDatamodel.checkState.eq("1"))
                .fetch()
                .stream()
                .map(innerServiceInfoDO -> {
                    innerServiceInfoDO.setType("0");
                    return innerServiceInfoDO;
                })
                .collect(Collectors.toList());
    }

    /**
     * 查询内部视图服务信息列表
     *
     * @return 视图服务信息列表
     */
	public List<InnerServiceInfoDO> selectInnerViewService() {
	    QBiTableview qBiTableview = QBiTableview.biTableview;
	    return this.queryFactory
                .select(
                        Projections.bean(
                                InnerServiceInfoDO.class,
                                qBiTableview.id,
                                qBiTableview.name.as("code"),
                                qBiTableview.viewAlias.as("alias")
                        )
                )
                .from(qBiTableview)
                .where(qBiTableview.checkState.eq("1"))
                .fetch()
                .stream()
                .map(innerServiceInfoDO -> {
                    innerServiceInfoDO.setVersion(null);
                    innerServiceInfoDO.setType("1");
                    return innerServiceInfoDO;
                })
                .collect(Collectors.toList());
    }

}


















