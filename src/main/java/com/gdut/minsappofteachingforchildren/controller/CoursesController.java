package com.gdut.minsappofteachingforchildren.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdut.minsappofteachingforchildren.entity.Courses;
import com.gdut.minsappofteachingforchildren.VO.Result;
import com.gdut.minsappofteachingforchildren.service.CoursesService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "课程", tags = "课程管理服务")
@Slf4j
@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping("/findAll")
    public Result<List<Courses>> findAll(){
        List<Courses> result = coursesService.list(null);
        if(result.size() != 0){
            return new Result<List<Courses>>("200","查询成功",result);
        }else{
            return new Result<List<Courses>>("400","没有对应数据");
        }
    }

    @GetMapping("/selectByAge/{minage}/{maxage}")
    public Result<List<Courses>> selectByAge(@PathVariable("minage") int minage, @PathVariable("maxage") int maxage){
        if(minage < 0 || maxage < 0 || minage >= maxage){
            return new Result<List<Courses>>("500","参数错误");
        }else{
            //mybatisplus条件构造器
            QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
            /**
             * ge: =>; le: <=
             */
            querywrapper.ge("minage",minage)
                        .le("maxage",maxage);
            List<Courses> result = coursesService.list(querywrapper);

            if(result.size() != 0){
                return new Result<List<Courses>>("200","查询成功",result);
            }else{
                return new Result<List<Courses>>("400","没有对应数据");
            }
        }
    }

    @GetMapping("/selectByAuthor/{Author}")
    public Result<List<Courses>> selectByAuthor(@PathVariable("Author") String Author){

        QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
        querywrapper.like("author",Author);                     //模糊查询，相当于 author = %Author%
        List<Courses> result = coursesService.list(querywrapper);

        if(result.size() != 0){
            return new Result<List<Courses>>("200","查询成功",result);
        }else{
            return new Result<List<Courses>>("400","没有对应数据");
        }
    }

    @GetMapping("/selectByType/{type}")
    public Result<List<Courses>> selectByType(@PathVariable("type") String type){

        QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
        querywrapper.like("type",type);                         //模糊查询，相当于 type = %type%
        List<Courses> result = coursesService.list(querywrapper);

        if(result.size() != 0){
            return new Result<List<Courses>>("200","查询成功",result);
        }else{
            return new Result<List<Courses>>("400","没有对应数据");
        }
    }

    @GetMapping("/selectByName/{name}")
    public Result<List<Courses>> selectByName(@PathVariable("name") String name){
        QueryWrapper<Courses> querywrapper = new QueryWrapper<>();
        querywrapper.like("name",name);                         //模糊查询，相当于 name = %name%
        List<Courses> result = coursesService.list(querywrapper);

        if(result.size() != 0){
            return new Result<List<Courses>>("200","查询成功",result);
        }else{
            return new Result<List<Courses>>("400","没有对应数据");
        }
    }

    @GetMapping("/selectById/{id}")
    public Result<Courses> selectById(@PathVariable("id") String id){
        Long cid = Long.parseLong(id);
        Courses result = coursesService.getById(cid);
        if(result != null){
            return new Result<Courses>("200","查询成功",result);
        }else{
            return new Result<Courses>("400","没有对应数据");
        }
    }

    @GetMapping("/removeById/{id}")
    public Result removeById(@PathVariable("id") String id){
        Long cid = Long.parseLong(id);
        boolean result = coursesService.removeById(cid);
        if(result == true){
            return new Result("200","删除成功");
        }else{
            return new Result("400","删除失败");
        }
    }

    @GetMapping("/getAllCoursesIncludedDeleted")
    public Result<List<Courses>> getAllCoursesIncludedDeleted(){
        List<Courses> result = coursesService.getAllCoursesIncludedDeleted();
        if(result != null){
            return new Result<List<Courses>>("200","查询成功",result);
        }else{
            return new Result<List<Courses>>("400","没有对应数据");
        }
    }

    @GetMapping("/getDelectedCourses")
    public Result<List<Courses>> getDelectedCourses(){
        List<Courses> result = coursesService.getDelectedCourses();
        if(result != null){
            return new Result<List<Courses>>("200","查询成功",result);
        }else{
            return new Result<List<Courses>>("400","没有对应数据");
        }
    }

    /**
     *
     * @param courses
     * @return
     */
    @PostMapping("/create")
    public Result create(@RequestBody @Valid Courses courses){
        boolean result = coursesService.save(courses);
        if(result){
            return new Result("200", "新增成功");
        }else{
            return new Result("400", "新增失败");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid Courses courses){
        boolean result = coursesService.updateById(courses);
        if(result){
            return new Result("200", "更新成功", result);
        }else{
            return new Result("400", "更新失败");
        }
    }
}
