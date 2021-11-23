package com.megagao.production.ssm.controller.scheduling;

import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.Product;
import com.megagao.production.ssm.domain.Task;
import com.megagao.production.ssm.domain.Work;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.TaskMapper;
import com.megagao.production.ssm.service.TaskService;
import com.megagao.production.ssm.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("task")
public class TaskController {//生产 派工管理
    @Autowired
    TaskService taskService;

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "task_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Task task) throws Exception {
        EUDataGridResult result = taskService.getList(page, rows,task);
        return result;
    }
    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "task_add";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid Task task, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(taskService.get(task.getTaskId()) != null){
            result = new CustomResult(0, "该订单编号已经存在，请更换订单编号！", null);
        }else{
            result = taskService.insert(task);
        }
        return result;
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "task_edit";
    }
    @RequestMapping(value="/update_all")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid Task task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return taskService.updateAll(task);
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = taskService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = taskService.deleteBatch(ids);
        return result;
    }
}
