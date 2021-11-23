package com.megagao.production.ssm.controller.scheduling;

import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.domain.Product;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.domain.vo.WorkVO;
import com.megagao.production.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {//产品管理
    @Autowired
    ProductService productService;//productId
    @RequestMapping("/get/{productId}")
    @ResponseBody
    public Product getItemById(@PathVariable String productId) throws Exception {
        Product product = productService.get(productId);
        return product;
    }
    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "product_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Product product) throws Exception {
        EUDataGridResult result = productService.getList(page, rows,product);
        return result;
    }
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "product_edit";
    }
    @RequestMapping(value="/update_all")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid Product product, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return productService.updateAll(product);
    }

    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Product> getData() throws Exception{
        return productService.find();
    }

    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "product_add";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private  CustomResult insert(@Valid Product product, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(productService.get(product.getProductId()) != null){
            result=new CustomResult(0, "该产品编号已经存在，请更换产品编号！", null);
        }else{
            result=productService.insert(product);
        }
        return result;
    }

    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = productService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =productService.deleteBatch(ids);
        return result;
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid Product product, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return productService.updateNote(product);
    }
}
