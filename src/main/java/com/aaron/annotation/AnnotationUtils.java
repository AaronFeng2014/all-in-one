package com.aaron.annotation;

import java.lang.annotation.Annotation;
import javax.servlet.annotation.WebFilter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-04
 */
public final class AnnotationUtils
{
    private AnnotationUtils()
    {

    }


    public static boolean hasAnnotation(Class clazz, Class<WebFilter> annotationClazz)
    {
        Annotation annotation = clazz.getAnnotation(annotationClazz);

        return false;
    }

}
