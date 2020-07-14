package com.goufaning.mall.admin.controller;

import com.goufaning.mall.bean.vo.ManagerInfoVo;
import com.goufaning.mall.bean.vo.ManagerVo;
import com.goufaning.mall.common.page.PageRequest;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-13 17:50
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/list")
    public CommonResult getPageByName(@RequestBody PageRequest pageRequest) {
        String name = pageRequest.getParamValue("name");
        return CommonResult.success(managerService.getPageByName(name, pageRequest.getPageNum(), pageRequest.getPageSize()));
    }

    @GetMapping("id")
    public CommonResult getInfoById(@RequestParam int id) {
        Manager manager = managerService.getById(id);
        if (manager == null) {
            return CommonResult.error("没有该用户");
        }
        ManagerInfoVo managerInfoVo = new ManagerInfoVo(manager);
        return CommonResult.success(managerInfoVo);
    }

    @PostMapping("/state")
    public CommonResult changeManagerState(@RequestBody ManagerVo managerVo) {
        Manager manager = managerService.getById(managerVo.getUserId());
        if (manager == null) {
            return CommonResult.error("无该用户");
        }
        manager.setState(managerVo.getState());
        boolean success = managerService.updateById(manager);
        if (!success) {
            return CommonResult.error("修改失败！请一会后再试试吧～");
        }
        return CommonResult.success("设置状态成功啦");
    }

    @PostMapping("/add")
    public CommonResult addNewManager(@RequestBody ManagerVo managerVo) {
        Manager manager = new Manager(managerVo);
        manager.setState(0);
        manager.setCreateTime(new Date());
        boolean success = manager.insert();
        if (!success) {
            return CommonResult.error("添加失败！请一会后再试试吧～");
        }
        ManagerInfoVo managerInfoVo = new ManagerInfoVo(manager);
        return CommonResult.success("添加用户成功", managerInfoVo);
    }

    @PostMapping("/update")
    public CommonResult updateManager(@RequestBody ManagerVo managerVo) {
        Manager manager = managerService.getById(managerVo.getUserId());
        if (manager == null) {
            return CommonResult.error("无该用户");
        }
        manager.setEmail(managerVo.getEmail());
        manager.setMobile(managerVo.getMobile());
        boolean success = manager.updateById();
        if (!success) {
            return CommonResult.error("修改失败！请一会后再试试吧～");
        }
        ManagerInfoVo managerInfoVo = new ManagerInfoVo(manager);
        return CommonResult.success("修改成功", managerInfoVo);
    }

    @GetMapping("/delete")
    public CommonResult deleteManager(@RequestParam int id) {
        Manager manager = managerService.getById(id);
        if (manager == null) {
            return CommonResult.error("无该用户");
        }
        boolean success = manager.deleteById();
        if (!success) {
            return CommonResult.error("删除失败！请一会后再试试吧～");
        }
        return CommonResult.success("删除成功");
    }


}
