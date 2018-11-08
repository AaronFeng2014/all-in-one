## 微信小程序和服务号消息处理

### 功能介绍

### 接入指南

1. maven引入方式

2. 配置文件配置方式
    
       properties配置：
            wechat.config.appConfigList[0].appId=appid
            wechat.config.appConfigList[0].token=token
            wechat.config.appConfigList[0].encodingAesKeyencodingAesKey=encodingAesKey
       
       yml配置：
            wechat:
              config:
                appConfigList:
                - appId: appId1
                  token: token1
                  encodingAesKeyencodingAesKey: encodingAesKeyencodingAesKey1
                - appId: appId2
                  token: token2
                  encodingAesKeyencodingAesKey: encodingAesKeyencodingAesKey2
3. java基础实现
        
       a.接入的应用需要有一个controller实现 **AbstractMessageCallBackController**（该controller会自动暴露endpoints） 并实现一个需要唯一实现的方法 **afterPropertiesSet**
       b.在该方法中注册相应的MessageHandlerContext 和 MessageHandlerAdapterContext，形式如下
            
            //申明一个MessageHandlerAdapterContext，对应一个appId下的事件处理，同一个appId可以注册多个MessageHandlerAdapterContext
            MessageHandlerAdapterContext context = new MessageHandlerAdapterContext("appId");
            
            ontext.addMessageHandlerAdapter(ReceiveMessageType.ReceiveEventPushEnum.SUBSCRIBE_EVENT.getEventType(), params -> {},params -> {});
                    
            handlerContext.addMessageHandler(context);