/**
 * spring中BeanPostProcessor的使用
 * <p>
 * 主要用于在spring中bean的生命周期中，bean创建的前后，给用户一个机会，自己去对bean做一些额外的事情
 * <p>
 * 例如，判断有没有实现特定的接口，执行接口方法等，Spring中Aware一类的接口就是通过这种方式实现的
 *
 * @description 一句话描述该文件的用途
 * @author FengHaixin
 * @date 2019-06-04
 */

package com.aaron.framework.customizespring.beanpostprocessor;