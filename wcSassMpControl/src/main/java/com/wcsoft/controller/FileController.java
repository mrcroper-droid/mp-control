package com.wcsoft.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.wcsoft.entity.RespBean;
import com.wcsoft.utils.DateUtils;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Roper
 * @since 2023-11-09
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseAbstractController{

	 /**
     * 上传文件返回地址url
     */
    @RequestMapping(value = "/updFile", method = RequestMethod.POST)
    public RespBean updFile(MultipartFile file) throws IOException {
    	if (file.getSize() > 524288000) {
	      return RespBean.error("文件大小超过限制500MB");
	    }
        String fileUri = DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT2)+"//"+file.getOriginalFilename(); 
        File avatarFile = new File(filePath + fileUri);
        FileUtils.copyInputStreamToFile(file.getInputStream(), avatarFile);
        RespBean res = RespBean.ok("上传成功", fileUrl + fileUri);
        return res;
    }
    /**
     * 上传文件返回地址path
     */
    @RequestMapping(value = "/updFile2Path", method = RequestMethod.POST)
    public RespBean updFile2Path(MultipartFile file) throws IOException {
      	if (file.getSize() > 524288000) {
  	      return RespBean.error("文件大小超过限制500MB");
  	    }
        String fileUri = DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT2)+File.separator+file.getOriginalFilename(); 
        File avatarFile = new File(filePath + fileUri);
        FileUtils.copyInputStreamToFile(file.getInputStream(), avatarFile);
        RespBean res = RespBean.ok("上传成功", filePath + fileUri);
        return res;
    }
    
    
    @RequestMapping("pdfToImg")
    public Object pdfToImg(@RequestParam String url) {
    	JSONArray jsonArr = new JSONArray();
    	  try{  
    		  CloseableHttpClient httpClient = HttpClients.createDefault();
              HttpGet request = new HttpGet(url);  
              try (CloseableHttpResponse response = httpClient.execute(request)) {  
                  if (response.getStatusLine().getStatusCode() == 200) {  
                	  InputStream pdfBytes = response.getEntity().getContent();  
    
                      try (PDDocument document = PDDocument.load(pdfBytes)) {  
                          PDFRenderer renderer = new PDFRenderer(document);  
    
                          for (int i = 0; i < document.getNumberOfPages(); ++i) { 
                        	  JSONObject job = new JSONObject();
                        	 
                              BufferedImage image = renderer.renderImageWithDPI(i, 100);  
                              ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
                              ImageIO.write(image, "PNG", outputStream);  
                              byte[] imageBytes = outputStream.toByteArray();  
                              String base64Image = Base64.getEncoder().encodeToString(imageBytes);  
                              job.put("page", i+1);
                              job.put("url", "data:image/png;base64,"+ base64Image);
                              jsonArr.add(job);
                          }  
    
                      }  
                  }  
              }  
    	  }catch (Exception e) {
    		  e.printStackTrace();
    		  return RespBean.error("下载文件失败");
    	  }
    	  return jsonArr;
    }
	
	
}
