package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.Avatar;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class ImgFileService {
    protected boolean processFile(Avatar d, @RequestParam("file") MultipartFile imageFile) throws IOException {
        String uploadPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
        System.out.println(new Date() + "   ====>   " +"Uploading file.........");
        if (imageFile == null) {
            return false;
        }
        // 获得文件名：
        String originalFilename = imageFile.getOriginalFilename();

        //获得文件后缀
        String extName = null;
        int i = originalFilename.lastIndexOf(".");
        if (i > 0) {
            extName = originalFilename.substring(i);
        }else{
            return false;
        }
        File file = new File(uploadPath, System.currentTimeMillis() + extName);

        //没有父级文件夹的话 就创建一个
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //以UUID 重命名文件
        String fileName = UUID.randomUUID().toString() + extName;
        imageFile.transferTo(new File(uploadPath, fileName).getAbsoluteFile());
        d.setAvatar("/images/" + fileName);
        System.out.println(new Date() + "   ====>   " +"Upload Success");
        return true;

    }
}
