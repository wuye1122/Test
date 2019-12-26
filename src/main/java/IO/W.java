package IO;
import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.*;

import java.util.ArrayList;
import java.util.List;

public class W {
    public static void main(String[] args) {
        System.out.println(111);
        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        build.connectionsPerHost(100);// ��Ŀ�����ݿ��ܹ����������connection����
        build.threadsAllowedToBlockForConnectionMultiplier(100);// �����ǰ���е�connection����ʹ���У�ÿ��connection���߳��Ŷӵȴ���
        build.maxWaitTime(1000 * 60 * 2);// ���ȴ�ʱ��
        build.connectTimeout(1000 * 60 * 1);// �����ݿ⽨�����ӵ�timeout
        MongoClientOptions mongoOptions = build.build();
        String hosts = "132.232.168.98:27017";
        List<ServerAddress> servers = new ArrayList<ServerAddress>();
        for (String host : hosts.split(";")) {
            servers.add(new ServerAddress(host));
        }
        // MongoClient mongo = new MongoClient(servers, mongoOptions);
        MongoCredential credentials = MongoCredential
                .createScramSha1Credential("zl20181023", "admin",
                        "zl20181023".toCharArray());
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(credentials);
        MongoClient mongo = new MongoClient(servers, credentialsList,
                mongoOptions);




        MongoDatabase db = mongo.getDatabase("082702");// 0101108000
        MongoCollection<Document> collection = db.getCollection("chatLog_back");
        Document doc = new Document();
        doc.append("key", "value");
        doc.append("username", "jack");
        doc.append("age", 31);

        // д������
        collection.insertOne(doc);
    }
}
