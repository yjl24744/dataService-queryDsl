package com.yss.datamiddle.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 分页结果
 * @author: fangzhao
 * @create: 2020/3/24 13:09
 * @update: 2020/3/24 13:09
 */
@Data
public class PageModel<T> {

    private long pageNum;
    private long pageSize;
    private long total;
    private List<T> list = new ArrayList<>();

}
