package com.aaron.designpattern.state.dog;

import com.aaron.designpattern.state.DogState;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
@ToString
public class BadDog implements DogState
{
    @Override
    public void actionPerform()
    {
        System.out.println(this.getClass().getSimpleName() + "会咬人");
    }
}
