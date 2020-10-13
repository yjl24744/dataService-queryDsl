package com.yss.datamiddle.dao.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.yss.datamiddle.dao.AbstractDataMiddleBaseDao;
import com.yss.datamiddle.entities.*;
import com.yss.datamiddle.util.BeanUtils;
import com.yss.datamiddle.util.StringUtils;
import com.yss.datamiddle.vo.tableview.TableView;
import com.yss.datamiddle.vo.tableview.TableViewVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BiTableviewDaoImpl extends AbstractDataMiddleBaseDao<BiTableview> {

    private JPAQuery selectTableviewVo() {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        return this.queryFactory
                .select(
                        Projections.bean(
                                TableViewVO.class,
                                qBiTableview.id,
                                qBiTableview.viewAlias,
                                qBiTableview.name,
                                qBiTableview.desc,
                                qBiTableview.content,
                                qBiTableview.dsId,
                                qBiTableview.sql,
                                qBiTableview.inlineDim,
                                qBiTableview.creatorId,
                                qBiTableview.createTime,
                                qBiTableview.lastEditorId,
                                qBiTableview.deleteTime,
                                qBiTableview.deleteState,
                                qBiTableview.checkState,
                                qBiTableview.checkerId,
                                qBiTableview.checkTime,
                                qBiTableview.createView,
                                qBiDatamartCatalogRe.datamartId
                        )
                )
                .from(qBiTableview)
                .leftJoin(qBiDatamartCatalogRe)
                .on(qBiTableview.id.eq(qBiDatamartCatalogRe.relId).and(qBiDatamartCatalogRe.dataType.eq("1")));
    }

    /**
     * 查询数据视图列表
     *
     * @param tableview 数据视图信息
     * @return 数据视图集合
     */
    public List<TableViewVO> selectTableviewList(TableViewVO tableview) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        JPAQuery<TableViewVO> jpaQuery = this.selectTableviewVo();
        if (!StringUtils.isEmpty(tableview.getId())) {
            jpaQuery.where(qBiTableview.id.eq(tableview.getId()));
        }
        if (!StringUtils.isEmpty(tableview.getViewAlias())) {
            jpaQuery.where(qBiTableview.viewAlias.eq(tableview.getViewAlias()));
        }
        if (!StringUtils.isEmpty(tableview.getName())) {
            jpaQuery.where(qBiTableview.name.toUpperCase().like("%" + tableview.getName().toUpperCase() + "%"));
        }
        if (!StringUtils.isEmpty(tableview.getDescription())) {
            jpaQuery.where(qBiTableview.desc.eq(tableview.getDescription()));
        }
        if (!StringUtils.isEmpty(tableview.getDsId())) {
            jpaQuery.where(qBiTableview.dsId.eq(tableview.getDsId()));
        }
        if (!StringUtils.isEmpty(tableview.getInlineDim())) {
            jpaQuery.where(qBiTableview.inlineDim.eq(tableview.getInlineDim()));
        }
        if (!StringUtils.isEmpty(tableview.getDeleteState())) {
            jpaQuery.where(qBiTableview.deleteState.eq(tableview.getDeleteState()));
        }
        if (!StringUtils.isEmpty(tableview.getCheckState())) {
            jpaQuery.where(qBiTableview.checkState.eq(tableview.getCheckState()));
        }
        if (!StringUtils.isEmpty(tableview.getCreateView())) {
            jpaQuery.where(qBiTableview.createView.eq(tableview.getCreateView()));
        }
        if (!StringUtils.isEmpty(tableview.getPath())) {
            jpaQuery.where(
                    qBiTableview.id.in(
                            JPAExpressions.select(qBiDatamartCatalogRe.relId)
                                    .from(qBiDatamartCatalogRe)
                                    .where(qBiDatamartCatalogRe.dataType.eq("1")
                                            .and(qBiDatamartCatalogRe.datamartId.in(
                                                    JPAExpressions
                                                            .select(qBiDatamartCatalog.id)
                                                            .from(qBiDatamartCatalog)
                                                            .where(qBiDatamartCatalog.path.like("%" + tableview.getPath() + "%"))
                                                    )
                                            )
                                    )
                    )
            );
        }
        return jpaQuery.fetch();
    }

    /**
     * 查询数据视图信息
     *
     * @param id 数据视图ID
     * @return 数据视图信息
     */
    public TableViewVO selectTableviewById(String id) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        JPAQuery<TableViewVO> jpaQuery = this.selectTableviewVo();
        return jpaQuery.where(qBiTableview.id.eq(id)).fetchOne();
    }

    /**
     * 查询数据视图信息
     *
     * @param ids 数据视图ID集合
     * @return 数据视图信息
     */
    public List<TableViewVO> selectTableviewByIds(List<String> ids) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        JPAQuery<TableViewVO> jpaQuery = this.selectTableviewVo();
        return jpaQuery.where(qBiTableview.id.in(ids)).fetch();
    }

    /**
     * 根据表名查找视图
     *
     * @param names
     * @return 视图列表
     */
    public List<TableView> selectTableviewByNames(String[] names) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        JPAQuery<TableView> jpaQuery = this.selectTableviewVo();
        return jpaQuery
                .where(qBiTableview.name.in(names))
                .fetch();
    }

    /**
     * 根据表名查找视图
     *
     * @param name
     * @return 视图
     */
    public TableView selectTableviewByName(String name) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        JPAQuery<TableView> jpaQuery = this.selectTableviewVo();
        return jpaQuery
                .where(qBiTableview.name.toUpperCase().eq(name.toUpperCase()))
                .fetchOne();
    }

    /**
     * 校验是否存在非审核数据
     *
     * @param ids id列表
     * @return true:存在非审核数据/false:不存在非审核数据
     */
    public Boolean validateDataNonChecked(List<String> ids) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        long count = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiTableview)
                .where(
                        qBiTableview.checkState.eq("0")
                                .and(qBiTableview.id.in(ids))
                ).fetchCount();
        return count > 0 ? true : false;
    }

    /**
     * 新增数据视图
     *
     * @param tableview 数据视图信息
     * @return 结果
     */
    @Transactional
    public void insertTableview(TableViewVO tableview) {
        BiTableview biTableview = new BiTableview();
        BeanUtils.copyBeanProp(biTableview, tableview);
        this.persist(biTableview);
    }

    /**
     * 修改数据视图
     *
     * @param tableview 数据视图信息
     * @return 结果
     */
    @Transactional
    public int updateTableview(TableView tableview) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        List<StringPath> columnList = new LinkedList<>();
        List<Object> valueList = new LinkedList<>();
        if (!StringUtils.isEmpty(tableview.getViewAlias())) {
            columnList.add(qBiTableview.viewAlias);
            valueList.add(tableview.getViewAlias());
        }
        if (!StringUtils.isEmpty(tableview.getName())) {
            columnList.add(qBiTableview.name);
            valueList.add(tableview.getName());
        }
        if (!StringUtils.isEmpty(tableview.getDescription())) {
            columnList.add(qBiTableview.desc);
            valueList.add(tableview.getDescription());
        }
        if (!StringUtils.isEmpty(tableview.getDsId())) {
            columnList.add(qBiTableview.dsId);
            valueList.add(tableview.getDsId());
        }
        if (!StringUtils.isEmpty(tableview.getSql())) {
            columnList.add(qBiTableview.sql);
            valueList.add(tableview.getSql());
        }
        if (!StringUtils.isEmpty(tableview.getInlineDim())) {
            columnList.add(qBiTableview.inlineDim);
            valueList.add(tableview.getInlineDim());
        }
        if (!StringUtils.isEmpty(tableview.getRelationField())) {
            columnList.add(qBiTableview.relationField);
            valueList.add(tableview.getRelationField());
        }
        if (!StringUtils.isEmpty(tableview.getCreatorId())) {
            columnList.add(qBiTableview.creatorId);
            valueList.add(tableview.getCreatorId());
        }
        if (!StringUtils.isEmpty(tableview.getCreateTime())) {
            columnList.add(qBiTableview.createTime);
            valueList.add(tableview.getCreateTime());
        }
        if (!StringUtils.isEmpty(tableview.getLastEditorId())) {
            columnList.add(qBiTableview.lastEditorId);
            valueList.add(tableview.getLastEditorId());
        }
        if (!StringUtils.isEmpty(tableview.getLastEditTime())) {
            columnList.add(qBiTableview.lastEditTime);
            valueList.add(tableview.getLastEditTime());
        }
        if (!StringUtils.isEmpty(tableview.getDeleteTime())) {
            columnList.add(qBiTableview.deleteTime);
            valueList.add(tableview.getDeleteTime());
        }
        if (!StringUtils.isEmpty(tableview.getDeleteState())) {
            columnList.add(qBiTableview.deleteState);
            valueList.add(tableview.getDeleteState());
        }
        if (!StringUtils.isEmpty(tableview.getCheckState())) {
            columnList.add(qBiTableview.checkState);
            valueList.add(tableview.getCheckState());
        }
        if (!StringUtils.isEmpty(tableview.getCheckerId())) {
            columnList.add(qBiTableview.checkerId);
            valueList.add(tableview.getCheckerId());
        }
        if (!StringUtils.isEmpty(tableview.getCheckTime())) {
            columnList.add(qBiTableview.checkTime);
            valueList.add(tableview.getCheckTime());
        }
        if (!StringUtils.isEmpty(tableview.getCreateView())) {
            columnList.add(qBiTableview.createView);
            valueList.add(tableview.getCreateView());
        }
        if (!StringUtils.isEmpty(tableview.getContent())) {
            columnList.add(qBiTableview.content);
            valueList.add(tableview.getContent());
        }

        return (int) this.getQueryFactory()
                .update(qBiTableview)
                .set(columnList, valueList)
                .where(qBiTableview.id.eq(tableview.getId()))
                .execute();
        // return this.merge(tableview);
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
    @Transactional
    public int updateCheckState(String checkState,
                                String checkerId,
                                String checkTime,
                                List<String> ids) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        List<StringPath> columnList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        if (!StringUtils.isEmpty(checkState)) {
            columnList.add(qBiTableview.checkState);
            valueList.add(checkState);
        }
        if (!StringUtils.isEmpty(checkerId)) {
            columnList.add(qBiTableview.checkerId);
            valueList.add(checkerId);
        }
        if (!StringUtils.isEmpty(checkTime)) {
            columnList.add(qBiTableview.checkTime);
            valueList.add(checkTime);
        }
        long result = this.getQueryFactory()
                .update(qBiTableview)
                .set(columnList, valueList)
                .where(qBiTableview.id.in(ids))
                .execute();
        return (int) result;
    }

    /**
     * 删除数据视图
     *
     * @param id 数据视图ID
     * @return 结果
     */
    @Transactional
    public int deleteTableviewById(String id) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        return (int) this.getQueryFactory()
                .delete(qBiTableview)
                .where(qBiTableview.id.eq(id))
                .execute();
    }

    /**
     * 批量删除数据视图
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    public int deleteTableviewByIds(String[] ids) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        return (int) this.getQueryFactory()
                .delete(qBiTableview)
                .where(qBiTableview.id.in(ids))
                .execute();
    }

    /**
     * 获取视图集市数据
     *
     * @param dsId 数据源id
     * @return 视图集市数据
     */
    public List<Map<String, Object>> getViewDatamartCatalogData(String dsId) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
        StringExpression expression = new CaseBuilder()
                .when(qBiDatamartCatalogRe.datamartId.isNull())
                .then(qBiDatamartCatalog.id)
                .otherwise(qBiDatamartCatalogRe.datamartId);

        return this.queryFactory
                .select(
                        qBiTableview.id,
                        qBiTableview.name,
                        qBiTableview.viewAlias,
                        expression.as("FDATAMART_ID")
                )
                .from(qBiTableview)
                .leftJoin(qBiDatamartCatalogRe)
                .on(
                        qBiTableview.createView.eq("0")
                                .and(qBiDatamartCatalogRe.dataType.eq("1")
                                        .and(qBiTableview.id.eq(qBiDatamartCatalogRe.relId)))
                )
                .leftJoin(qBiDatamartCatalog)
                .on(
                        qBiDatamartCatalog.name.eq("数据集市")
                )
                .where(
                        qBiTableview.dsId.eq(dsId)
                                .and(qBiTableview.createView.eq("1"))
                )
                .fetch()
                .stream()
                .map(tuple -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("FDS_ID", tuple.get(qBiTableview.id));
                    map.put("FNAME", tuple.get(qBiTableview.name));
                    map.put("FVIEW_ALIAS", tuple.get(qBiTableview.viewAlias));
                    map.put("FDATAMART_ID", tuple.get(expression.as("FDATAMART_ID")));
                    map.put("FTABLE_TYPE", Expressions.asString("view").as("FTABLE_TYPE"));
                    map.put("FTABLE_TYPE", "view");
                    return map;
                }).collect(Collectors.toList());
    }

    /**
     * 判断视图编码是否存在
     *
     * @param name 编码
     * @return true:存在/false:不存在
     */
    public boolean existName(String name) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        long count = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiTableview)
                .where(qBiTableview.name.toUpperCase().eq(name.toUpperCase()))
                .fetchCount();
        return count > 0 ? true : false;
    }

    /**
     * 判断视图中文名是否存在
     *
     * @param alias 中文名
     * @return true:存在/false:不存在
     */
    public boolean existAlias(String alias) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        long count = this.queryFactory
                .select(Expressions.ONE)
                .from(qBiTableview)
                .where(qBiTableview.viewAlias.toUpperCase().eq(alias.toUpperCase()))
                .fetchCount();
        return count > 0 ? true : false;
    }

    /**
     * 查询数据视图列表，数据权限
     *
     * @return 数据视图集合
     */
    public List<TableViewVO> selectTableview() {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        return this.queryFactory
                .select(
                        Projections.bean(
                                TableViewVO.class,
                                qBiTableview.id,
                                qBiTableview.name,
                                qBiTableview.viewAlias,
                                qBiDatamartCatalogRe.datamartId
                        )
                ).from(qBiTableview)
                .leftJoin(qBiDatamartCatalogRe)
                .on(qBiTableview.id.eq(qBiDatamartCatalogRe.relId))
                .fetch();
    }

    /**
     * 查询数据视图列表
     *
     * @param tableview 数据视图信息
     * @param dataIds 数据id
     * @return 数据视图集合
     */
    public List<TableViewVO> selectTableviewList4Perms(TableViewVO tableview,
                                                       List<String> dataIds) {
        QBiTableview qBiTableview = QBiTableview.biTableview;
        QBiService qBiService = QBiService.biService;
        QBiDatamartCatalogRe qBiDatamartCatalogRe = QBiDatamartCatalogRe.biDatamartCatalogRe;
        BooleanExpression booleanExpression = JPAExpressions
                .select(qBiService.id.count())
                .from(qBiService)
                .where(
                        qBiService.type.eq("1")
                                .and(qBiService.relId.eq(qBiTableview.id))
                )
                .fetchAll()
                .gt(0L);
        BooleanExpression booleanExpression1 = new CaseBuilder()
                .when(booleanExpression)
                .then(true)
                .otherwise(false);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(tableview.getId())) {
            booleanBuilder.and(qBiTableview.id.eq(tableview.getId()));
        }
        if (!StringUtils.isEmpty(tableview.getViewAlias())) {
            booleanBuilder.and(qBiTableview.viewAlias.eq(tableview.getViewAlias()));
        }
        if (!StringUtils.isEmpty(tableview.getName())) {
            booleanBuilder
                    .and(
                            qBiTableview.name.toUpperCase().like("%" + tableview.getName().toUpperCase() + "%")
                                    .or(qBiTableview.viewAlias.toUpperCase().like("%" + tableview.getName().toUpperCase() + "%"))
                    );
        }
        if (!StringUtils.isEmpty(tableview.getDsId())) {
            booleanBuilder.and(qBiTableview.dsId.eq(tableview.getDsId()));
        }
        if (!StringUtils.isEmpty(tableview.getInlineDim())) {
            booleanBuilder.and(qBiTableview.inlineDim.eq(tableview.getInlineDim()));
        }
        if (!StringUtils.isEmpty(tableview.getDeleteState())) {
            booleanBuilder.and(qBiTableview.deleteState.eq(tableview.getDeleteState()));
        }
        if (!StringUtils.isEmpty(tableview.getCheckState())) {
            booleanBuilder.and(qBiTableview.checkState.eq(tableview.getCheckState()));
        }
        if (!StringUtils.isEmpty(tableview.getCreateView())) {
            booleanBuilder.and(qBiTableview.createView.eq(tableview.getCreateView()));
        }
        if (!StringUtils.isEmpty(tableview.getPath())) {
            QBiDatamartCatalog qBiDatamartCatalog = QBiDatamartCatalog.biDatamartCatalog;
            BooleanBuilder booleanBuilder1 = new BooleanBuilder();
            SubQueryExpression subQueryExpression = JPAExpressions
                    .select(qBiDatamartCatalog.id)
                    .from(qBiDatamartCatalog)
                    .where(qBiDatamartCatalog.path.like("%" + tableview.getPath() + "%"));
            SubQueryExpression subQueryExpression1 = JPAExpressions
                    .select(qBiDatamartCatalogRe.relId)
                    .from(qBiDatamartCatalogRe)
                    .where(
                            qBiDatamartCatalogRe.dataType.eq("1")
                                    .and(qBiDatamartCatalogRe.datamartId.in(subQueryExpression))
                    );
            booleanBuilder.and(qBiTableview.id.in(JPAExpressions.select(subQueryExpression1)));
        }
        if (dataIds != null && dataIds.size() == 0) {
            booleanBuilder.and(Expressions.ONE.eq(2));
        }
        if (dataIds != null && dataIds.size() > 0) {
            booleanBuilder.and(qBiTableview.id.in(dataIds));
        }
        return this.queryFactory
                .select(
                        Projections.bean(
                                TableViewVO.class,
                                qBiTableview.id,
                                qBiTableview.name,
                                qBiTableview.viewAlias,
                                qBiTableview.checkState,
                                qBiTableview.inlineDim,
                                qBiTableview.createView,
                                qBiTableview.desc,
                                qBiTableview.createTime,
                                qBiTableview.dsId,
                                qBiDatamartCatalogRe.datamartId,
                                ExpressionUtils.as(booleanExpression1, "service")
                        )
                )
                .from(qBiTableview)
                .leftJoin(qBiDatamartCatalogRe)
                .on(
                        qBiTableview.id.eq(qBiDatamartCatalogRe.relId)
                                .and(qBiDatamartCatalogRe.dataType.eq("1"))
                )
                .where(booleanBuilder)
                .orderBy(qBiTableview.createTime.desc()).fetch();
    }
}
