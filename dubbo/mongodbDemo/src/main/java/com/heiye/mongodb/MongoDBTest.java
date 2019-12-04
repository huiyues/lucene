package com.heiye.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBTest {

    public static void main(String[] args) {
        //创建连接
        MongoClient mongoClient = new MongoClient("localhost",27017);
        //连接数据库
        MongoDatabase database = mongoClient.getDatabase("test");
        //获取所有文档
        MongoCollection<Document> document = database.getCollection("person");
        //查询第一个文档
        Document first = document.find().first();
        System.out.println(first);
    }
}
