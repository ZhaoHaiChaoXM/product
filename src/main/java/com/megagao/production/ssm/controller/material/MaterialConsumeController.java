package com.megagao.production.ssm.controller.material;

import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.MaterialConsume;
import com.megagao.production.ssm.domain.MaterialReceive;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.MaterialConsumeVO;
import com.megagao.production.ssm.service.MaterialConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/materialConsume")
public class MaterialConsumeController {

    @Autowired
    MaterialConsumeService materialConsumeService;
    @RequestMapping("/find")
    public String getAll()throws Exception{
        return "materialConsume_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, MaterialConsumeVO materialConsume) throws Exception{
        EUDataGridResult result = materialConsumeService.getList(page, rows, materialConsume);
        return result;
    }
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "materialConsume_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid MaterialConsume materialConsume, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(materialConsumeService.get(materialConsume.getConsumeId()) != null){
            result = new CustomResult(0, "该物料消耗编号已经存在，请更换编号！", null);
        }else{
            result = materialConsumeService.insert(materialConsume);
        }
        return result;
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = materialConsumeService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =materialConsumeService.deleteBatch(ids);
        return result;
    }
    //员工编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "materialConsume_edit";
    }
    //员工信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid MaterialConsume materialConsume, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return materialConsumeService.updateAll(materialConsume);
    }
    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid MaterialConsume materialConsume, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return materialConsumeService.updateNote(materialConsume);
    }
}
