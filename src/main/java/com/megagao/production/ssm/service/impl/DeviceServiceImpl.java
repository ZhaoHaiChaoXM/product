package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.Device;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.DeviceMapper;
import com.megagao.production.ssm.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public EUDataGridResult getList(int page, int rows, Device device) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);

        List<Device> list = deviceMapper.find(device);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Device> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public List<Device> find() throws Exception {
        return null;
    }

    @Override
    public Device get(String string) throws Exception {
        return deviceMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult insert(Device device) throws Exception {
        int i = deviceMapper.insert(device);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增顾客信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] deviceIds) throws Exception {
        int i=deviceMapper.deleteBatch(deviceIds);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult delete(String deviceId) throws Exception {
        int i=deviceMapper.deleteByPrimaryKey(deviceId);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(Device device) throws Exception {
        int i=deviceMapper.updateByPrimaryKeySelective(device);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateAll(Device device) throws Exception {
        int i=deviceMapper.updateByPrimaryKey(device);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
