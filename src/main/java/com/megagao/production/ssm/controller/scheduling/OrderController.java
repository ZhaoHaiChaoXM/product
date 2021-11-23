package com.megagao.production.ssm.controller.scheduling;

import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.domain.vo.EmployeeVO;
import com.megagao.production.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
@RequestMapping("/order")
public class OrderController {//订单管理
    @Autowired
    OrderService orderService;//orderId

    @RequestMapping("/get/{orderId}")
    @ResponseBody
    public COrderVO getItemById(@PathVariable String orderId) throws Exception{
        COrderVO cOrderVO = orderService.get(orderId);
        return cOrderVO;
    }

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "order_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, COrderVO cOrderVO) throws Exception {
        EUDataGridResult result = orderService.getList(page, rows,cOrderVO);
        return result;
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "order_edit";
    }
    @RequestMapping(value="/update_all")//信息更新模块
    @ResponseBody
    private CustomResult updateAll(@Valid COrder cOrder, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return orderService.updateAll(cOrder);
    }
    //信息增添模块
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "order_add";
    }

    @RequestMapping("/get")//暂时没用
    @ResponseBody
    public List<COrderVO> getData() throws Exception{
        return orderService.find();
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid COrder cOrder, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(orderService.get(cOrder.getOrderId()) != null){
            result = new CustomResult(0, "该订单编号已经存在，请更换订单编号！", null);
        }else{
            result = orderService.insert(cOrder);
        }
        return result;
    }

    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = orderService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = orderService.deleteBatch(ids);
        return result;
    }
    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid  COrder order, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return orderService.updateNote(order);
    }
}
