package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.COrderExample;
import com.megagao.production.ssm.domain.Technology;
import com.megagao.production.ssm.domain.TechnologyExample;
import com.megagao.production.ssm.domain.TechnologyRequirement;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.TechnologyMapper;
import com.megagao.production.ssm.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    @Autowired
    TechnologyMapper technologyMapper;

    @Override
    public List<Technology> find() throws Exception {
        TechnologyExample example = new TechnologyExample();
        return technologyMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(int page, int rows, Technology technology) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);

        List<Technology> list = technologyMapper.find(technology);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Technology> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Technology get(String string) throws Exception {
        return technologyMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=technologyMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=technologyMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(Technology technology) throws Exception {
        int i = technologyMapper.insert(technology);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增顾客信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Technology technology) throws Exception {
        int i=technologyMapper.updateByPrimaryKey(technology);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
