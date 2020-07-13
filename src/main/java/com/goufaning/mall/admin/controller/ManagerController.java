package com.goufaning.mall.admin.controller;

import com.goufaning.mall.bean.vo.ManagerStateVo;
import com.goufaning.mall.common.page.PageRequest;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/state")
    public CommonResult changeManagerState(@RequestBody ManagerStateVo managerStateVo) {
        Manager manager = managerService.getById(managerStateVo.getUserId());
        if (manager == null) {
            return CommonResult.error("无该用户");
        }
        manager.setState(managerStateVo.getState() == 1);
        boolean success = managerService.updateById(manager);
        if (!success) {
            return CommonResult.error("修改失败！请一会后再试试吧～");
        }
        return CommonResult.success("设置状态成功啦", manager);
    }
}
