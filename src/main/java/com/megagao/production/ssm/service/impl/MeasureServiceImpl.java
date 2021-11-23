package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.FinalMeasuretCheck;
import com.megagao.production.ssm.domain.Material;
import com.megagao.production.ssm.domain.ProcessCountCheck;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.FinalCountCheckMapper;
import com.megagao.production.ssm.mapper.FinalMeasuretCheckMapper;
import com.megagao.production.ssm.mapper.ProcessCountCheckMapper;
import com.megagao.production.ssm.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeasureServiceImpl implements MeasureService {
   @Autowired
    FinalMeasuretCheckMapper finalMeasuretCheckMapper;


    public EUDataGridResult getList(Integer page, Integer rows, FinalMeasuretCheck
            finalMeasuretCheck) throws Exception{
        //分页处理
        PageHelper.startPage(page, rows);

        List<FinalMeasuretCheck> list = finalMeasuretCheckMapper.find(finalMeasuretCheck);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalMeasuretCheck> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public FinalMeasuretCheck get(String string) throws Exception {
        return finalMeasuretCheckMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=finalMeasuretCheckMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=finalMeasuretCheckMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(FinalMeasuretCheck finalMeasuretCheck) throws Exception {
        int i =finalMeasuretCheckMapper.insert(finalMeasuretCheck);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增信息失败");
        }
    }

    @Override
    public CustomResult updateAll(FinalMeasuretCheck finalMeasuretCheck) throws Exception {
        int i=finalMeasuretCheckMapper.updateByPrimaryKey(finalMeasuretCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(FinalMeasuretCheck finalMeasuretCheck) throws Exception {
        int i=finalMeasuretCheckMapper.updateByPrimaryKeySelective(finalMeasuretCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

}
