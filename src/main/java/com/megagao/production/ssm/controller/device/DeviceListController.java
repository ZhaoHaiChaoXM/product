package com.megagao.production.ssm.controller.device;


import com.megagao.production.ssm.domain.*;
import com.megagao.production.ssm.domain. Device;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.DeviceVO;
import com.megagao.production.ssm.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/deviceList")
public class DeviceListController {
     @Autowired
     DeviceService deviceService;//deviceId

    @RequestMapping("/get/{deviceId}")
    @ResponseBody
    public Device getItemById(@PathVariable String deviceId) throws Exception {
        Device device = deviceService.get(deviceId);
        return device;
    }


    @RequestMapping("/deviceList")
    public String getAll()throws Exception{//进入部门管理页面
        return "deviceList";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Device device) throws Exception{
        EUDataGridResult result = deviceService.getList(page, rows, device);
        return result;
    }
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "deviceList_edit";
    }
    @RequestMapping(value="/update")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid  Device  Device, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceService.updateAll( Device);
    }

//    @RequestMapping("/get_data")//暂时没用
//    @ResponseBody
//    public List< Device> getData() throws Exception{
//        return deviceService.find();
//    }

    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "deviceList_add";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private  CustomResult insert(@Valid  Device  Device, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(deviceService.get( Device.getDeviceId()) != null){
            result=new CustomResult(0, "该编号已经存在，请更换编号！", null);
        }else{
            result=deviceService.insert( Device);
        }
        return result;
    }

    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = deviceService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =deviceService.deleteBatch(ids);
        return result;
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid  Device  Device, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceService.updateNote( Device);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll1(@Valid Device device, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceService.updateAll(device);
    }
}
