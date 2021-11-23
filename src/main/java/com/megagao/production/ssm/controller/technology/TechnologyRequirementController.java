package com.megagao.production.ssm.controller.technology;

import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.Technology;
import com.megagao.production.ssm.domain.TechnologyRequirement;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.EmployeeVO;
import com.megagao.production.ssm.service.TechnologyRequirementService;
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
@RequestMapping("technologyRequirement")
public class TechnologyRequirementController {////工艺要求
    @Autowired
    TechnologyRequirementService technologyRequirementService;

    @RequestMapping("/get/{technologyId}")
    @ResponseBody
    public TechnologyRequirement getItemById(@PathVariable String technologyId) throws Exception{

        TechnologyRequirement technologyRequirement=technologyRequirementService.get(technologyId);
        return technologyRequirement;
    }

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "technologyRequirement_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, TechnologyRequirement technologyRequirement) throws Exception {
        EUDataGridResult result = technologyRequirementService.getList(page, rows,technologyRequirement);
        return result;
    }//
    @RequestMapping(value="/update_requirement")
    @ResponseBody
    private CustomResult updatenote(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return technologyRequirementService.updateNote(technologyRequirement);
    }

    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<TechnologyRequirement> getData() throws Exception{
        return technologyRequirementService.find();
    }
    //编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "technologyRequirement_edit";
    }
    //信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return technologyRequirementService.updateAll(technologyRequirement);
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result =technologyRequirementService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = technologyRequirementService.deleteBatch(ids);
        return result;
    }

    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "technologyRequirement_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(technologyRequirementService.get(technologyRequirement.getTechnologyRequirementId()) != null){
            result = new CustomResult(0, "该工艺要求编号已经存在，请更换工艺要求编号！", null);
        }else{
            result = technologyRequirementService.insert(technologyRequirement);
        }
        return result;
    }
}
