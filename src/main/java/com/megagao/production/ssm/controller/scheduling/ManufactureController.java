package com.megagao.production.ssm.controller.scheduling;

import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.domain.Manufacture;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.domain.vo.ManufactureVO;
import com.megagao.production.ssm.domain.vo.WorkVO;
import com.megagao.production.ssm.service.ManufactureService;
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
@RequestMapping("manufacture")
public class ManufactureController {//生产计划管理
    @Autowired
    ManufactureService manufactureService;//manufactureSn

    @RequestMapping("/get/{manufactureSn}")
    @ResponseBody
    public ManufactureVO getItemById(@PathVariable String manufactureSn) throws Exception {
        ManufactureVO manufacture= manufactureService.get(manufactureSn);
        return manufacture;
    }

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "manufacture_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) throws Exception {
        EUDataGridResult result = manufactureService.getList(page, rows);
        return result;
    }
    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Manufacture> getData() throws Exception{
        return manufactureService.find();
    }
    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "manufacture_add";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(manufactureService.get(manufacture.getManufactureSn()) != null){
            result=new CustomResult(0, "该顾客编号已经存在，请更换顾客编号！", null);
        }else{
            result=manufactureService.insert(manufacture);
        }
        return result;
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = manufactureService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = manufactureService.deleteBatch(ids);
        return result;
    }
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "manufacture_edit";
    }
    @RequestMapping(value="/update_all")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return manufactureService.updateAll(manufacture);
    }
}
