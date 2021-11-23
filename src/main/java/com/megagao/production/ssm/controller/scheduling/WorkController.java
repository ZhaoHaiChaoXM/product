package com.megagao.production.ssm.controller.scheduling;

import com.megagao.production.ssm.domain.*;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.WorkVO;
import com.megagao.production.ssm.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("work")
public class WorkController {//作业管理
    @Autowired
    WorkService workService;

    @RequestMapping("/get/{workId}")
    @ResponseBody
    public WorkVO getItemById(@PathVariable String workId) throws Exception {
      WorkVO work = workService.get(workId);
        return work;
    }

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "work_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Work work) throws Exception {
        EUDataGridResult result = workService.getList(page, rows,work);
        return result;
    }
    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Work> getData() throws Exception{
        return workService.find();
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "work_edit";
    }
    @RequestMapping(value="/update_all")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid Work work, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return workService.updateAll(work);
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = workService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = workService.deleteBatch(ids);
        return result;
    }
}
