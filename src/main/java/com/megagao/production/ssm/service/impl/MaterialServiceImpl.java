package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.CustomExample;
import com.megagao.production.ssm.domain.Device;
import com.megagao.production.ssm.domain.Material;
import com.megagao.production.ssm.domain.MaterialExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.MaterialMapper;
import com.megagao.production.ssm.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialMapper materialMapper;

    @Override
    public List<Material> find() throws Exception {
       MaterialExample example=new MaterialExample();
        return materialMapper.selectByExample(example);
    }

    public EUDataGridResult getList(int page, int rows, Material material) throws Exception{
        //分页处理
        PageHelper.startPage(page, rows);

        List<Material> list = materialMapper.find(material);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Material> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Material get(String string) throws Exception {
        return materialMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=materialMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=materialMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(Material material) throws Exception {
        int i = materialMapper.insert(material);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Material material) throws Exception {
        int i=materialMapper.updateByPrimaryKey(material);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(Material material) throws Exception {
        int i=materialMapper.updateByPrimaryKeySelective(material);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
