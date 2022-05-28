package com.gdut.minsappofteachingforchildren.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/moment")
public class MomentController {

    @PostMapping("/share")
    public boolean releaseMoment(HttpServletRequest req, @RequestPart("file") MultipartFile[] file){
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println("Key = " + entry.getKey());
            for (String s : entry.getValue()) {
                System.out.println("Value = " + s);
            }
        }
        System.out.println("********************");
        System.out.println(file.length);
        return false;
    }

    @PostMapping("/comment")
    public void releaseComment(HttpServletRequest req){



    }
}
