package com.lbr.waimai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbr.waimai.entity.DishFlavor;
import com.lbr.waimai.mapper.DishFlavorMapper;
import com.lbr.waimai.service.DishFlavorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DishFlavorServiceIml extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
