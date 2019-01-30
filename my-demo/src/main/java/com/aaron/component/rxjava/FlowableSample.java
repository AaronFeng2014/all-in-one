package com.aaron.component.rxjava;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.parallel.ParallelRunOn;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 背压：
 * <p>
 * 即是说，当数据发送方发送数据的速度大于数据消费方消费数据的速度时，
 * 数据发送方能够对发送的数据做出一定的缓存处理能力,
 * 待到数据消费方有能力消费的时候，再发送数据
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-17
 */
public class FlowableSample
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowableSample.class);


    @Test
    public void test() throws InterruptedException
    {
        Flowable.<String>create(flowableEmitter -> {

            int index = 0;
            while (true)
            {
                flowableEmitter.onNext("test - " + (index++));
                LOGGER.info("test - " + index);
                TimeUnit.MILLISECONDS.sleep(50);

                if (index > 200)
                {
                    break;
                }
            }

            flowableEmitter.onComplete();
            LOGGER.info("发送完成");
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.newThread())
                                     .observeOn(Schedulers.newThread())
                                     .subscribe(new Subscriber<String>()
                                     {
                                         @Override
                                         public void onSubscribe(Subscription subscription)
                                         {
                                             subscription.request(Long.MAX_VALUE);
                                         }


                                         @Override
                                         public void onNext(String s)
                                         {
                                             LOGGER.info("处理：" + s);

                                             try
                                             {
                                                 TimeUnit.MILLISECONDS.sleep(400);
                                             }
                                             catch (InterruptedException e)
                                             {
                                                 e.printStackTrace();
                                             }
                                         }


                                         @Override
                                         public void onError(Throwable throwable)
                                         {

                                         }


                                         @Override
                                         public void onComplete()
                                         {
                                             LOGGER.info("done");
                                         }
                                     });

        TimeUnit.SECONDS.sleep(30);

    }


    @Test
    public void testBlockFirst()
    {
        Integer blockingFirst = Flowable.fromArray(1, 2, 3, 4).blockingFirst();

        LOGGER.info("first item is {}", blockingFirst);
    }


    @Test
    public void testBlockLatest()
    {
        Iterable<Integer> blockingLatest = Flowable.fromArray(1, 2, 3, 4).blockingLatest();

        blockingLatest.forEach(num -> LOGGER.info("first item is {}", num));
    }


    @Test
    public void testBlockLast()
    {
        Integer blockingLast = Flowable.just(1).blockingSingle(-1);

        LOGGER.info("first item is {}", blockingLast);
    }


    @Test
    public void testBlockLast1() throws InterruptedException
    {

        Subscriber[] subscribers = new Subscriber[2];
        subscribers[0] = new Subscriber()
        {
            @Override
            public void onSubscribe(Subscription s)
            {
                s.request(10L);
            }


            @Override
            public void onNext(Object o)
            {
                LOGGER.info("-----item is {}", o);
            }


            @Override
            public void onError(Throwable t)
            {
                LOGGER.error("-----err is {}");
            }


            @Override
            public void onComplete()
            {

            }
        };

        subscribers[1] = new Subscriber()
        {
            @Override
            public void onSubscribe(Subscription s)
            {
                s.request(22L);
            }


            @Override
            public void onNext(Object o)
            {
                LOGGER.info("item is {}", o);

            }


            @Override
            public void onError(Throwable t)
            {
                LOGGER.error("-----err is {}");
            }


            @Override
            public void onComplete()
            {
                LOGGER.info("onComplete");
            }
        };

        ParallelRunOn.from(subscriber -> {

            subscriber.onSubscribe(new Subscription()
            {
                @Override
                public void request(long n)
                {

                }


                @Override
                public void cancel()
                {

                }
            });
            for (int i =1; i<= 100;i++)
            {
                subscriber.onNext("test " + i);

            }

            subscriber.onComplete();

        }, subscribers.length ).subscribe(subscribers);

        TimeUnit.SECONDS.sleep(5);
    }
}