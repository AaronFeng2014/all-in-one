/**
 * @description 一句话描述该文件的用途
 * @author FengHaixin
 * @date 2018-09-01
 */

package com.aaron.netty.channel;

/*
 * * Channel接口
 *
 * 基本的IO操作（bind，connect，read，write），依赖底层网络传输所提供的原语
 *
 *
 * * Channel的生命周期：
 * 1. ChannelRegistered
 * 2. ChannelActive
 * 3. ChannelInactive
 * 4. ChannelUnregistered
 *
 * 当上面的状态发生改变的时候会产生相应的事件，这些事件会被转发给ChannelPipeline中的ChannelHandler
 *
 *
 * * ChannelHandler的生命周期
 * 1. handlerAdded：被添加到ChannelPipeline
 * 2. handlerRemoved：从ChannelPipeline中移除
 * 3. exceptionCaught：当在处理过程中，ChannelPipeline中有错误产生时调用
 *
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
 *
 * * ByteBuf
 *
 * ByteBuf是Netty的数据容器，主要是ByteBuf和ByteBufHolder这两个组件来处理数据
 *
 * ByteBuf API的优点：
 * 1.可以被用户自定义的缓冲区类型扩展
 * 2.通过内置的复合缓冲区类型实现了透明的零拷贝
 * 3.容量可以按需增长（类似于jdk的StringBuilder）
 * 4.在读和写这两种模式之间切换不需要向原生jdk的API那样调用flip方法
 * 5.读和写使用不同的索引
 * 6.支持方法的链式调用
 * 7.支持引用计数
 * 8.支持池化+
 *
 */