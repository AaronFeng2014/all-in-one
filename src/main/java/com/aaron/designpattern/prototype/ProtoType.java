package com.aaron.designpattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ԭ��ģʽ
 * ԭ��ģʽ��Ȼ�Ǵ����͵�ģʽ�������빤��ģʽû�й�ϵ�������ּ��ɿ�������ģʽ��˼����ǽ�һ��������Ϊԭ�ͣ�
 * ������и��ơ���¡������һ����ԭ�������Ƶ��¶���
 * һ��ԭ���ֻ࣬��Ҫʵ��Cloneable�ӿڣ���дclone�������˴�clone�������Ըĳ����������
 * ����ΪCloneable�ӿ��Ǹ��սӿڣ���������ⶨ��ʵ����ķ���������cloneA����cloneB��
 * ��Ϊ�˴����ص���super.clone()��仰��super.clone()���õ���Object��clone()������
 * ����Object���У�clone()��native�ģ�������ôʵ�֣��һ�����һƪ�����У����ڽ��Java�б��ط����ĵ��ã�
 * �˴��������
 * ��������ҽ���϶����ǳ���ƺ������˵һ�£�������Ҫ�˽�����ǳ���Ƶĸ��
 * ǳ���ƣ���һ�������ƺ󣬻����������͵ı����������´��������������ͣ�ָ��Ļ���ԭ������ָ��ġ�
 * ��ƣ���һ�������ƺ󣬲����ǻ����������ͻ����������ͣ��������´����ġ�����˵��������ƽ�������ȫ���׵ĸ��ƣ���ǳ���Ʋ����ס�
 *
 * @author Genius
 */
public class ProtoType implements Cloneable
{

    public String getStr()
    {
        return str;
    }


    public void setStr(String str)
    {
        this.str = str;
    }


    public SerializableObject getObj()
    {
        return obj;
    }


    public void setObj(SerializableObject obj)
    {
        this.obj = obj;
    }


    private String str;
    private SerializableObject obj;


    /**
     * ǳ����
     */
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        ProtoType proto = (ProtoType)super.clone();
        return proto;
    }


    /**
     * ���
     *
     * @return
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    public Object deepClone() throws IOException, ClassNotFoundException
    {
        //д�뵱ǰ����Ķ�������
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        //�����������������¶���
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

}

class SerializableObject implements Serializable
{

    private static final long serialVersionUID = -5391475391382847500L;

}
