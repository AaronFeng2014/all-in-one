package com.aaron.rxjava;

import com.aaron.beginner.jms.People;
import com.aaron.designpattern.observe.impl2.Students;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 响应式编程 RXJava学习demo
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/6/29
 */
public class RXJavaSample
{

    /**
     * RXJava是以观察者模式为基础
     * <p>
     * RX java基础接口
     * <p>
     * Publisher：发出事件
     * Subscriber：处理事件
     * Subscription
     * Processor
     * <p>
     * <p>
     * RXJava  三部曲
     * <p>
     * ** 第一步：初始化 Observable **
     * ** 第二步：初始化 Observer **
     * ** 第三步：建立订阅关系 **
     */

    public static void main(String[] args) throws InterruptedException
    {
        Observable.<People>create(emitter -> {

            emitter.onNext(new People("feng", 1));
            emitter.onNext(new People("feng", 2));
            emitter.onNext(new People("feng", 3));
            emitter.onNext(new People("feng", 4));

            emitter.onComplete();

            //map函数用于改变类型，是函数式编程中比较常用的函数
        }).map(o -> {

            Students students = new Students();

            students.setAge(o.getAge());

            return students;

            //订阅消息
        }).subscribeOn(Schedulers.newThread()).subscribe(new Observer<Students>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                System.out.println("注册");

            }


            /**
             * 观察者处理完自己的业务逻辑后， 被观察者才能发起下一次调用
             *
             * @param o
             */
            @Override
            public void onNext(Students o)
            {
                System.out.println("接收到消息：" + o);
                try
                {
                    TimeUnit.SECONDS.sleep(5);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }


            @Override
            public void onError(Throwable e)
            {
                System.out.println("出错");

            }


            @Override
            public void onComplete()
            {
                System.out.println("完成");

            }
        });

        TimeUnit.MINUTES.sleep(1);
    }


    @Test
    public void interval() throws InterruptedException
    {
        Flowable.interval(1, TimeUnit.SECONDS).subscribe(aLong -> System.out.println(aLong + 10));

        TimeUnit.SECONDS.sleep(10);
    }


    @Test
    public void single() throws InterruptedException
    {
        Single.just("nihasdf").subscribe(new SingleObserver<String>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }


            @Override
            public void onSuccess(String o)
            {

                System.out.println(o);
            }


            @Override
            public void onError(Throwable e)
            {

            }
        });

        TimeUnit.SECONDS.sleep(10);
    }


    /**
     * 去除发送时间间隔小于某个值的
     *
     * @throws InterruptedException
     */
    @Test
    public void debounce() throws InterruptedException
    {
        Observable.<String>create(emitter -> {

            emitter.onNext("test1");
            TimeUnit.MILLISECONDS.sleep(100);
            emitter.onNext("test2");
            emitter.onNext("test3");
            TimeUnit.MILLISECONDS.sleep(600);
            emitter.onNext("test4");
            emitter.onNext("test5");

            /**
             * debounce：用于过滤高频操作，比如重复点击等等
             *
             * 当指定的时间间隔过去了，仍旧没有发送数据，那么将会发送最后一个
             */
        }).debounce(500, TimeUnit.MILLISECONDS).subscribe(new Observer<String>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }


            @Override
            public void onNext(String o)
            {
                System.out.println(o);

            }


            @Override
            public void onError(Throwable e)
            {

            }


            @Override
            public void onComplete()
            {

            }
        });

        TimeUnit.SECONDS.sleep(10);
    }
}
