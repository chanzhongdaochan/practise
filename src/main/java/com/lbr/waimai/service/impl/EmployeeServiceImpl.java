package com.lbr.waimai.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbr.waimai.entity.Employee;
import com.lbr.waimai.mapper.EmployeeMapper;
import com.lbr.waimai.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
