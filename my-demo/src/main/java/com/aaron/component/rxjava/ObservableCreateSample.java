package com.aaron.component.rxjava;

import com.aaron.designpattern.observe.impl2.Students;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * RXJava中观察者创建的几种方式
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-08-08
 */
public class ObservableCreateSample
{

    private Observer<Students> studentsObserver = new Observer<Students>()
    {
        @Override
        public void onSubscribe(Disposable disposable)
        {

        }


        @Override
        public void onNext(Students students)
        {
            System.out.println(students);
        }


        @Override
        public void onError(Throwable throwable)
        {

        }


        @Override
        public void onComplete()
        {
            System.out.println("所有已完成");
        }
    };


    @Test
    public void fromStyle() throws InterruptedException
    {
        Students students1 = new Students();
        students1.setAge(12);
        Students students2 = new Students();
        students2.setAge(22);

        Observable.fromArray(students1, students2).subscribe(studentsObserver);

        TimeUnit.SECONDS.sleep(2);
    }


    @Test
    public void concatStyle() throws InterruptedException
    {
        Students students1 = new Students();
        students1.setAge(12);
        Students students2 = new Students();
        students2.setAge(22);

        ObservableSource<Students> source = observer -> {

            observer.onNext(students1);

            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            observer.onComplete();
        };

        source.subscribe(new Observer<Students>()
        {

            @Override
            public void onSubscribe(Disposable d)
            {

            }


            @Override
            public void onNext(Students students)
            {

                System.out.println("自己的：" + students);
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

        ObservableSource<Students> source1 = observer -> {

            observer.onNext(students1);

            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            observer.onComplete();
        };

        source.subscribe(new Observer<Students>()
        {

            @Override
            public void onSubscribe(Disposable d)
            {

            }


            @Override
            public void onNext(Students students)
            {

                System.out.println("自己的：" + students);
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
        /**
         * concat主要合并几个可观察对象，当都完成时，才会调用最终的观察者方法
         */
        Observable.concat(source, source1).subscribe(studentsObserver);

        TimeUnit.SECONDS.sleep(5);

    }
}
