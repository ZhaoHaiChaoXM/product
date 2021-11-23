package com.megagao.production.ssm.controller.device;


import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.domain.DeviceCheck;
import com.megagao.production.ssm.domain.DeviceType;
import com.megagao.production.ssm.domain.DeviceCheck;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.DeviceCheckService;
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
@RequestMapping("/deviceCheck")
public class DeviceCheckController {
    @Autowired
    DeviceCheckService deviceCheckService;//deviceCheckEmpId

    @RequestMapping("/get/{deviceCheckEmpId}")
    @ResponseBody
    public DeviceCheck getItemById(@PathVariable String deviceCheckEmpId) throws Exception {
        DeviceCheck deviceCheck = deviceCheckService.get(deviceCheckEmpId);
        return deviceCheck;
    }

    @RequestMapping("/deviceCheck")
    public String getAll()throws Exception{
        return "deviceCheck";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, DeviceCheck deviceCheck) throws Exception{
        EUDataGridResult result = deviceCheckService.getList(page, rows, deviceCheck);
        return result;
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "deviceCheck_edit";
    }
    @RequestMapping(value="/update")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid DeviceCheck deviceCheck, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceCheckService.updateAll(deviceCheck);
    }

//    @RequestMapping("/get_data")//暂时没用
//    @ResponseBody
//    public List<DeviceCheck> getData() throws Exception{
//        return deviceCheckService.find();
//    }

    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "deviceCheck_add";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private  CustomResult insert(@Valid DeviceCheck DeviceCheck, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(deviceCheckService.get(DeviceCheck.getDeviceCheckId()) != null){
            result=new CustomResult(0, "该编号已经存在，请更换编号！", null);
        }else{
            result=deviceCheckService.insert(DeviceCheck);
        }
        return result;
    }

    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = deviceCheckService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =deviceCheckService.deleteBatch(ids);
        return result;
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid DeviceCheck DeviceCheck, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceCheckService.updateNote(DeviceCheck);
    }
}
