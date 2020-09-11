package com.goufaning.mall.wx.web;

import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Brand;
import com.goufaning.mall.db.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 专题服务
 *
 * @author goufn
 * @version V1.0
 * @date 2020/9/8 1:27 下午
 */
@RestController
@RequestMapping("/wx/brand")
@Validated
public class WxBrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 品牌列表
     *
     * @param page 分页页数
     * @param limit 分页大小
     * @return 品牌列表
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        List<Brand> brandList = brandService.query(page, limit, sort, order);
        return CommonResult.success(brandList);
    }

    /**
     * 品牌详情
     *
     * @param id 品牌ID
     * @return 品牌详情
     */
    @GetMapping("detail")
    public Object detail(@NotNull Integer id) {
        Brand entity = brandService.getById(id);
        if (entity == null) {
            return CommonResult.badArgumentValue();
        }
        return CommonResult.success(entity);
    }
}
