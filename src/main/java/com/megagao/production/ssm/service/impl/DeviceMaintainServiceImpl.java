package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.DeviceCheck;
import com.megagao.production.ssm.domain.DeviceFault;
import com.megagao.production.ssm.domain.DeviceMaintain;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.DeviceMaintainMapper;
import com.megagao.production.ssm.service.DeviceMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceMaintainServiceImpl implements DeviceMaintainService {
    @Autowired
    DeviceMaintainMapper deviceMaintainMapper;


    public EUDataGridResult getList(int page, int rows, DeviceMaintain deviceMaintain) throws Exception {
//分页处理
        PageHelper.startPage(page, rows);

        List<DeviceMaintain> list = deviceMaintainMapper.find(deviceMaintain);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public DeviceMaintain get(String string) throws Exception {
        return deviceMaintainMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult insert(DeviceMaintain deviceMaintain) throws Exception {
        int i = deviceMaintainMapper.insert(deviceMaintain);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增顾客信息失败");
        }
    }

    @Override
    public CustomResult delete(String deviceMaintainId) throws Exception {
        int i=deviceMaintainMapper.deleteByPrimaryKey(deviceMaintainId);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] deviceMaintainIds) throws Exception {
        int i=deviceMaintainMapper.deleteBatch(deviceMaintainIds);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateAll(DeviceMaintain deviceMaintain) throws Exception {
        int i=deviceMaintainMapper.updateByPrimaryKey(deviceMaintain);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(DeviceMaintain deviceMaintain) throws Exception {
        int i=deviceMaintainMapper.updateByPrimaryKeySelective(deviceMaintain);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
