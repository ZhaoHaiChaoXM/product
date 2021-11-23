package com.megagao.production.ssm.controller.device;

import com.megagao.production.ssm.domain.Device;
import com.megagao.production.ssm.domain. DeviceMaintain;
import com.megagao.production.ssm.domain.DeviceMaintain;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.DeviceMaintainService;
import com.megagao.production.ssm.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/deviceMaintain")
public class DeviceMaintainController {

    @Autowired
    DeviceMaintainService deviceMaintainService;


    @RequestMapping("/deviceMaintain")
    public String getAll()throws Exception{//进入部门管理页面
        return "deviceMaintain";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, DeviceMaintain deviceMaintain) throws Exception{
        EUDataGridResult result = deviceMaintainService.getList(page, rows, deviceMaintain);
        return result;
    }
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "deviceMaintain_edit";
    }
    @RequestMapping(value="/update")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid  DeviceMaintain  DeviceMaintain, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceMaintainService.updateAll( DeviceMaintain);
    }

//    @RequestMapping("/get_data")//暂时没用
//    @ResponseBody
//    public List< DeviceMaintain> getData() throws Exception{
//        return deviceMaintainService.find();
//    }

    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "deviceMaintain_add";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private  CustomResult insert(@Valid  DeviceMaintain  DeviceMaintain, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(deviceMaintainService.get( DeviceMaintain.getDeviceMaintainId()) != null){
            result=new CustomResult(0, "该编号已经存在，请更换编号！", null);
        }else{
            result=deviceMaintainService.insert( DeviceMaintain);
        }
        return result;
    }

    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = deviceMaintainService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result =deviceMaintainService.deleteBatch(ids);
        return result;
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid  DeviceMaintain  DeviceMaintain, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceMaintainService.updateNote( DeviceMaintain);
    }
}
