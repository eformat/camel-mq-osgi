package com.fusesource.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;




public class MyTransform implements Processor, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(MyTransform.class);
	
	public void process(Exchange exchange) throws Exception {
		logger.debug("Entered Transform.");

		try {
			
			// Transformation
			logger.debug("got JMSMessageID = " + exchange.getIn().getHeader("JMSMessageID"));
			exchange.setOut(exchange.getIn());
			exchange.getOut().setBody(exchange.getIn().getBody());
			//exchange.getOut().setHeader("JMSCorrelationID", exchange.getIn().getHeader("JMSMessageID"));
			logger.debug("Message body = " + exchange.getOut().getBody());
			logger.debug("Finished Transform.");
		} catch (Exception e) {
			logger.warn("Error while invoking on the AddressService", e);
			exchange.setException(e);
//			exchange.getOut();
//			exchange.getFault(true).setBody(new SoapFault("Unable to process request due to server error.", SoapFault.FAULT_CODE_SERVER));
		}
	}

	public void afterPropertiesSet() throws BeanInitializationException {
	}

}
