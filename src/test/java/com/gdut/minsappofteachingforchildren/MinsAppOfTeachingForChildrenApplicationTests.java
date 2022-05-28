package com.gdut.minsappofteachingforchildren;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdut.minsappofteachingforchildren.entity.Courses;
import com.gdut.minsappofteachingforchildren.service.CoursesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.List;

@SpringBootTest
class MinsAppOfTeachingForChildrenApplicationTests {

    @Autowired
    private CoursesService coursesService;

    @Test
    public void test1(){
        QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
        querywrapper.ge("minage",0)
                    .le("maxage",100);
        //条件查询
        List<Courses> result = coursesService.list(querywrapper);
    }

    @Test
    public void test2(){
        QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
        querywrapper.like("author","李白");
        List<Courses> result = coursesService.list(querywrapper);
    }

    @Test
    public void test3(){
        Courses course = new Courses();
        course.setText("《过故人庄》\n" +
                "故人具鸡黍，邀我至田家。\n" +
                "绿树村边合，青山郭外斜。\n" +
                "开轩面场圃，把酒话桑麻。\n" +
                "待到重阳日，还来就菊花。");
        course.setName("《过故人庄》");
        course.setType("诗歌");
        course.setAuthor("孟浩然");
        course.setMinage(6);
        course.setMaxage(18);
        //新增
        boolean result = coursesService.save(course);
        System.out.println(result);
    }


    @Test
    public void test4(){
        QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
        querywrapper.select("type");
        List<Courses> result = coursesService.list(querywrapper);
        for (Courses course : result) {
            System.out.println(course.getType());
        }
    }

    @Test
    public void test5(){
        boolean result = coursesService.removeById(47L);
    }

    @Test
    public void test6(){
        long cid = 1l;
        Courses courses = coursesService.getById(cid);
        String text = courses.getText();
        String newText = text.substring(text.indexOf("》") + 1);
        courses.setText(newText);

        UpdateWrapper<Courses> coursesUpdateWrapper = new UpdateWrapper<>();
        coursesUpdateWrapper.eq("cid", cid).set("text", newText);
        coursesService.update(courses, coursesUpdateWrapper);
    }

    @Test
    public void test7(){
        UpdateWrapper<Courses> coursesUpdateWrapper = new UpdateWrapper<>();
        coursesUpdateWrapper.eq("cid",19L)
                            .set("minAge",3);
        boolean result = coursesService.update(coursesUpdateWrapper);
    }

    @Test
    public void test8(){
        QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
        querywrapper.ge("minage",1);
        //条件查询
        Page<Courses> page = new Page<>(1,5);

        IPage<Courses> result = coursesService.page(page,querywrapper);
    }

    @Test
    public void test9(){

    }

    @Test
    public void test10(){
        /**
         *  SELECT * FROM t_courses
         */
        List<Courses> list1 = coursesService.getAllCoursesIncludedDeleted();
        /**
         *  SELECT cid,type,text,name,author,maxage,minage,isdeleted,version FROM t_courses WHERE isdeleted=0
         */
        List<Courses> list2 = coursesService.list();
        System.out.println("******************");
        System.out.println(list1.size());
        System.out.println(list2.size());

    }


    @Test
    public void test11(){

    }
}
