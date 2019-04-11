package com.wzz.acg.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties prop;

    static{
        String fileName = "acg.properties";
        prop = new Properties();
        try {
            prop.load(new InputStreamReader(
                    PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),
                    "UTF-8"));
        } catch (IOException e) {
            logger.info("配置文件读取异常", e);
        }
    }

    public static String getProperty(String key){
        String value = prop.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = null;
        }
        return value != null ? value.trim() : null;
    }

    public static String getProperty(String key, String defaultValue){
        String value = prop.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
