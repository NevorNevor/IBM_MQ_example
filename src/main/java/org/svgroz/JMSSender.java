package org.svgroz;

import com.ibm.jms.JMSBytesMessage;
import com.ibm.jms.JMSTextMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by SGrozovsky on 17.08.2016.
 */
public class JMSSender {
    private JmsTemplate jmsTemplate;
    private Destination destination;

    public JMSSender(JmsTemplate jmsTemplate, Destination destination){
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
    }

    public void send(){
//        this.jmsTemplate.convertAndSend("Q1", "Hello");
        this.jmsTemplate.convertAndSend(this.destination, "Hello");
    }
}
