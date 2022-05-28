package com.gdut.minsappofteachingforchildren.entity;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moment {
    private Long momentId;
    private Long uid;
    private List<String> img;
    private List<HashMap<String,String>> content;
}
