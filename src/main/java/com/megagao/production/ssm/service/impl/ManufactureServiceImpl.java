package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.CustomExample;
import com.megagao.production.ssm.domain.Manufacture;
import com.megagao.production.ssm.domain.ManufactureExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.ManufactureVO;
import com.megagao.production.ssm.domain.vo.MaterialConsumeVO;
import com.megagao.production.ssm.mapper.ManufactureMapper;
import com.megagao.production.ssm.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureServiceImpl implements ManufactureService {
    @Autowired
    ManufactureMapper manufactureMapper;

    @Override
    public List<Manufacture> find() throws Exception {
        ManufactureExample example=new ManufactureExample();
        return manufactureMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(int page, int rows) throws Exception {

        List<Manufacture> list = manufactureMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Manufacture> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public ManufactureVO get(String string) throws Exception {
        return manufactureMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=manufactureMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=manufactureMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(Manufacture manufacture) throws Exception {
        int i = manufactureMapper.insert(manufacture);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增订单信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Manufacture manufacture) throws Exception {
        int i=manufactureMapper.updateByPrimaryKey(manufacture);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }
}
