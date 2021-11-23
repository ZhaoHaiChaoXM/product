package com.megagao.production.ssm.controller.device;


import com.megagao.production.ssm.domain.Device;
import com.megagao.production.ssm.domain.DeviceCheck;
import com.megagao.production.ssm.domain.DeviceFault;
import com.megagao.production.ssm.domain.DeviceType;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.DeviceFaultVO;
import com.megagao.production.ssm.service.DeviceFaultService;
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
@RequestMapping("/deviceFault")
public class DeviceFaultController {
    @Autowired
    DeviceFaultService deviceFaultService;//deviceFaultId

    @RequestMapping("/get/{deviceFaultId}")
    @ResponseBody
    public DeviceFault getItemById(@PathVariable String deviceFaultId) throws Exception {
        DeviceFault deviceFault = deviceFaultService.get(deviceFaultId);
        return deviceFault;
    }
    @RequestMapping("/deviceFault")
    public String getAll(){
        return "deviceFault";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, DeviceFault deviceFault) throws Exception{
        EUDataGridResult result = deviceFaultService.getList(page, rows, deviceFault);
        return result;
    }
//这是我新加的一段话



    
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "deviceFault_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid DeviceFault deviceFault, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(deviceFaultService.get(deviceFault.getDeviceFaultId()) != null){
            result = new CustomResult(0, "该部门编号已经存在，请更换部门编号！", null);
        }else{
            result = deviceFaultService.insert(deviceFault);
        }
        return result;
    }
    //删除部分
    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = deviceFaultService.deleteBatch(ids);
        return result;
    }
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = deviceFaultService.delete(id);
        return result;
    }
    //编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "deviceFault_edit";
    }
    //信息编辑，相当于信息更新
    @RequestMapping(value="/update")
    @ResponseBody
    private CustomResult updateAll(@Valid DeviceFault deviceFault, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceFaultService.updateAll(deviceFault);
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid DeviceFault deviceFault, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return
        deviceFaultService.updateNote( deviceFault);
    }
}
