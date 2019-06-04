package com.aaron.framework.customizespring.importbean;

import com.aaron.framework.customizespring.importbean.aaron.MyComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-03-27
 */
@Component ("beanImport")
@MyComponentScan (packages = "com.aaron.framework.customizespring.importbean.aaron")
public class ImportBean
{
}
