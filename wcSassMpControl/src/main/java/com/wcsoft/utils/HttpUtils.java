package com.wcsoft.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 网络请求工具类
 * 
 * @author Administrator
 *
 */
public class HttpUtils {

	public static String sendPost(String url, String joinParams) {
		try {
			HttpResponse response = new DefaultHttpClient().execute(getHttpPost(url, joinParams, "UTF-8"));
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取HTTP对象
	 * 
	 * @param url
	 * @param params
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static HttpPost getHttpPost(String url, String params, String encode)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(params, ContentType.create("application/json", "UTF-8"));
		httpPost.setEntity(entity);
		return httpPost;
	}

	public static String doGet(String url, Map<String, Object> params) {
		// 创建client
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建一个uri对象
		URIBuilder uriBuilder;
		try {
			uriBuilder = new URIBuilder(url);
			if (params != null) {
				for (String name : params.keySet()) {
					uriBuilder.addParameter(name, (String) params.get(name));
				}
			}
			// get方法
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			// 执行请求
			CloseableHttpResponse response = client.execute(httpGet);
			// 获取打印结果
			// 查看状态码
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			// 打印内容
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");
			response.close();
			client.close();
			return result;
		} catch (URISyntaxException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	
	public static String postBase64File(String url, String fieldName, String base64Content, String mimeType, String fileName) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
 
            // Create a multipart entity
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(StandardCharsets.UTF_8);
 
            // Add the base64 encoded file content as a byte array body
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Content);
            File file = new File(fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(decodedBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileBody bin = new FileBody(file);// 文件
            HttpEntity reqEntity = MultipartEntityBuilder.create()
            		              .addPart("media", bin).build();// 请求体. media为文件对于的key值
            post.setEntity(reqEntity);
//            builder.addBinaryBody(
//                    fieldName,
//                    decodedBytes,
//                    ContentType.create(mimeType),
//                    fileName // You can set any filename here, it won't be used for content
//            );
// 
//            HttpEntity multipart = builder.build();
//            post.setEntity(multipart);
 
            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            }
        }
    }
}
