package com.senpure.base.util;

import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


/**
 * 不需要本地秘钥
 * 强制发起https请求
 */
public class HttpsConnect {

    private static TrustManager[] trustManagers = new TrustManager[1];
    private static HostnameVerifier hostnameVerifier;
    private static SSLSocketFactory sslSocketFactory;

    static {
        hostnameVerifier = new HostnameVerifierAllBank();
        trustManagers[0] = new TrustManagerAllBank();

        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, trustManagers, null);
            sslSocketFactory = ctx.getSocketFactory();
        } catch (KeyManagementException e) {

            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

    }

    public static String sendGetRequest(String url) {
        String result = null;
        StringBuilder sb = new StringBuilder("");
        HttpsURLConnection con = null;
        int status = 0;
        try {
            URL urlObject = new URL(url);
            con = (HttpsURLConnection) urlObject .openConnection();
            //con.setRequestMethod("GET");默认get可以不用写
            con.setSSLSocketFactory(sslSocketFactory);
            con.setHostnameVerifier(hostnameVerifier);
            status = con.getResponseCode();
            InputStream urlStream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlStream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (MalformedURLException e) {

            result = "the_requested_address_anomaly";
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

            sb.delete(0, sb.length());
            result = sb.append("HTTP STATUS:").append(status).toString();
        }

        return result;

    }

    public static String sendPostRequest(String url, String content) {
        String result = null;
        StringBuilder sb = new StringBuilder();
        HttpsURLConnection con = null;
        int status = 0;
        try {
            URL urlObject = new URL(url);
            con = (HttpsURLConnection) urlObject.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length",
                    String.valueOf(content.length()));
            con.setUseCaches(false);
            con.setSSLSocketFactory(sslSocketFactory);
            con.setHostnameVerifier(hostnameVerifier);
            con.setDoOutput(true);
            con.getOutputStream().write(content.getBytes("utf-8"));
            con.getOutputStream().flush();
            con.getOutputStream().close();
            status = con.getResponseCode();
            InputStream urlStream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlStream, "UTF-8"));
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);

            }
            result = sb.toString();
        } catch (MalformedURLException e) {
            result = "the_requested_address_anomaly";
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {
            sb.delete(0, sb.length());
            result = sb.append("HTTP STATUS:").append(status).toString();
            e.printStackTrace();
        }
        return result;

    }

    private static class TrustManagerAllBank implements X509TrustManager {

        @Override
        public void checkClientTrusted(
                java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public void checkServerTrusted(
                java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {

            return null;
        }
    }

    private static class HostnameVerifierAllBank implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {

            return true;
        }
    }

}
