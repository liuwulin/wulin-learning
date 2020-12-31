package com.central.search.service;

import java.util.Map;

/**
 * @Author: wulin
 * @date 2019/4/24
 */
public interface IAggregationService {
    /**
     * 访问统计聚合查询
     * @param indexName 索引名
     * @param routing es的路由
     * @return
     */
    Map<String, Object> requestStatAgg(String indexName, String routing);
}
