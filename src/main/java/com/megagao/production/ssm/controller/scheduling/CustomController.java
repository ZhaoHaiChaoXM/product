package com.megagao.production.ssm.controller.scheduling;

import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.domain.vo.WorkVO;
import com.megagao.production.ssm.service.CustomService;
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
@RequestMapping("custom")
public class CustomController {//客户管理
    @Autowired
    CustomService customService;//customId

    @RequestMapping("/get/{customId}")
    @ResponseBody
    public Custom getItemById(@PathVariable String customId) throws Exception {
        Custom custom = customService.get(customId);
        return custom;
    }
    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "custom_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Custom custom) throws Exception {
        EUDataGridResult result = customService.getList(page, rows,custom);
        return result;

    }
    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Custom> getData() throws Exception{
        return customService.find();
    }


    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "custom_edit";
    }
    @RequestMapping(value="/update_all")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid Custom custom, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return customService.updateAll(custom);
    }

    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "custom_add";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private  CustomResult insert(@Valid Custom custom, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(customService.get(custom.getCustomId()) != null){
            result=new CustomResult(0, "该顾客编号已经存在，请更换顾客编号！", null);
        }else{
            result=customService.insert(custom);
        }
        return result;
    }

    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = customService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = customService.deleteBatch(ids);
        return result;
    }
    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid Custom custom, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return customService.updateNote(custom);
    }
}
