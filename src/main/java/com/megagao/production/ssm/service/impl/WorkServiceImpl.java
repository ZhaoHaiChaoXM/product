package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.ManufactureExample;
import com.megagao.production.ssm.domain.Work;
import com.megagao.production.ssm.domain.WorkExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.domain.vo.WorkVO;
import com.megagao.production.ssm.mapper.WorkMapper;
import com.megagao.production.ssm.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;

    @Override
    public List<Work> find() throws Exception {
        WorkExample example=new WorkExample();
        return workMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(int page, int rows, Work work) throws Exception {
        PageHelper.startPage(page, rows);

        List<Work> list = workMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Work> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public WorkVO get(String workId) throws Exception {
        return workMapper.selectByPrimaryKey(workId);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=workMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=workMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateAll(Work work) throws Exception {
        int i=workMapper.updateByPrimaryKey(work);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

//    @Override
//    public Work get(String string) throws Exception {
//        return null;
//    }
}
