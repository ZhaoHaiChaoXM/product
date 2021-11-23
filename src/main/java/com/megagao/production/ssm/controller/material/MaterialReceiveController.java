package com.megagao.production.ssm.controller.material;

import com.megagao.production.ssm.domain.Material;
import com.megagao.production.ssm.domain.MaterialReceive;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.MaterialReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/materialReceive")
public class MaterialReceiveController {
@Autowired
    MaterialReceiveService materialReceiveService;

    @RequestMapping("/find")
    public String getAll()throws Exception{
        return "materialReceive_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, MaterialReceive materialReceive) throws Exception{
        EUDataGridResult result = materialReceiveService.getList(page, rows, materialReceive);
        return result;
    }
//    @RequestMapping("/get_data")//暂时没用
//    @ResponseBody
//    public List<MaterialReceive> getData() throws Exception{
//        return materialReceiveService.find();
//    }
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "materialReceive_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid MaterialReceive materialReceive, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(materialReceiveService.get(materialReceive.getReceiveId()) != null){
            result = new CustomResult(0, "该物料收入编号已经存在，请更换编号！", null);
        }else{
            result = materialReceiveService.insert(materialReceive);
        }
        return result;
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = materialReceiveService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =materialReceiveService.deleteBatch(ids);
        return result;
    }
    //员工编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "materialReceive_edit";
    }
    //员工信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid MaterialReceive materialReceive, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return materialReceiveService.updateAll(materialReceive);
    }
    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid MaterialReceive materialReceive, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return materialReceiveService.updateNote(materialReceive);
    }
}
