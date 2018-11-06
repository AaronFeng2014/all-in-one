package com.aaron.wx;

import com.aaron.wx.handler.MessageHandlerAdapterContext;
import com.aaron.wx.handler.MessageHandlerContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信消息回调事件处理器，该类是一个抽象类，需要用户实现该类，并实现该类中唯一需要实现的方法afterPropertiesSet {@link InitializingBean}
 * <p>
 * 实现该方法的目的是向handlerContext中注册不同的小程序或者服务号的MessageHandlerAdapterContext
 * <p>
 * 而MessageHandlerAdapterContext中有需要注册同一个小程序或者服务号下的不同事件处理器，例如 text，image， scan等事件对应的处理adapter
 * <p>
 * 消息适配原理：{@link MessageHandlerContext}  {@link MessageHandlerAdapterContext}
 * <p>
 * 微信回到的请求参数中会携带该消息所属的appId，而我们在配置MessageHandler时，会指定其appId参数；
 * 在接收到请求时，会解析出消息的appId信息，去和handlerContext中的MessageHandlerAdapterContext列表中的每一个MessageHandlerAdapterContext做匹配，
 * 匹配上了，就会被MessageHandlerAdapterContext处理
 * <p>
 * 如果所有的MessageHandlerAdapterContext列表中都没有被匹配到一个合适的MessageHandlerAdapterContext，会发出一个警告信息，提醒开发者注意
 * <p>
 * 如果匹配到了MessageHandlerAdapterContext，但是没有匹配到具体的事件处理adapter，也会发出相应的警告
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public abstract class AbstractMessageCallBackController implements InitializingBean
{

    /**
     * 微信消息回调官方推荐返回
     * 并异步处理逻辑
     */
    private static final String SUCCESS = "SUCCESS";

    protected MessageHandlerContext handlerContext = new MessageHandlerContext();


    @RequestMapping ("message/callback/{appId}")
    public String handle(@PathVariable ("appId") String appId, HttpServletRequest request)
    {

        if (checkConnection())
        {
            return "";
        }

        String params = "";

        doHandle(params);

        return SUCCESS;
    }


    /**
     * 微信开发平台配置地址时，会检查连接是否可用，需要按要求原样返回请求内容
     *
     * @return
     */
    private boolean checkConnection()
    {

        return true;
    }


    /**
     * 异步，核心逻辑处理
     *
     * @param originalParams String：微信回调的原始参数
     */
    @Async
    void doHandle(String originalParams)
    {
        handlerContext.handleMessageChain(originalParams);
    }
}