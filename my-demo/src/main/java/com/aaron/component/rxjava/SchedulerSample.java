package com.aaron.component.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * scheduler主要用于控制观察者或者被观察者行为发生的线程
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/8/9
 */
@Slf4j
public class SchedulerSample
{
    @Test
    public void test01()
    {

        /**
         * subscribeOn指定订阅发生在哪个线程，即时间产生的线程
         *
         * observeOn指定观察者线程，即事件消费的线程
         */
        Observable.fromArray("1111", "2222").subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(new Observer<String>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }


            @Override
            public void onNext(String result)
            {
                log.info("result->" + result);
            }


            @Override
            public void onError(Throwable e)
            {

            }


            @Override
            public void onComplete()
            {
                log.info("all done");
            }
        });
    }


    @Test
    public void test02() throws InterruptedException
    {

        ObservableOnSubscribe<String> observableOnSubscribe = emitter -> {

            Thread.sleep(3000);

            log.info("发送数据：this is a test");
            emitter.onNext("this is a test");

            emitter.onComplete();
        };

        Observable<String> stringObservable = Observable.create(observableOnSubscribe)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(Schedulers.newThread());

        stringObservable.subscribe(new Observer<String>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }


            @Override
            public void onNext(String result)
            {
                log.info("result->" + result);
            }


            @Override
            public void onError(Throwable e)
            {

            }


            @Override
            public void onComplete()
            {
                log.info("all done");
            }
        });

        Thread.sleep(5000);
        System.out.println("end");

        stringObservable.subscribe(content -> log.info("只关心onNext事件：{}", content));
    }

}
