package com.lcp.blog.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class PropertyUtil {

    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps(){
        log.info("开始加载配置文件内容");
        props = new Properties();
        try {
            props.load(new InputStreamReader(Objects.requireNonNull(PropertyUtil.class.getClassLoader()
                    .getResourceAsStream("config/app-config.properties")), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            log.error("没找到配置文件!");
        } catch (IOException e) {
            log.error("出现IOException");
        }
        log.info("加载配置文件内容完成");
    }

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
