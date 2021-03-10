package com.yunwanjia.guli.service.oss.service;

import java.io.InputStream;

public interface FileService {

    /**
     * 阿里云oss文件上传
     * @param inputStream 输入流
     * @param module 文件夹名称
     * @param origin 原始文件名
     * @return
     */
    String upload(InputStream inputStream, String module, String origin);
}
