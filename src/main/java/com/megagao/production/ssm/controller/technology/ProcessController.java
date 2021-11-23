package com.megagao.production.ssm.controller.technology;

import com.megagao.production.ssm.domain.*;
import com.megagao.production.ssm.domain.Process;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.FCountCheckService;
import com.megagao.production.ssm.service.ProcessService;
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
@RequestMapping("process")
public class ProcessController {//工序管理
    @Autowired
    ProcessService processService;//processId,

    @RequestMapping("/get/{processId}")
    @ResponseBody
    public Process getItemById(@PathVariable String processId) throws Exception{

        Process process=processService.get(processId);
        return process;
    }

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "process_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) throws Exception {
        EUDataGridResult result = processService.getList(page, rows);
        return result;
    }
    //编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "process_edit";
    }
    //信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Process process, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return processService.updateAll(process);
    }
    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Process> getData() throws Exception{
        return processService.find();
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = processService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = processService.deleteBatch(ids);
        return result;
    }

    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "process_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid Process process, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(processService.get(process.getProcessId()) != null){
            result = new CustomResult(0, "该工艺编号已经存在，请更换工艺编号！", null);
        }else{
            result = processService.insert(process);
        }
        return result;
    }
}
