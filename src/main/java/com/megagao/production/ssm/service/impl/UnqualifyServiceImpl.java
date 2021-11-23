package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.MaterialConsumeVO;
import com.megagao.production.ssm.domain.vo.UnqualifyApplyVO;
import com.megagao.production.ssm.mapper.UnqualifyApplyMapper;
import com.megagao.production.ssm.service.UnqualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnqualifyServiceImpl implements UnqualifyService {
    @Autowired
    UnqualifyApplyMapper unqualifyApplyMapper;
    @Override
    public EUDataGridResult getList(Integer page, Integer rows, UnqualifyApply unqualifyApply) throws Exception {
        List<UnqualifyApply> list = unqualifyApplyMapper.find(unqualifyApply);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<UnqualifyApply> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public UnqualifyApply get(String string) throws Exception {
        return unqualifyApplyMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=unqualifyApplyMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=unqualifyApplyMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(UnqualifyApply unqualify) throws Exception {
        int i = unqualifyApplyMapper.insert(unqualify);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增信息失败");
        }
    }

    @Override
    public CustomResult updateAll(UnqualifyApply unqualify) throws Exception {
        int i=unqualifyApplyMapper.updateByPrimaryKey(unqualify);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(UnqualifyApply unqualify) throws Exception {
        int i=unqualifyApplyMapper.updateByPrimaryKeySelective(unqualify);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
