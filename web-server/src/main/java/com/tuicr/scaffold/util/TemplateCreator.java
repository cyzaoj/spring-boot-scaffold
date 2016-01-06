package com.tuicr.scaffold.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * freemarker简单加载器
 *
 * @author ylxia
 * @version 1.0
 * @package com.iyerka.ec.network.util
 * @date 15/10/7
 */
@Slf4j
public final class TemplateCreator {


    /**
     * template配置
     */
    @Getter
    private Configuration freemarkerCfg;

    /**
     * 缓存
     */
    @Getter
    private StringTemplateLoader loader = new StringTemplateLoader();


    public TemplateCreator() {
        configuration();
    }


    /**
     * 配置freemarker
     *
     * @return
     */
    private void configuration() {
        freemarkerCfg = new Configuration(Configuration.VERSION_2_3_23);
        if (null != freemarkerCfg) {
            freemarkerCfg.setTemplateLoader(loader);
        }

    }


    /**
     * 增加自定义marco
     *
     * @param name
     * @param model
     */
    public void addShareVariable(String name, TemplateModel model) {
        freemarkerCfg.setSharedVariable(name, model);
    }

    /**
     * 增加模板
     *
     * @param name
     * @param source
     */
    public void addTemplate(String name, String source, long millis) {
        loader.putTemplate(name, source, millis);
    }


    /**
     * 解析结果
     *
     * @param name
     * @param rootMap
     * @return
     */
    public String toString(String name, Map<String, ?> rootMap) {
        StringWriter sw = new StringWriter();
        Template template = null;
        try {
            template = freemarkerCfg.getTemplate(name);
            template.process(rootMap, sw);
        } catch (IOException | TemplateException e) {
            log.debug("getTemplate failure !!! e={}", e);
            if (null != template) {
                return template.toString();
            }
        }
        return sw.toString();
    }

}