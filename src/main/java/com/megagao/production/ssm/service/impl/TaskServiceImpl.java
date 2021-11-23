package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Product;
import com.megagao.production.ssm.domain.Task;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.TaskMapper;
import com.megagao.production.ssm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Override
    public EUDataGridResult getList(int page, int rows, Task task) throws Exception {
        PageHelper.startPage(page, rows);
        List<Task> list = taskMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Task> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Task get(String string) throws Exception {
        return taskMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=taskMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=taskMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(Task task) throws Exception {
            int i =taskMapper.insert(task);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增顾客信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Task task) throws Exception {
        int i=taskMapper.updateByPrimaryKey(task);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
