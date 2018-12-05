package com.zimu.my2018.quyoulib.utils;

import android.content.Context;
import android.content.res.Resources;

import com.zimu.my2018.quyoulib.R;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public class SSLSocketUtils {

    public static final String CLIENT_AGREEMENT = "TLS";//使用协议

    public static SSLSocketFactory getSocketFactory(Context context) {
        SSLSocketFactory sslSocketFactory = null;

        try {
            //取得SSL的SSLContext实例
            SSLContext sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);

            //取得BKS密库实例
            KeyStore tks = KeyStore.getInstance(KeyStore.getDefaultType());
            tks.load(null);

            Resources resources = context.getResources();
            InputStream is = resources.openRawResource(R.raw.tests);
            try {
                //取得TrustManagerFactory的X509密钥管理器实例
                String defaultAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory trustManager = TrustManagerFactory.getInstance(defaultAlgorithm);
                Certificate certificate = CertificateFactory.getInstance("X.509").generateCertificate(is);
                //设置自己的证书
                tks.setCertificateEntry("tests", certificate);
                //初始化密钥管理器
                trustManager.init(tks);
                //初始化SSLContext
                sslContext.init(null, trustManager.getTrustManagers(), null);
            } finally {
                is.close();
            }
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sslSocketFactory;
    }
}
