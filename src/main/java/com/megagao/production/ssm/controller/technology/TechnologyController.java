package com.megagao.production.ssm.controller.technology;

import com.megagao.production.ssm.domain.Process;
import com.megagao.production.ssm.domain.Technology;
import com.megagao.production.ssm.domain.TechnologyRequirement;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.service.TechnologyService;
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
@RequestMapping("technology")
public class TechnologyController {//工艺管理
    @Autowired
    TechnologyService technologyService;

    @RequestMapping("/find")
    public String getAll() throws Exception {
        return "technology_list";
    }
    @RequestMapping("/list")//显示数据
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Technology technology) throws Exception {
        EUDataGridResult result =technologyService.getList(page, rows,technology);
        return result;
    }

    @RequestMapping("/get/{technologyId}")
    @ResponseBody
    public Technology getItemById(@PathVariable String technologyId) throws Exception{

        Technology technology=technologyService.get(technologyId);
        return technology;
    }

    @RequestMapping("/get_data")//暂时没用
    @ResponseBody
    public List<Technology> getData() throws Exception{
        return technologyService.find();
    }

    //编辑
    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "technology_edit";
    }
    //信息编辑，相当于信息更新
    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Technology technology, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return technologyService.updateAll(technology);
    }
    //单个删除和多个删除
    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = technologyService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = technologyService.deleteBatch(ids);
        return result;
    }

    @RequestMapping("/add")//跳到增加数据页面
    public String add() throws Exception{
        return "technology_add";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)//新增部门信息
    @ResponseBody
    private CustomResult insert(@Valid Technology technology, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(technologyService.get(technology.getTechnologyId()) != null){
            result = new CustomResult(0, "该工艺编号已经存在，请更换工艺编号！", null);
        }else{
            result = technologyService.insert(technology);
        }
        return result;
    }
}
