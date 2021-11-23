package com.megagao.production.ssm.controller.employee;

import java.util.List;

import javax.validation.Valid;

import com.megagao.production.ssm.domain.Employee;
import com.megagao.production.ssm.domain.TechnologyRequirement;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.Department;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.EmployeeVO;
import com.megagao.production.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/department")
public class DepartmentController {//departmentId
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/get/{departmentId}")
    @ResponseBody
    public Department getItemById(@PathVariable String departmentId) throws Exception{

        Department department=departmentService.get(departmentId);
        return department;
    }
    @RequestMapping("/find")
    public String getAll()throws Exception{//进入部门管理页面
        return "department_list";
    }

    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Department> getData() throws Exception{
        return departmentService.find();
    }

    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Department department) throws Exception{
        EUDataGridResult result = departmentService.getList(page, rows, department);
        return result;
     }
    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "department_add";
    }

    @RequestMapping(value="/insert", method=RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid Department department, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(departmentService.get(department.getDepartmentId()) != null){
            result = new CustomResult(0, "该部门编号已经存在，请更换部门编号！", null);
        }else{
            result = departmentService.insert(department);
        }
        return result;
    }
//单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = departmentService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = departmentService.deleteBatch(ids);
        return result;
    }
    //员工编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "department_edit";
    }
   //员工信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Department department, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return departmentService.updateAll(department);
    }

    //按照部门编号查询
    //
    @RequestMapping("/search_department_by_departmentId")
    @ResponseBody
    public EUDataGridResult searchEmployeeByEmployeeId(Integer page, Integer rows, String searchValue)
            throws Exception{
        EUDataGridResult result = departmentService.searchDepartmentByDepartmentId(page,rows,searchValue);
        return result;
    }

    //按照部门名字查询
    @RequestMapping("/search_department_by_departmentName")
    @ResponseBody
    public EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String searchValue)
            throws Exception{
        EUDataGridResult result = departmentService.searchDepartmentByDepartmentName(page,rows,searchValue);
        return result;
    }
    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid Department department, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return departmentService.updateNote(department);
    }
}
