package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.*;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.TechnologyRequirementMapper;
import com.megagao.production.ssm.service.TechnologyRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TechnologyRequirementServiceImpl implements TechnologyRequirementService {
    @Autowired
    TechnologyRequirementMapper technologyRequirementMapper;

    @Override
    public List<TechnologyRequirement> find() throws Exception {
        TechnologyRequirementExample example = new TechnologyRequirementExample();
        return technologyRequirementMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(int page, int rows, TechnologyRequirement technologyRequirement) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);

        List<TechnologyRequirement> list = technologyRequirementMapper.find(technologyRequirement);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyRequirement> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TechnologyRequirement get(String string) throws Exception {
        return technologyRequirementMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=technologyRequirementMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=technologyRequirementMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(TechnologyRequirement technologyRequirement) throws Exception {
        int i = technologyRequirementMapper.insert(technologyRequirement);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增信息失败");
        }
    }

    @Override
    public CustomResult updateAll(TechnologyRequirement technologyRequirement) throws Exception {
        int i=technologyRequirementMapper.updateByPrimaryKey(technologyRequirement);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(TechnologyRequirement technologyRequirement) throws Exception {
        int i=technologyRequirementMapper.updateByPrimaryKeySelective(technologyRequirement);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
