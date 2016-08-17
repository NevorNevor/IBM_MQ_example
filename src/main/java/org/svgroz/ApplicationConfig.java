package org.svgroz;

import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * Created by SGrozovsky on 17.08.2016.
 */
@Configuration
public class ApplicationConfig {
    private static final String QUEUE_MANAGER = "QM1";
    private static final String QUEUE = "Q1";
    private static final String CHANNEL = "PASSWORD.SVRCONN";
    private static final String USER = "user";
    private static final String PASSWORD = "passw0rd";

    @Bean
    public ConnectionFactory getConnectionFactory() throws Exception {
        MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(1414);
        connectionFactory.setQueueManager(QUEUE_MANAGER);
        connectionFactory.setChannel(CHANNEL);
        connectionFactory.setTransportType(1);
        return connectionFactory;
    }

    @Bean
    public Destination getDestination() throws Exception {
        return new MQQueue(QUEUE);
    }

    @Autowired
    @Bean
    public JmsTemplate getJmsTemplate(ConnectionFactory connectionFactory){
        return new JmsTemplate(connectionFactory);
    }

    @Autowired
    @Bean
    public JMSSender getJmsSender(JmsTemplate jmsTemplate, Destination destination){
        return new JMSSender(jmsTemplate, destination);
    }

    @Bean
    public JMSListener getJmsListener(){
        return new JMSListener();
    }

    @Bean @Autowired
    public MessageListenerContainer getMessageListenerContainer(ConnectionFactory connectionFactory, JMSListener jmsListener, Destination destination){
        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setDestination(destination);
        listenerContainer.setMessageListener(jmsListener);
        return listenerContainer;
    }
}
