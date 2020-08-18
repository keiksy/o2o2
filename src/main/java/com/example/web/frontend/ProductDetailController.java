package com.example.web.frontend;

import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listProductDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        long productId = HttpServletRequestUtil.getLong(request, "productId");
        if (productId>-1) {
            Product product = productService.getProductById(productId);
            modelMap.put("product", product);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "productId error!");
        }
        return modelMap;
    }
}
