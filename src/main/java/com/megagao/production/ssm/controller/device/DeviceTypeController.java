package com.megagao.production.ssm.controller.device;

import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.Device;
import com.megagao.production.ssm.domain.DeviceType;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.WorkVO;
import com.megagao.production.ssm.service.DeviceTypeService;
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
@RequestMapping("/deviceType")
public class DeviceTypeController {
    @Autowired
    DeviceTypeService deviceTypeService;//deviceTypeId

    @RequestMapping("/get/{deviceTypeId}")
    @ResponseBody
    public DeviceType getItemById(@PathVariable String deviceTypeId) throws Exception {
        DeviceType deviceType = deviceTypeService.get(deviceTypeId);
        return deviceType;
    }

    @RequestMapping("/deviceType")
    public String getAll()throws Exception{//进入部门管理页面
        return "deviceType";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, DeviceType deviceType) throws Exception{
        EUDataGridResult result = deviceTypeService.getList(page, rows, deviceType);
        return result;
    }
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "deviceType_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(deviceTypeService.get(deviceType.getDeviceTypeId()) != null){
            result = new CustomResult(0, "该部门编号已经存在，请更换部门编号！", null);
        }else{
            result = deviceTypeService.insert(deviceType);
        }
        return result;
    }
    //删除部分
    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = deviceTypeService.deleteBatch(ids);
        return result;
    }
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = deviceTypeService.delete(id);
        return result;
    }
    //编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "deviceType_edit";
    }
    //信息编辑，相当于信息更新
    @RequestMapping(value="/update")
    @ResponseBody
    private CustomResult updateAll(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceTypeService.updateAll(deviceType);
    }
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll1(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceTypeService.updateAll(deviceType);
    }
}
