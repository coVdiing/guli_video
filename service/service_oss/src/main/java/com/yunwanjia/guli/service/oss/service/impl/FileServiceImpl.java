package com.yunwanjia.guli.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.yunwanjia.guli.service.oss.service.FileService;
import com.yunwanjia.guli.service.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream inputStream, String module, String origin) {
        // 读取配置文件信息
        String bucketname = ossProperties.getBucketname();
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();

        OSS ossClient = createOssClient(endpoint,keyid,keysecret,bucketname);

        // 构建objectName：文件路径 avatar/2021/03/10
        String folder = new DateTime().toString("yyyy/MM/dd");
        String filename = UUID.randomUUID().toString();
        String fileExtension = origin.substring(origin.lastIndexOf("."));
        String key = module + "/" + folder + "/" + filename + fileExtension;

        // 上传文件流
        ossClient.putObject(ossProperties.getBucketname(), key, inputStream);

        // 关闭ossclient
        ossClient.shutdown();

        // 返回url
        return "https://"+bucketname+"."+endpoint+"/"+key;
    }

    @Override
    public void removeFile(String url) {
        // 读取配置文件信息
        String bucketname = ossProperties.getBucketname();
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();

        OSS ossClient = createOssClient(endpoint,keyid,keysecret,bucketname);
        String prefix = "https://"+bucketname+"."+endpoint+"/";
        String objectName = url.substring(prefix.length());
        ossClient.deleteObject(bucketname,objectName);
    }

    private OSS createOssClient(String endpoint,String keyid,String keysecret,String bucketname) {
        // 创建OssClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucketname)) {
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }
        return ossClient;
    }
}
