package com.aaron.designpattern.adapter.classAdapter;

/**
 * Adapter��̳�Source�࣬ʵ��Targetable�ӿ� ����Targetable�ӿڵ�ʵ����;�����Source��Ĺ���
 *
 * @author Genius
 */
public class ClassAdapter extends Source implements Targetable
{

    @Override
    public void newSource()
    {

        System.out.println("this is new method in interface!");
    }

}
