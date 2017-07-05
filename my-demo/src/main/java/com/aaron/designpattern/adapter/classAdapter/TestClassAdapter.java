package com.aaron.designpattern.adapter.classAdapter;

import org.junit.Test;

/**
 * ���������ģʽ ��д���裺
 * 1.��дһ����ͨ��Source
 * 2.��дһ���ӿ�Targetable���ӿ�������Source��ķ�������������
 * 3.��дһ����������Adapter���̳���Source��ʵ�ֽӿ�Targetable
 * 4.��д������TestAdapter�����ࣩ
 *
 * @author Genius
 */
public class TestClassAdapter
{

    @Test
    public void m01()
    {
        Targetable target = new ClassAdapter();
        target.source();
        target.newSource();
    }
}
