package org.svgroz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by SGrozovsky on 17.08.2016.
 */
public class JMSListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(JMSListener.class);

    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            LOGGER.info(textMessage.getText());
        } catch (Exception ex) {
            LOGGER.error("Error on message", ex);
        }
    }
}
