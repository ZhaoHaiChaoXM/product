package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Device;
import com.megagao.production.ssm.domain.DeviceCheck;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.DeviceCheckMapper;
import com.megagao.production.ssm.service.DeviceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceCheckServiceImpl implements DeviceCheckService {
    @Autowired
    DeviceCheckMapper deviceCheckMapper;

    public EUDataGridResult getList(int page, int rows, DeviceCheck deviceCheck) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);

        List<DeviceCheck> list = deviceCheckMapper.find(deviceCheck);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceCheck> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public DeviceCheck get(String string) throws Exception {
        return deviceCheckMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult insert(DeviceCheck deviceCheck) throws Exception {
        int i = deviceCheckMapper.insert(deviceCheck);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增顾客信息失败");
        }
    }

    @Override
    public CustomResult delete(String deviceCheckId) throws Exception {
        int i=deviceCheckMapper.deleteByPrimaryKey(deviceCheckId);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] deviceCheckIds) throws Exception {
        int i=deviceCheckMapper.deleteBatch(deviceCheckIds);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateAll(DeviceCheck deviceCheck) throws Exception {
        int i=deviceCheckMapper.updateByPrimaryKey(deviceCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(DeviceCheck deviceCheck) throws Exception {
        int i=deviceCheckMapper.updateByPrimaryKeySelective(deviceCheck);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
