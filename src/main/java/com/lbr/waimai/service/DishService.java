package com.lbr.waimai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbr.waimai.dto.DishDto;
import com.lbr.waimai.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息同时更新口味信息
    public void updateWithFlavor(DishDto dishDto);
}
