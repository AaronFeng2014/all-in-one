/**
 * @description 一句话描述该文件的用途
 * @author FengHaixin
 * @date 2018-09-02
 */

package com.aaron.component.netty.bytebuf;


/*
 **ByteBuf
 *
 *ByteBuf是Netty的数据容器，主要是ByteBuf和ByteBufHolder这两个组件来处理数据
 *
 *ByteBuf API的优点：
 *1.可以被用户自定义的缓冲区类型扩展
 *2.通过内置的复合缓冲区类型实现了透明的零拷贝
 *3.容量可以按需增长（类似于jdk的StringBuilder）
 *4.在读和写这两种模式之间切换不需要向原生jdk的API那样调用flip方法
 *5.读和写使用不同的索引
 *6.支持方法的链式调用
 *7.支持引用计数
 *8.支持池化+
 *
 *
 * ByteBuf的内存分配是由ByteBufAllocator接口实现的，他实现了ByteBuf的池化技术，已达到降低内存开销的目的
 *
 * ByteBufAllocator可以通过Channel或者绑定到ChannelHandlePipeline获取
 *
 * ByteBufAllocator提供了两种具体的实现，即PooledByteBufAllocator和UnpooledByteBufAllocator(池化和非池化)
 */