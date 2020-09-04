package com.goufaning.mall.wx.web;

import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Category;
import com.goufaning.mall.db.service.CategoryService;
import com.goufaning.mall.wx.cache.HomeCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类controller
 *
 * @author goufn
 * @version V1.0
 * @date 2020/9/2 1:43 下午
 */
@RestController
@RequestMapping("/wx/category")
@Slf4j
public class WxCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getfirstcategory")
    public CommonResult<List<Category>> getFirstCategory() {
        // 所有一级分类目录
        List<Category> l1CatList = categoryService.queryL1();
        return CommonResult.success(l1CatList);
    }

    @GetMapping("/getsecondcategory")
    public CommonResult<List<Category>> getSecondCategory(@NotNull Integer id) {
        // 所有二级分类目录
        List<Category> currentSubCategory = categoryService.queryByPid(id);
        return CommonResult.success(currentSubCategory);
    }

    /**
     * 分类详情
     *
     * @param id   分类类目ID。
     *             如果分类类目ID是空，则选择第一个分类类目。
     *             需要注意，这里分类类目是一级类目
     * @return 分类详情
     */
    @GetMapping("index")
    public CommonResult<Map<String, Object>> index(Integer id) {

        // 所有一级分类目录
        List<Category> l1CatList = categoryService.queryL1();

        // 当前一级分类目录
        Category currentCategory = null;
        if (id != null) {
            currentCategory = categoryService.getById(id);
        } else {
            if (l1CatList.size() > 0) {
                currentCategory = l1CatList.get(0);
            }
        }

        // 当前一级分类目录对应的二级分类目录
        List<Category> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("categoryList", l1CatList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return CommonResult.success(data);
    }

    /**
     * 所有分类数据
     *
     * @return 所有分类数据
     */
    @GetMapping("all")
    public CommonResult<Map<String, Object>> queryAll() {
        //优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.CATALOG)) {
            return CommonResult.success(HomeCacheManager.getCacheData(HomeCacheManager.CATALOG));
        }
        // 所有一级分类目录
        List<Category> l1CatList = categoryService.queryL1();

        //所有子分类列表
        Map<Integer, List<Category>> allList = new HashMap<>();
        List<Category> sub;
        for (Category category : l1CatList) {
            sub = categoryService.queryByPid(category.getId());
            allList.put(category.getId(), sub);
        }

        // 当前一级分类目录
        Category currentCategory = l1CatList.get(0);

        // 当前一级分类目录对应的二级分类目录
        List<Category> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", l1CatList);
        data.put("allList", allList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);

        //缓存数据
        HomeCacheManager.loadData(HomeCacheManager.CATALOG, data);
        return CommonResult.success(data);
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @GetMapping("current")
    public CommonResult<Map<String, Object>> current(@NotNull Integer id) {
        // 当前分类
        Category currentCategory = categoryService.getById(id);
        if(currentCategory == null){
            return CommonResult.badArgumentValue();
        }
        List<Category> currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return CommonResult.success(data);
    }
}
