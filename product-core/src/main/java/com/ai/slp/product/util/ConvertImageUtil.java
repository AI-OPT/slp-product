package com.ai.slp.product.util;

import java.util.ArrayList;
import java.util.List;

import com.ai.slp.product.api.webfront.param.ProductImage;
/**
 *图片转换工具类 
 *
 */
public final class ConvertImageUtil {
    private ConvertImageUtil() {
    }

    /**
     * 转换商品图片对象
     */
    public static ProductImage convert(String imageinfo){
        imageinfo = imageinfo.replace("{", "");
        imageinfo = imageinfo.replace("}", "");
        imageinfo = imageinfo.replace("\"", "");
        String sd[]= imageinfo.split(",");
        ProductImage image = new ProductImage();
        for(int i=0;i<sd.length;i+=2){
           image.setVfsId(sd[i+1].split(":")[1]);
           image.setPicType(sd[i].split(":")[1]);
        }
        return image;
    }
    /**
     * 图片集合转换
     */
    public static List<ProductImage> convertThum(String imageinfo){
        List<ProductImage> list = new ArrayList<ProductImage>();
        imageinfo = imageinfo.replace("{", "");
        imageinfo = imageinfo.replace("[", "");
        imageinfo = imageinfo.replace("]", "");
        imageinfo = imageinfo.replace("}", "");
        imageinfo = imageinfo.replace("\"", "");
        String sd[]= imageinfo.split(",");
        for(int i=0;i<sd.length;i+=2){
            ProductImage image = new ProductImage();
           image.setVfsId(sd[i+1].split(":")[1]);
           image.setPicType(sd[i].split(":")[1]);
           list.add(image);
        }
        return list;
    }
       
}
