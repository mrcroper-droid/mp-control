package com.wcsoft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcsoft.entity.Params;
import com.wcsoft.mapper.ParamsMapper;
import com.wcsoft.service.ParamsService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 全局参数表 服务实现类
 * </p>
 *
 * @author Roper
 * @since 2023-10-11
 */
@Service
public class ParamsServiceImpl extends ServiceImpl<ParamsMapper, Params> implements ParamsService {

}
