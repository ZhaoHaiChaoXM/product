package com.megagao.production.ssm.controller.material;

import com.megagao.production.ssm.domain.*;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.EmployeeVO;
import com.megagao.production.ssm.service.MaterialService;
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
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    MaterialService materialService;//

    @RequestMapping("/get/{materialId}")
    @ResponseBody
    public Material getItemById(@PathVariable String materialId) throws Exception {
        Material material = materialService.get(materialId);
        return material;
    }

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "material_list";
    }

    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Material material) throws Exception {
        EUDataGridResult result = materialService.getList(page, rows, material);
        return result;
    }

    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Material> getData() throws Exception {
        return materialService.find();
    }

    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception {
        return "material_add";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid Material material, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if (materialService.get(material.getMaterialId()) != null) {
            result = new CustomResult(0, "该物料编号已经存在，请更换编号！", null);
        } else {
            result = materialService.insert(material);
        }
        return result;
    }

    //单个删除和多个删除
    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = materialService.delete(id);
        return result;
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = materialService.deleteBatch(ids);
        return result;
    }

    //员工编辑
    @RequestMapping("/edit")
    public String edit() throws Exception {
        return "material_edit";
    }

    //员工信息编辑，相当于信息更新
    @RequestMapping(value = "/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Material material, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return materialService.updateAll(material);
    }

    @RequestMapping(value = "/update_note")
    @ResponseBody
    private CustomResult updatenote(@Valid Material material, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return materialService.updateNote(material);
    }
}
