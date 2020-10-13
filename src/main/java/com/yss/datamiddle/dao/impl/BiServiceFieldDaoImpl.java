package com.yss.datamiddle.dao.impl;

import com.querydsl.core.types.Projections;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.BiServiceField;
import com.yss.datamiddle.entities.QBiServiceField;
import com.yss.datamiddle.utils.BeanUtils;
import com.yss.datamiddle.vo.standardservice.ServiceFieldDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiServiceFieldDaoImpl extends AbstractDataMiddleBaseDao {

    /**
     * 查询服务字段信息列表
     *
     * @param serviceId 服务id
     *
     * @return 服务字段信息列表
     */
    public List<ServiceFieldDO> selectList(String serviceId) {
        /*SELECT FID,FSERVICE_ID,FNAME,FINNER_NAME,FTYPE,FALIAS FROM
        t_bi_service_field WHERE FSERVICE_ID = #{serviceId}
		ORDER BY FID*/
        QBiServiceField qBiServiceField = QBiServiceField.biServiceField;
        return this.queryFactory
                .select(
                        Projections.bean(
                                ServiceFieldDO.class,
                                qBiServiceField.id,
                                qBiServiceField.serviceId,
                                qBiServiceField.name,
                                qBiServiceField.innerName,
                                qBiServiceField.type,
                                qBiServiceField.alias
                        )
                )
                .from(qBiServiceField)
                .where(qBiServiceField.serviceId.eq(serviceId))
                .orderBy(qBiServiceField.id.asc())
                .fetch();
    }

    /**
     * 添加服务字段信息
     *
     * @param serviceFieldDO 服务字段信息
     * @return 结果
     */
    public int insert(ServiceFieldDO serviceFieldDO) {
        BiServiceField biServiceField = new BiServiceField();
        BeanUtils.copyBeanProp(biServiceField, serviceFieldDO);
        this.persist(biServiceField);
        return 1;
    }

    /**
     * 更新服务信息
     *
     * @param serviceFieldDO 更新服务字段信息
     * @return 结果
     */
    public int update(ServiceFieldDO serviceFieldDO) {
        // TODO 需要转换
        BiServiceField biServiceField = new BiServiceField();
        BeanUtils.copyBeanProp(biServiceField, serviceFieldDO);
        this.merge(biServiceField);
        return 1;
    }

    /**
     * 批量删除服务字段信息
     *
     * @param ids 服务id列表
     * @return 结果
     */
    public int delete(String[] ids) {
        /*DELETE FROM t_bi_service_field WHERE FID IN
		<foreach item="id" collection="array" open="(" separator=","
        close=")">
			#{id}
		</foreach>*/
        QBiServiceField qBiServiceField = QBiServiceField.biServiceField;
        return (int) this.getQueryFactory()
                .delete(qBiServiceField)
                .where(qBiServiceField.id.in(ids))
                .execute();
    }

    /**
     * 批量删除服务字段信息
     *
     * @param serviceIds 服务id集合
     * @return 结果
     */
    public int deleteByServiceId(String[] serviceIds) {
        QBiServiceField qBiServiceField = QBiServiceField.biServiceField;
        return (int) this.getQueryFactory()
                .delete(qBiServiceField)
                .where(qBiServiceField.serviceId.in(serviceIds))
                .execute();
    }
}
