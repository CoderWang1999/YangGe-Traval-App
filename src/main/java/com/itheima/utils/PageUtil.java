package com.itheima.utils;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: LHL
 * @date: 2020-08-21 12:30
 */
public class PageUtil {
          public static void page(String pageNum, String pageSize){
            int pageN = 1;
            int pageS = 5;
            if (StringUtils.isNotBlank(pageNum)) {
                pageN = Integer.parseInt(pageNum);
            }
            if (StringUtils.isNotBlank(pageSize)) {
                pageS = Integer.parseInt(pageSize);
            }
            PageHelper.startPage(pageN, pageS);
        }
    }
