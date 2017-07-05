package com.aaron.designpattern.state;

import com.aaron.designpattern.state.dog.BadDog;
import com.aaron.designpattern.state.dog.GoodDog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
@Setter
@Getter
public class DogContext
{
    private DogState goodDog;

    private DogState badDog;

    private DogState dogState;


    public DogContext()
    {
        this.goodDog = new GoodDog();

        this.badDog = new BadDog();

        dogState = goodDog;
    }


    public void action()
    {
        dogState.actionPerform();
    }
}
