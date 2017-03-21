package com.aaron.beginner.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-12-01
 */
public class JMSConsumer
{
    private static final Log LOG = LogFactory.getLog(JMSConsumer.class);

    private static final Properties env = new Properties();

    private Connection connection;

    private Destination destination;


    private JMSConsumer()
    {
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, "http-remoting://192.168.2.175:8080");

        try
        {
            InitialContext context = new InitialContext(env);

            ConnectionFactory factory = (ConnectionFactory)context.lookup("java:/jms/RemoteConnectionFactory");

            connection = factory.createConnection();

            connection.start();

            destination = (Destination)context.lookup("java:/jms/queue/testQueue");
        }
        catch (Exception e)
        {
            LOG.error("创建InitialContext错误", e);
        }
    }


    public static void main(String[] args)
    {
        JMSConsumer jms = new JMSConsumer();

        for (int i = 0; i < 1; i++)
        {
            new Thread(() ->
            {
                try
                {
                    jms.consumeMsg();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }).start();
        }

        new Thread(() ->
        {
            try
            {
                jms.produceMsg();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();

        while (true)
        {

        }
    }


    private void consumeMsg() throws Exception
    {
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(destination);

        for (int i = 0; i < 500; i++)
        {
            consumer.setMessageListener(message ->
            {
                try
                {
                    if (message instanceof TextMessage)
                    {
                        TextMessage textMessage = (TextMessage)message;
                        System.out.println("received message content: " + textMessage.getText());

                    }

                    else if (message instanceof ObjectMessage)
                    {
                        ObjectMessage objectMessage = (ObjectMessage)message;

                        Object name = objectMessage.getObjectProperty("name");

                        System.out.println(name);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            });

            Thread.sleep(50);
        }
    }


    private void produceMsg() throws Exception
    {
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 500; i++)
        {
            producer.send(session.createTextMessage("Hello,这是第" + i + "次生产消息"));

            ObjectMessage shazi = session.createObjectMessage(new People("shazi", 34));

            producer.send(shazi);
            Thread.sleep(10);
        }
    }
}
