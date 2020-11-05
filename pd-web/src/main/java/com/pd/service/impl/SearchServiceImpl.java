package com.pd.service.impl;

import com.pd.pojo.Item;
import com.pd.service.SearchService;
import com.sun.source.doctree.InlineTagTree;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;

/**
 * @Author WL
 * @Date 2020-11-5 14:44
 * @Version 1.0
 * solr的全局搜索
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public List<Item> search(String key) throws IOException, SolrServerException {
        log.info("关键词:" + key);
        // 封装关键词和分页信息
        SolrQuery query = new SolrQuery(key);
        query.setStart(0);  // 起始行
        query.setRows(20);  // 行数
        // 用solrclient执行查询，并得到查询结果
        QueryResponse response = solrClient.query(query);   // Http请求 调用solr服务器
        // 将json结果转成一组Item对象返回  原生提供的方法
        List<Item> list = response.getBeans(Item.class);
        log.info(key + "搜索结果：" + list);
        return list;
    }
}
