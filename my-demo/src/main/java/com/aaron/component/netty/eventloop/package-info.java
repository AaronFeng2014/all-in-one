/**
 * @description 一句话描述该文件的用途
 * @author FengHaixin
 * @date 2018-09-02
 */

package com.aaron.component.netty.eventloop;


/*
 * * EventLoop接口
 * 此接口定义了Netty的核心抽象，用于处理连接的生命周期中所发生的事件
 *
 *
 *
 * * 他们之间的关系是：
 *
 * 一个EventLoopGroup中包含一个或者多个EvenLoop
 *
 * 一个EventLoop在他的生命周期中之和一个Thread绑定
 *
 * 所有由EventLoop处理的Io事件都将在它专有的Thread上被处理
 *
 * 一个Channel在他的生命周期内值注册一个EventLoop
 *
 * 一个EventLoop啃个被分配给一个或者多个Channel
 *
 * EventLoopGroup负责为每个新创建的Channel分配EventLoop，一旦某个Channel被分配给某个EventLoop，那么该Channel的整个生命周期内都将使用
 * 这个EventLoop（以及关联的线程）
 *
 *
 * * ChannelFuture接口
 * Netty的所有操作都是一部的，因此需要一个能够在后面的某个时间点确定其返回结果接口，而ChannelFuture正是该接口，其addListener用于注册ChannelFutureListener，便于在完成操作时，得到通知
 *
 *
 * * ChannelHandler
 * 从应用程序开发人员的角度来看，Netty的主要构件就是ChannelHandler，他充当了所有的处理入站和出站数据的应用程序逻辑的容器，该接口中的方法都是有网络中的事件触发的
 *
 *
 * * ChannelPipeline
 *
 * ChannelPipeline为ChannelHandler链提供了容器，并定义了在该链上传播入站和出站事件流的API， 当创建一个Channel的时候，他会自动被分配到一个他专属的ChannelPipeline
 *
 */