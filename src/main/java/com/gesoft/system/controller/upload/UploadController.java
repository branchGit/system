package com.gesoft.system.controller.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@Slf4j
public class UploadController {


    @PostMapping("/upload")
    String login(@RequestParam(name = "inputVal" ,required = false) String inputVal,@RequestParam("file") MultipartFile file) {

        String contentType = file.getContentType();   //图片文件类型
        String fileName = file.getOriginalFilename();  //图片名字
        log.info("表单输入框:"+inputVal);
        log.info("文件类型:"+contentType);
        log.info("文件名字:"+fileName);

        try {

            String path  = ResourceUtils.getURL("classpath:templates").getPath();

            InputStream ins =  file.getInputStream();
            File f=new File(path+"\\"+file.getOriginalFilename());
            log.info("文件路径"+path+file.getOriginalFilename());
            inputStreamToFile(ins, f);
            log.info("上传成功");
            return "{\"result\":\"true\"}";
        } catch (IOException e) {
            e.printStackTrace();
            log.info("上传失败");
            return "{\"result\":\"false\"}";
        }
    }

    @PostMapping("/picList")
    public String  picList(){
        
        String  jsonString = "{\"result\":\"true\",\"list\":[{\"imgurl\":\"https://www.apiview.com/Public/img/logo.png\"},{\"imgurl\":\"http://www.ge-soft.com/static/images/logo.png\"},{\"imgurl\":\"http://www.sytouch.com/img/u27.png\"},{\"imgurl\":\"http://www.nsi-soft.com/nsi/pages/website/webpage/images/logo.png\"},{\"imgurl\":\"https://www.baidu.com/img/baidu_jgylogo3.gif\"}]}";

        return jsonString;
    }


    public static void inputStreamToFile(InputStream ins,File file){
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192000];
            while ((bytesRead = ins.read(buffer, 0, 8192000)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @PostMapping("/uploadPic")
    String login(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();  //图片名字
        try {
            String uploadsuffix = "/images/upload/";
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");

            //如果上传目录为/static/images/upload/，则可以如下获取：
            File upload = new File(path.getAbsolutePath(),"static"+uploadsuffix);
            if(!upload.exists()) upload.mkdirs();
            //在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
            //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/

            InputStream ins =  file.getInputStream();
            File f=new File(upload+"\\"+file.getOriginalFilename());
            log.info("文件路径"+upload+"\\"+file.getOriginalFilename());
            inputStreamToFile(ins, f);
            log.info("上传成功");
            return "{\"result\":\"true\",\"picUrl\":\""+uploadsuffix+file.getOriginalFilename()+"\"}";
        } catch (IOException e) {
            e.printStackTrace();
            log.info("上传失败");
            return "{\"result\":\"false\"}";
        }
    }

}