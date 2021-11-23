package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.COrderExample;
import com.megagao.production.ssm.domain.DepartmentExample;
import com.megagao.production.ssm.domain.FinalMeasuretCheck;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.mapper.COrderMapper;
import com.megagao.production.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    COrderMapper cOrderMapper;

    @Override
    public List<COrderVO> find() throws Exception {
       COrderExample example = new COrderExample();
        return cOrderMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(int page, int rows, COrderVO cOrder) throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);

        List<COrderVO> list = cOrderMapper.find(cOrder);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<COrderVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public COrderVO get(String string) throws Exception {
        return cOrderMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=cOrderMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=cOrderMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(COrder cOrder) throws Exception {
        int i = cOrderMapper.insert(cOrder);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增订单信息失败");
        }
    }

    @Override
    public CustomResult updateAll(COrder cOrder) throws Exception {
        int i=cOrderMapper.updateByPrimaryKey(cOrder);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(COrder cOrder) throws Exception {
        int i=cOrderMapper.updateByPrimaryKeySelective(cOrder);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

}
