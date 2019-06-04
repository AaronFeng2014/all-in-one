package com.aaron.framework.customizespring.conditional.matchcondition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
public class WindowsCondition implements Condition
{
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata)
    {
        String osName = conditionContext.getEnvironment().getProperty("os.name");

        if (StringUtils.isEmpty(osName))
        {
            return false;
        }

        return osName.contains("Windows");
    }
}
