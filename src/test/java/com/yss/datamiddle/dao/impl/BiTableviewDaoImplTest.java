package com.yss.datamiddle.dao.impl;

import com.yss.datamiddle.entities.BiTableview;
import com.yss.datamiddle.vo.tableview.TableView;
import com.yss.datamiddle.vo.tableview.TableViewVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BiTableviewDaoImplTest {

    @Autowired
    private BiTableviewDaoImpl biTableviewDao;

    @Test
    void testSelectTableviewList() {
        TableViewVO tableViewVO = new TableViewVO();
        // tableViewVO.setName("Wr_tEs");
        // tableViewVO.setId("20090814405164000918");
        // tableViewVO.setPath("20091813242000000572");
        /*List<TableViewVO> tableViewVOList = biTableviewDao.selectTableviewList(tableViewVO);
        for (TableViewVO temp : tableViewVOList) {
            System.out.println(temp.getName());
        }*/
        // TableViewVO tableView = biTableviewDao.selectTableviewById("20090814405164000918");
        // TableView tableView = biTableviewDao.selectTableviewByName("wr_tEsT1");
        // List<TableView> tableView = biTableviewDao.selectTableviewByNames(new String[]{"wr_test1", "wr_2hive视图"});

        /*List<String> ids = new ArrayList(){{add("20091617530215000018");add("20091813242000000002");}};
        Boolean aBoolean = biTableviewDao.validateDataNonChecked(ids);
        System.out.println(aBoolean);*/

        /*TableView tableView = biTableviewDao.selectTableviewByName("wr_2hive20200918");
        int i = biTableviewDao.deleteTableviewById(tableView.getId());
        System.out.println(i);
        BiTableview biTableview = new BiTableview();
        BeanUtils.copyProperties(tableView, biTableview);
        biTableview.setExtdataServiceState("0");
        biTableviewDao.insertTableview(biTableview);*/

        List<Map<String, Object>> map = biTableviewDao.getViewDatamartCatalogData("206");
        System.out.println(map.size());

    }

}