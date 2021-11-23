package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.domain.CustomExample;
import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.DepartmentExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.mapper.CustomMapper;
import com.megagao.production.ssm.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    CustomMapper customMapper;

    @Override
    public List<Custom> find() throws Exception {

        CustomExample example=new CustomExample();
        return customMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(int page, int rows, Custom custom) throws Exception {
        PageHelper.startPage(page, rows);

        List<Custom> list = customMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(Custom custom) {//*****有问题这
        int i = customMapper.insert(custom);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增顾客信息失败");
        }
    }


    @Override
    public Custom get(String string) throws Exception {
        return customMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=customMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=customMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateAll(Custom custom) throws Exception {
        int i=customMapper.updateByPrimaryKey(custom);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(Custom custom) throws Exception {
        int i=customMapper.updateByPrimaryKeySelective(custom);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
