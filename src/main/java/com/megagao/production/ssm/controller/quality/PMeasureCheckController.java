package com.megagao.production.ssm.controller.quality;

import com.megagao.production.ssm.domain.FinalMeasuretCheck;
import com.megagao.production.ssm.domain.ProcessCountCheck;
import com.megagao.production.ssm.domain.ProcessMeasureCheck;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.PMeasureCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("p_measure_check")
public class PMeasureCheckController {//工序计量质检

    @Autowired
    PMeasureCheckService pMeasureCheckService;
    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "p_measure_check_list";
    }

    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, ProcessMeasureCheck processMeasureCheck) throws Exception {
        EUDataGridResult result = pMeasureCheckService.getList(page, rows, processMeasureCheck);
        return result;
    }
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "p_measure_check_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid ProcessMeasureCheck processMeasureCheck, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(pMeasureCheckService.get(processMeasureCheck.getpMeasureCheckId()) != null){
            result = new CustomResult(0, "该不合格品编号已经存在，请更换编号！", null);
        }else{
            result = pMeasureCheckService.insert(processMeasureCheck);
        }
        return result;
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = pMeasureCheckService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =pMeasureCheckService.deleteBatch(ids);
        return result;
    }
    //员工编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "p_measure_check_edit";
    }
    //员工信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid ProcessMeasureCheck processMeasureCheck, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return pMeasureCheckService.updateAll(processMeasureCheck);
    }
    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid ProcessMeasureCheck processMeasureCheck, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return pMeasureCheckService.updateNote(processMeasureCheck);
    }
}
