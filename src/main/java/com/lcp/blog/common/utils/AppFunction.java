package com.lcp.blog.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lcp
 * @date 2019-12-04 17:35
 */
@Slf4j
public class AppFunction {

//    public static String getAttribute(HttpServletRequest request, String key) {
//        Map<String, String> paramMap = getQueryParamMap(request);
//
//        log.info("key:{},key对应的value{}", key, paramMap != null ? paramMap.get(key) : null);
//        return paramMap != null ? paramMap.get(key) : null;
//    }

//    private static Map<String, String> getQueryParamMap(HttpServletRequest request) {
//        String queryString = request.getQueryString();
//        if (StringUtils.isBlank(queryString)) {
//            return null;
//        }
//
//        Map<String, String> result = new HashMap<>(2);
//        String[] split = queryString.split("&");
//        for (String s : split) {
//            String[] split1 = s.split("=");
//            if (split1.length != 2) {
//                continue;
//            }
//            result.put(split1[0], split1[1]);
//        }
//        return result;
//    }

    /**
     * Bean对象转换成Map
     */
    public static Map<String, Object> beanToMap(Object obj) {
        return AppFunction.beanToMap(obj, false);
    }

    /**
     * Bean对象转换成Map
     *
     * @param isExcludeEmpty 当检测到空字符串，是否将其变为null
     */
    public static Map<String, Object> beanToMap(Object obj, boolean isExcludeEmpty) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] proDesc = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : proDesc) {
                String key = pd.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }

                Method getter = pd.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                map.put(key, value);
                if (isExcludeEmpty)
                    if (value != null && value instanceof String && "".equals((value + "").trim()))
                        map.put(key, null);
            }
        } catch (Exception e) {
//            throw new AppException("数据转换失败!", AppException.SYSTEM_FAIL);
        }

        return map;
    }

    /**
     * 取得当前时间戳(10位)
     *
     * @return
     */
    public static int getTime() {
        return (int) (AppFunction.getMillisTime() / 1000L);
    }


    /**
     * 取得当前时间戳(13位)
     */
    private static long getMillisTime() {
        return Calendar.getInstance().getTime().getTime();
    }

    /**
     * MD5加密
     * @param content 加密内容
     * @return
     */
    public static String md5(String content) {
        //用于加密的字符
        char[] md5String = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = content.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = md5String[byte0 >>> 4 & 0xf];
                str[k++] = md5String[byte0 & 0xf];
            }

            //返回经过加密后的字符串
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String encryption(String password, String salt){
        String md5 = AppFunction.md5(password);
        if (StringUtils.isBlank(md5)) {
            return null;
        }
        String md51 = AppFunction.md5(md5 + salt);
        if (StringUtils.isBlank(md51)) {
            return null;
        }
        return md51;
    }

    public static String timeFormatted(Object dateTime, String pattern) {
        if (dateTime == null) {
            return "";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            if (dateTime.getClass().equals(Long.class)) {
                Date date = new Date((Long) dateTime);
                return sdf.format(date);
            }
            Method intValue = dateTime.getClass().getDeclaredMethod("intValue");
            Date date = new Date(((Integer) intValue.invoke(dateTime)) * 1000L);
            return sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("不支持的数据类型", AppException.FORM_INVALID);
        }

    }
}
