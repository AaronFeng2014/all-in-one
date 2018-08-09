package com.aaron.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 常用操作符
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/8/9
 */
@Slf4j
public class RXJavaOperatorSample
{

    private ObservableSource<String> source1 = observer -> {

        try
        {
            int a = 1 / 0;
            TimeUnit.SECONDS.sleep(3);
        }
        catch (Exception e)
        {
            observer.onError(e);
        }
        observer.onNext("111");
    };

    private ObservableSource<String> source2 = observer -> observer.onNext("222");

    private ObservableSource<String> source3 = observer -> observer.onNext("333");

    private ObservableSource<Integer> error = observer -> observer.onNext(-1);


    @Test
    public void merge()
    {
        Observable.mergeArray(source1, source2, source3).subscribe(result -> log.info("结果：{}", result));
    }


    /**
     * zip操作可以合并多个结果，但是需要你自己提供合并的算法
     */
    @Test
    public void zip()
    {
        BiFunction<String, String, Integer> function = (first, second) -> Integer.valueOf(first) + Integer.valueOf(second);

        /**
         * onExceptionResumeNext：聚合异常的时候，回到方法
         * onErrorResumeNext：聚合错误的时候，回到方法
         *
         * 以上两种，都需要主动抛出，即主动调用onError方法
         */
        Observable.zip(source1, source2, function).onExceptionResumeNext(error).subscribe((result) -> log.info("合并结果：{}", result));
    }


    @Test
    public void interval() throws InterruptedException
    {

        Observable.interval(2, TimeUnit.SECONDS).subscribe((result) -> log.info("定时轮询：{}", result));

        TimeUnit.MINUTES.sleep(1);
    }
}
