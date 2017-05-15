package com.csl.serviceImpl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by csl on 2017/5/11.
 */
@Service
public class ImageServiceImpl {
    //生成上传凭证，然后准备上传
    private String accessKey = "QGMjxMoLa6489NEh_LexbISnK9mLjgFFzonYzH8m";
    private String secretKey = "lz_h3maZ522ROtiZtitsr1njH-UFCJuBkQ0u_k_Q";
    private String bucket = "ptp-image";

    public String upImage(byte[] uploadBytes) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        String result = "http://opru416u1.bkt.clouddn.com/";
        try {
            Auth auth = Auth.create(this.accessKey, this.secretKey);
            String upToken = auth.uploadToken(this.bucket);
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                result += putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception exception) {

        }
        return result;
    }
}
