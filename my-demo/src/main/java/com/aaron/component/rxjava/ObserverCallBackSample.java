package com.aaron.component.rxjava;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 观察者回调
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/8/9
 */
@Slf4j
public class ObserverCallBackSample
{

    /**
     * Action 接口作为onCompleted回调
     * Consumer 接口最为onNext或者onError的回调
     */
    private Action completed = () -> log.info("执行完成");

    private Consumer<String> onNext = content -> log.info("接收到数据：{}", content);

    private Consumer<Throwable> onError = throwable -> log.info("执行出错", throwable);


    @Test
    public void test01()
    {

        Observable.just("1", "2", "3").doOnComplete(() -> log.info("这是完成前的回调")).subscribe(onNext, onError, completed);
    }

}
