package com.lbr.waimai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbr.waimai.dto.DishDto;
import com.lbr.waimai.entity.Dish;
import com.lbr.waimai.entity.DishFlavor;
import com.lbr.waimai.mapper.DishMapper;
import com.lbr.waimai.service.DishFlavorService;
import com.lbr.waimai.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**新增菜品同时保存对应的口味数据
     *
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto){
        //保存菜品基本信息
        this.save(dishDto);


        Long dishId = dishDto.getId();//菜品id

        List<DishFlavor> flavors=dishDto.getFlavors();
        flavors =flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());


        //保存菜品口味信息

        dishFlavorService.saveBatch(flavors);

    }

    /**
     * 根据id查询菜品和对应的口味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);


        //查询菜品口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors=dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);




        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);

        //清理当前菜品对应的口味数据
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());

        dishFlavorService.remove(queryWrapper);

        //添加当前提交过来的口味数据

        List<DishFlavor> flavors=dishDto.getFlavors();
        flavors =flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

       dishFlavorService.saveBatch(flavors);
    }


}
