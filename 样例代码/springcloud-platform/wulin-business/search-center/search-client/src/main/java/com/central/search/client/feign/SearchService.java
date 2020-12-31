package com.central.search.client.feign;

import com.alibaba.fastjson.JSONObject;
import com.central.common.constant.ServiceNameConstants;
import com.central.common.model.PageResult;
import com.central.search.client.feign.fallback.SearchServiceFallbackFactory;
import com.central.search.model.SearchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wulin
 */
@FeignClient(name = ServiceNameConstants.SEARCH_SERVICE, fallbackFactory = SearchServiceFallbackFactory.class, decode404 = true)
public interface SearchService {
    /**
     * 查询文档列表
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     */
    @PostMapping(value = "/search/{indexName}")
    PageResult<JSONObject> strQuery(@PathVariable("indexName") String indexName, @RequestBody SearchDto searchDto);
}
