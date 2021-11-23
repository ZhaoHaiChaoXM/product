package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Material;
import com.megagao.production.ssm.domain.MaterialReceive;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.MaterialReceiveMapper;
import com.megagao.production.ssm.service.MaterialReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MaterialReceiveServiceImpl implements MaterialReceiveService {
   @Autowired
    MaterialReceiveMapper materialReceiveMapper;

    @Override
    public EUDataGridResult getList(int page, int rows, MaterialReceive materialReceive) throws Exception {
        PageHelper.startPage(page, rows);

        List<MaterialReceive> list = materialReceiveMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialReceive> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public MaterialReceive get(String string) throws Exception {
        return materialReceiveMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=materialReceiveMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=materialReceiveMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(MaterialReceive materialReceive) throws Exception {
        int i = materialReceiveMapper.insert(materialReceive);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增信息失败");
        }
    }

    @Override
    public CustomResult updateAll(MaterialReceive materialReceive) throws Exception {
        int i=materialReceiveMapper.updateByPrimaryKey(materialReceive);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(MaterialReceive materialReceive) throws Exception {
        int i=materialReceiveMapper.updateByPrimaryKeySelective(materialReceive);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
