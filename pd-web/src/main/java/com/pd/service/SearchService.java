package com.pd.service;

import com.pd.pojo.Item;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * @Author WL
 * @Date 2020-11-5 14:40
 * @Version 1.0
 * 搜索业务层
 */
public interface SearchService {

    List<Item> search(String key) throws IOException, SolrServerException;

}
