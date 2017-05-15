package com.aaron.designpattern.adapter.interfaceAdapter;

import org.junit.Test;

/**
 * �ӿڵ������� 1.��дһ���ӿ�Sourceable
 * 2.��дһ��������AbstractWrapper��ʵ�ֽӿڵķ�����ע�⣺������ʵ�ֽӿڣ����Բ���ʵ�ֽӿڵķ�����
 * 3.��д��ͨ��SourceableSub1���̳г�����AbstractWrapper������д���ַ���
 * 4.��д��ͨ��SourceableSub2���̳г�����AbstractWrapper������д���ַ���
 * 5.��д������TestAbstractWrapper
 * SourceableSub1��SourceableSub2�̳��˳�����AbstractWrapper��
 * ������AbstractWrapperʵ���˽ӿ�Sourceable
 * ������SourceableSub1��SourceableSub2Ҳ�ǽӿ�Sourceable��ʵ����
 *
 * @author Genius
 */
public class TestAbstractWrapper
{

    @Test
    public void m01()
    {
        Sourceable s1 = new SourceableSub1();
        Sourceable s2 = new SourceableSub2();

        s1.method01();
        s1.method02();
        s2.method04();
        s2.method05();
        s2.method06();
    }
}
