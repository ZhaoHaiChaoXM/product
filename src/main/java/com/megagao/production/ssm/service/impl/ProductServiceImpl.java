package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.*;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.ProductMapper;
import com.megagao.production.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public EUDataGridResult getList(int page, int rows, Product product) throws Exception {
        PageHelper.startPage(page, rows);
        List<Product> list = productMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Product get(String string) throws Exception {
        return productMapper.selectByPrimaryKey(string);
    }

    @Override
    public CustomResult delete(String string) throws Exception {
        int i=productMapper.deleteByPrimaryKey(string);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) throws Exception {
        int i=productMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult insert(Product product) throws Exception {
           int i = productMapper.insert(product);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增订单信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Product product) throws Exception {
        int i=productMapper.updateByPrimaryKey(product);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public CustomResult updateNote(Product product) throws Exception {
        int i=productMapper.updateByPrimaryKeySelective(product);
        if(i>0){
            return CustomResult.ok();
        }else {
            return null;
        }
    }

    @Override
    public List<Product> find() throws Exception {
        ProductExample example=new ProductExample();
        return productMapper.selectByExample(example);
    }
}
