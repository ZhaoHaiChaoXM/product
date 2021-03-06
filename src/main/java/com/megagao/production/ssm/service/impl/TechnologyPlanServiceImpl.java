package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.DepartmentExample;
import com.megagao.production.ssm.domain.TechnologyPlan;
import com.megagao.production.ssm.domain.TechnologyPlanExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.TechnologyPlanMapper;
import com.megagao.production.ssm.service.TechnologyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TechnologyPlanServiceImpl implements TechnologyPlanService {
    @Autowired
    TechnologyPlanMapper technologyPlanMapper;

    @Override
    public List<TechnologyPlan> find() throws Exception {
       TechnologyPlanExample example = new TechnologyPlanExample();
        return technologyPlanMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(int page, int rows, TechnologyPlan technologyPlan) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);

        List<TechnologyPlan> list = technologyPlanMapper.find(technologyPlan);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyPlan> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TechnologyPlan get(String string) throws Exception {
        return technologyPlanMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=technologyPlanMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=technologyPlanMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(TechnologyPlan technologyPlan) throws Exception {
        int i = technologyPlanMapper.insert(technologyPlan);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增部门信息失败");
        }
    }

    @Override
    public CustomResult updateAll(TechnologyPlan technologyPlan) throws Exception {
        int i=technologyPlanMapper.updateByPrimaryKey(technologyPlan);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
