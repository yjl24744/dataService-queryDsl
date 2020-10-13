package com.yss.datamiddle.dao.impl;

import com.querydsl.core.types.Projections;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.BiServiceParameter;
import com.yss.datamiddle.entities.QBiServiceParameter;
import com.yss.datamiddle.utils.BeanUtils;
import com.yss.datamiddle.vo.standardservice.ServiceParameterDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiServiceParameterDaoImpl extends AbstractDataMiddleBaseDao {

    /**
     * 查询服务字段信息列表
     *
     * @param serviceId 服务id
     *
     * @return 服务字段信息列表
     */
    public List<ServiceParameterDO> selectList(String serviceId) {
        /*SELECT FID,FSERVICE_ID,FNAME,FTYPE,FDEFAULT_VALUE,FALIAS,FOPERATOR
		FROM t_bi_service_parameter
        WHERE FSERVICE_ID = #{serviceId}
		ORDER BY FID*/
        QBiServiceParameter qBiServiceParameter = QBiServiceParameter.biServiceParameter;
        return this.queryFactory
                .select(
                        Projections.bean(
                                ServiceParameterDO.class,
                                qBiServiceParameter.id,
                                qBiServiceParameter.serviceId,
                                qBiServiceParameter.name,
                                qBiServiceParameter.type,
                                qBiServiceParameter.defaultValue,
                                qBiServiceParameter.alias,
                                qBiServiceParameter.operator
                        )
                )
                .from(qBiServiceParameter)
                .where(qBiServiceParameter.serviceId.eq(serviceId))
                .orderBy(qBiServiceParameter.id.asc())
                .fetch();
    }

    /**
     * 添加服务字段信息
     *
     * @param serviceParameterDO 服务字段信息
     * @return 结果
     */
    public int insert(ServiceParameterDO serviceParameterDO) {
        QBiServiceParameter qBiServiceParameter = QBiServiceParameter.biServiceParameter;
        BiServiceParameter biServiceParameter = new BiServiceParameter();
        BeanUtils.copyBeanProp(biServiceParameter, serviceParameterDO);
        this.persist(biServiceParameter);
        return 1;
    }

    /**
     * 更新服务信息
     *
     * @param serviceParameterDO 更新服务字段信息
     * @return 结果
     */
    public int update(ServiceParameterDO serviceParameterDO) {
        // TODO 需要转换
        QBiServiceParameter qBiServiceParameter = QBiServiceParameter.biServiceParameter;
        BiServiceParameter biServiceParameter = new BiServiceParameter();
        BeanUtils.copyBeanProp(biServiceParameter, serviceParameterDO);
        this.merge(biServiceParameter);
        return 1;
    }

    /**
     * 批量删除服务字段信息
     *
     * @param ids 服务id列表
     * @return 结果
     */
    public int delete(String[] ids) {
        QBiServiceParameter qBiServiceParameter = QBiServiceParameter.biServiceParameter;
        return (int) this.getQueryFactory()
                .delete(qBiServiceParameter)
                .where(qBiServiceParameter.id.in(ids))
                .execute();
    }

    /**
     * 批量删除服务字段信息
     *
     * @param serviceIds 服务id集合
     * @return 结果
     */
    public int deleteByServiceId(String[] serviceIds) {
        QBiServiceParameter qBiServiceParameter = QBiServiceParameter.biServiceParameter;
        return (int) this.getQueryFactory()
                .delete(qBiServiceParameter)
                .where(qBiServiceParameter.serviceId.in(serviceIds))
                .execute();
    }
}
