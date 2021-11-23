package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.ProcessMeasureCheck;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.ProcessMeasureCheckMapper;
import com.megagao.production.ssm.service.PMeasureCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PMeasureCheckServiceImpl implements PMeasureCheckService {
    @Autowired
    ProcessMeasureCheckMapper processMeasureCheckMapper;
    @Override
    public EUDataGridResult getList(int page, int rows, ProcessMeasureCheck processMeasureCheck) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<ProcessMeasureCheck> list = processMeasureCheckMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<ProcessMeasureCheck> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=processMeasureCheckMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(ProcessMeasureCheck processMeasureCheck) throws Exception {
        int i = processMeasureCheckMapper.insert(processMeasureCheck);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增信息失败");
        }
    }

    @Override
    public ProcessMeasureCheck get(String string) throws Exception {
        return processMeasureCheckMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=processMeasureCheckMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateAll(ProcessMeasureCheck processMeasureCheck) throws Exception {
        int i=processMeasureCheckMapper.updateByPrimaryKey(processMeasureCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(ProcessMeasureCheck processMeasureCheck) throws Exception {
        int i=processMeasureCheckMapper.updateByPrimaryKeySelective(processMeasureCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
