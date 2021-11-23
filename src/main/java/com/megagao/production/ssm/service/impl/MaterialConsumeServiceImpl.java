package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.MaterialConsume;
import com.megagao.production.ssm.domain.MaterialReceive;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.MaterialConsumeVO;
import com.megagao.production.ssm.mapper.MaterialConsumeMapper;
import com.megagao.production.ssm.service.MaterialConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialConsumeServiceImpl implements MaterialConsumeService {

    @Autowired
    MaterialConsumeMapper materialConsumeMapper;

    @Override
    public EUDataGridResult getList(int page, int rows, MaterialConsumeVO materialConsume) throws Exception {

        List<MaterialConsumeVO> list = materialConsumeMapper.find(materialConsume);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialConsumeVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public MaterialConsume get(String string) throws Exception {
        return materialConsumeMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=materialConsumeMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=materialConsumeMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(MaterialConsume materialConsume) throws Exception {
        int i = materialConsumeMapper.insert(materialConsume);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增顾客信息失败");
        }
    }

    @Override
    public CustomResult updateAll(MaterialConsume materialConsume) throws Exception {
        int i=materialConsumeMapper.updateByPrimaryKey(materialConsume);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(MaterialConsume materialConsume) throws Exception {
        int i=materialConsumeMapper.updateByPrimaryKeySelective(materialConsume);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
