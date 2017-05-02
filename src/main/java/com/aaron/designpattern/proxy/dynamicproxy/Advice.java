/**
 *
 */
package com.aaron.designpattern.proxy.dynamicproxy;

import java.lang.reflect.Method;

/**
 * @author Aaron
 */
public interface Advice
{

    void doBeforeMethod(Method method);

    void doAfterMethod(Method method);
}
