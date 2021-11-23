package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.COrderExample;
import com.megagao.production.ssm.domain.FinalMeasuretCheck;
import com.megagao.production.ssm.domain.Process;
import com.megagao.production.ssm.domain.ProcessExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.mapper.ProcessMapper;
import com.megagao.production.ssm.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    ProcessMapper processMapper;

    @Override
    public EUDataGridResult getList(int page, int rows) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);

        List<Process> list = processMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Process> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Process get(String string) throws Exception {
        return processMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=processMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=processMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(Process process) throws Exception {
        int i = processMapper.insert(process);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增部门信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Process process) throws Exception {
        int i=processMapper.updateByPrimaryKey(process);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
    @Override
    public List<Process> find() throws Exception {
        ProcessExample example = new ProcessExample();
        return processMapper.selectByExample(example);
    }
}
