package com.gdut.minsappofteachingforchildren.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileReader;
import com.gdut.minsappofteachingforchildren.VO.Result;
import com.gdut.minsappofteachingforchildren.entity.FIle;
import com.gdut.minsappofteachingforchildren.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Api(value = "视频", tags = "视频管理服务")
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${dns}")
    private String dns;

    @ApiOperation("图片上传")
    @PostMapping("/upload")
    public Result upload(@RequestPart("file") MultipartFile file, HttpServletRequest req) throws IOException {

        //存储路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String path1 = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\img\\";
        String path2 = applicationHome.getDir().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\img\\";
        String path3 = applicationHome.getDir().getAbsolutePath() + "\\src\\main\\resources\\static\\img\\";
        HashMap<String, String> map = new HashMap<>();
        map.put("path1" ,path1);
        map.put("path2" ,path2);
        map.put("path3" ,path3);
        map.put("separatorChar", File.separator);
//        if(!file.isEmpty()){
//            //文件名
//            String filename = UUID.randomUUID()+"-"+file.getOriginalFilename();
//            file.transferTo(new File(path+filename));
//            FIle FIle = new FIle();
//            FIle.setLocalAddress("img/"+filename);
//            String geshi = filename.substring(filename.lastIndexOf(".")+1,filename.length());
//            FIle.setSuffix(geshi);
//            if (fileService.save(FIle)) {
//                return new Result("200","上传成功");
//            }
//        }
        return new Result("500","上传失败", map);
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("获取图片")
    @GetMapping("/download/{id}")
    public Result download(@PathVariable("id") String id){
        Long vid = Long.parseLong(id);
        FIle FIle = fileService.getById(vid);
        if (FIle != null){
            return new Result("200","获取成功",dns+ FIle.getLocalAddress());
        }else{
            return new Result("500","获取失败");
        }
    }

    @PostMapping("/test")
    public void test(){
        Date date = DateUtil.date();
        System.out.println(date.toString());
    }

    @GetMapping("/getText/{id}")
    public Result getText(@PathVariable("id") String id) {
        //存储路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String path = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\";

        Long vid = Long.parseLong(id);
        FIle FIle = fileService.getById(vid);

        FileReader fileReader = new FileReader(path+ FIle.getLocalAddress());
        String result = fileReader.readString();
        return new Result("200","获取成功",result);
    }

}

