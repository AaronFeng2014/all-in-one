/**
 *
 */
package com.aaron.designpattern.observe.impl2;

/**
 * @author Aaron
 */
public class SubjectImpl extends Subjects
{

    /* (non-Javadoc)
     * @see com.huawei.impl.Subjects#change(java.lang.String)
     */
    @Override
    public void change(int newAge)
    {
        System.out.println(this.getClass().getSimpleName() + " change " + newAge);
        this.notifyObsever(this, newAge);

    }

}
