package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.FinalCountCheck;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.EmployeeVO;
import com.megagao.production.ssm.mapper.FinalCountCheckMapper;
import com.megagao.production.ssm.service.FCountCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FCountCheckServiceImpl implements FCountCheckService {
    @Autowired
    FinalCountCheckMapper finalCountCheckMapper;
    @Override
    public EUDataGridResult getList(Integer page, Integer rows, FinalCountCheck finalCountCheck) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<FinalCountCheck> list = finalCountCheckMapper.find(finalCountCheck);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalCountCheck> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public FinalCountCheck get(String string) throws Exception {
        return finalCountCheckMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i =finalCountCheckMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult insert(FinalCountCheck finalCountCheck) throws Exception {
        int i = finalCountCheckMapper.insert(finalCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增员工信息失败");
        }
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i = finalCountCheckMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult updateAll(FinalCountCheck finalCountCheck) throws Exception {
        int i =finalCountCheckMapper.updateByPrimaryKey(finalCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改信息失败");
        }
    }

    @Override
    public CustomResult updateNote(FinalCountCheck finalCountCheck) throws Exception {
        int i=finalCountCheckMapper.updateByPrimaryKeySelective(finalCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
