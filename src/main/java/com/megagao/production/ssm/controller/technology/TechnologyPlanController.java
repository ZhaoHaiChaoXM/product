package com.megagao.production.ssm.controller.technology;

import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.Process;
import com.megagao.production.ssm.domain.TechnologyPlan;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.TechnologyPlanService;
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
@RequestMapping("technologyPlan")
public class TechnologyPlanController {//工艺计划控制器
    @Autowired
    TechnologyPlanService technologyPlanService;

    @RequestMapping("/get/{technologyPlanId}")
    @ResponseBody
    public TechnologyPlan getItemById(@PathVariable String technologyPlanId) throws Exception{

        TechnologyPlan technologyPlan =technologyPlanService.get(technologyPlanId);
        return technologyPlan;
    }
    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "technologyPlan_list";
    }

    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, TechnologyPlan technologyPlan) throws Exception {
        EUDataGridResult result = technologyPlanService.getList(page, rows, technologyPlan);
        return result;
    }
    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<TechnologyPlan> getData() throws Exception{
        return technologyPlanService.find();
    }
    //编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "technologyPlan_edit";
    }
    //信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid TechnologyPlan technologyPlan, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return technologyPlanService.updateAll(technologyPlan);
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result =technologyPlanService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = technologyPlanService.deleteBatch(ids);
        return result;
    }

    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "technologyPlan_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid TechnologyPlan technologyPlan, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(technologyPlanService.get(technologyPlan.getTechnologyPlanId()) != null){
            result = new CustomResult(0, "该计划编号已经存在，请更换工艺计划编号！", null);
        }else{
            result = technologyPlanService.insert(technologyPlan);
        }
        return result;
    }
}
