package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.ProcessCountCheck;
import com.megagao.production.ssm.domain.ProcessMeasureCheck;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.ProcessCountCheckMapper;
import com.megagao.production.ssm.service.PCountCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PCountCheckServiceImpl implements PCountCheckService {

    @Autowired
    ProcessCountCheckMapper processCountCheckMapper;
    @Override
    public EUDataGridResult getList(int page, int rows, ProcessCountCheck processCountCheck) throws Exception {
        PageHelper.startPage(page, rows);
        List<ProcessCountCheck> list = processCountCheckMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<ProcessCountCheck> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=processCountCheckMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public ProcessCountCheck get(String string) throws Exception {
        return processCountCheckMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=processCountCheckMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(ProcessCountCheck processCountCheck) throws Exception {
        int i = processCountCheckMapper.insert(processCountCheck);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增订单信息失败");
        }
    }

    @Override
    public CustomResult updateAll(ProcessCountCheck processCountCheck) throws Exception {
        int i=processCountCheckMapper.updateByPrimaryKey(processCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(ProcessCountCheck processCountCheck) throws Exception {
        int i=processCountCheckMapper.updateByPrimaryKeySelective(processCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
