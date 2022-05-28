package com.gdut.minsappofteachingforchildren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdut.minsappofteachingforchildren.entity.Moment;
import com.gdut.minsappofteachingforchildren.mapper.MomentMapper;
import com.gdut.minsappofteachingforchildren.service.MomentService;
import org.springframework.stereotype.Service;

@Service
public class MomentServiceImpl extends ServiceImpl<MomentMapper, Moment> implements MomentService {
}
