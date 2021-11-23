package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.DepartmentExample;
import com.megagao.production.ssm.domain.Employee;
import com.megagao.production.ssm.domain.EmployeeExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.EmployeeVO;
import com.megagao.production.ssm.mapper.DepartmentMapper;
import com.megagao.production.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> find() throws Exception {
        DepartmentExample example = new DepartmentExample();
        return departmentMapper.selectByExample(example);
    }

    @Override//显示数据
    public EUDataGridResult getList(int page, int rows, Department department) throws Exception {
//        //分页处理
        PageHelper.startPage(page, rows);

        List<Department> list = departmentMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override//插入数据
    public CustomResult insert(Department department) throws Exception {
        int i = departmentMapper.insert(department);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增部门信息失败");
        }
    }

    @Override
    public Department get(String departmentId) throws Exception {//通过主键查询一列数据
        return departmentMapper.selectByPrimaryKey(departmentId);
    }
    @Override
    public CustomResult delete(String string) throws Exception{
        int i=departmentMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }

    }
    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception{
        int i=departmentMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
    @Override
    public CustomResult updateAll(Department department) throws Exception{
        int i=departmentMapper.updateByPrimaryKey(department);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(Department department) throws Exception {
        int i=departmentMapper.updateByPrimaryKeySelective(department);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override//按照部门编号查询
    public EUDataGridResult searchDepartmentByDepartmentId(Integer page, Integer rows, String departmentId) throws Exception{
        PageHelper.startPage(page, rows);
        List<Department> list = departmentMapper.searchDepartmentByDepartmentId(departmentId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
    @Override//按照部名称查询
    public EUDataGridResult searchDepartmentByDepartmentName(Integer page, Integer rows, String departmentName) throws Exception{
        //分页处理
        PageHelper.startPage(page, rows);
        List<Department> list = departmentMapper.searchDepartmentByDepartmentName(departmentName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
