package com.pd.pojo;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @Author WL
 * @Date 2020-11-5 14:37
 * @Version 1.0
 * 搜索栏查询商品的pojo
 */
@Data
public class Item implements Serializable {

    @Field("id")
    private String id;
    @Field("title")
    private String title;
    @Field("sellPoint")
    private String sellPoint;
    @Field("price")
    private Long price;
    @Field("image")
    private String image;

}
