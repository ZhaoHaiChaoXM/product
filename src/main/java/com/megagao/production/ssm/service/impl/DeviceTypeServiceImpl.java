package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Device;
import com.megagao.production.ssm.domain.DeviceType;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.DeviceTypeMapper;
import com.megagao.production.ssm.service.DeviceService;
import com.megagao.production.ssm.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.DeclaredType;
import java.util.List;
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    DeviceTypeMapper deviceTypeMapper;
   public EUDataGridResult getList(int page, int rows, DeviceType deviceType) throws Exception{
        //分页处理
        PageHelper.startPage(page, rows);

        List<DeviceType> list =deviceTypeMapper.find(deviceType);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public DeviceType get(String string) throws Exception {
        return deviceTypeMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult insert(DeviceType deviceType) throws Exception {
        int i = deviceTypeMapper.insert(deviceType);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增部门信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] deviceTypeIds) throws Exception {
        int i=deviceTypeMapper.deleteBatch(deviceTypeIds);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult delete(String deviceTypeId) throws Exception {
        int i=deviceTypeMapper.deleteByPrimaryKey(deviceTypeId);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateAll(DeviceType deviceType) throws Exception {
        int i=deviceTypeMapper.updateByPrimaryKey(deviceType);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(DeviceType deviceType) throws Exception {
        int i=deviceTypeMapper.updateByPrimaryKeySelective(deviceType);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
