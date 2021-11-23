package com.megagao.production.ssm.controller.quality;

import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.MaterialConsume;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.UnqualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/unqualify")
public class UnqualifyController {//不合格管理

    @Autowired
    UnqualifyService unqualifyService;

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "unqualify_list";
    }

    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, UnqualifyApply unqualifyApply) throws Exception {
        EUDataGridResult result = unqualifyService.getList(page, rows, unqualifyApply);
        return result;
    }
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "unqualify_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(unqualifyService.get(unqualifyApply.getUnqualifyApplyId()) != null){
            result = new CustomResult(0, "该不合格品编号已经存在，请更换编号！", null);
        }else{
            result = unqualifyService.insert(unqualifyApply);
        }
        return result;
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = unqualifyService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =unqualifyService.deleteBatch(ids);
        return result;
    }
    //员工编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "unqualify_edit";
    }
    //员工信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return unqualifyService.updateAll(unqualifyApply);
    }
    //员工信息编辑，相当于信息更新
    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return unqualifyService.updateNote(unqualifyApply);
    }
}
