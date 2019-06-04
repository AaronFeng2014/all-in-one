/**
 * @description 一句话描述该文件的用途
 * @author FengHaixin
 * @date 2019-04-03
 */

package com.aaron.concurrent.threadpool;

/**
 * 线程池实现的核心
 * <p>
 * 当一个线程中的任务执行结束的时候，该线程并不是立马销毁，而是继续遍历任务队列，去取任务继续执行
 * <p>
 * 也就是说：在线程的run方法中其实就是一个while循环，而每次执行任务都是取一个任务（Runnable）实例（通过getTask方法实现的），调用其run方法（而不是线程的start方法）
 * <p>
 * <p>
 * 多线程实现方法等待超时：
 * <p>
 * 利用condition实现
 **/