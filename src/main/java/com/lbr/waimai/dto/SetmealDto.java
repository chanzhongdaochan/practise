package com.lbr.waimai.dto;

import com.lbr.waimai.entity.Setmeal;
import com.lbr.waimai.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
