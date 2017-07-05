package com.aaron.designpattern.adapter;

import com.aaron.designpattern.adapter.classAdapter.Source;
import com.aaron.designpattern.adapter.classAdapter.Targetable;
import com.aaron.designpattern.adapter.classAdapter.Wrapper;
import org.junit.Test;

/**
 * �����������ģʽ ��д���裺
 * 1.��дһ����ͨ��Source
 * 2.��дһ���ӿ�Targetable���ӿ�������Source��ķ�������������
 * 3.��дһ����������Wrapper��ʵ�ֽӿ�Targetable����дһ����Source���Ͳ����Ĺ��췽��
 * 4.��д������TestWrapper
 *
 * @author Genius
 */
public class TestWrapper
{

    @Test
    public void m01()
    {
        Source s = new Source();
        Targetable t = new Wrapper(s);
        t.source();
        t.newSource();
    }
}
