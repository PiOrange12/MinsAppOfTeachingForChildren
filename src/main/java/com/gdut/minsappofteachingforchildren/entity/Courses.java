package com.gdut.minsappofteachingforchildren.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @TableName t_courses
 */
@TableName(value ="t_courses")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Courses implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long cid;

    @NotNull
    private Long uid;

    @NotNull
    private String type;

    @NotBlank
    private String text;

    @NotNull
    private String name;

    private String author;

    @Max(value = 100)
    @Min(value = 0)
    private int maxage;

    @Max(value = 100)
    @Min(value = 0)
    private int minage;

    @TableLogic
    private Integer isdeleted = 0;

    @Version
    private Integer version = 1;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}