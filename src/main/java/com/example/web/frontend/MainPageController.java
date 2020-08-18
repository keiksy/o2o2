package com.example.web.frontend;

import com.example.entity.HeadLine;
import com.example.entity.ShopCategory;
import com.example.service.HeadLineService;
import com.example.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private HeadLineService headLineService;

    @RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listMainPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList", shopCategoryList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        List<HeadLine> headLineList = new ArrayList<>();
        try {
            HeadLine headLine = new HeadLine();
            headLine.setEnableStatus(1);
            headLineList = headLineService.getHeadLineList(headLine);
            modelMap.put("headLineList", headLineList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        modelMap.put("success", true);
        return modelMap;
    }

}
