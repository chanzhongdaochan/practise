package com.lbr.waimai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbr.waimai.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
