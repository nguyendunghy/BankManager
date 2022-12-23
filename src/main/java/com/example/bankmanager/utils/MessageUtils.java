package com.example.bankmanager.utils;

import lombok.extern.slf4j.Slf4j;
import org.primefaces.context.RequestContext;

@Slf4j
public class MessageUtils {

	public static void setMessageForMessageId(String idMessage, String textMessage, String messageType) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String params = "'#" + idMessage + "','" + textMessage + "','" + messageType + "'";
		log.info("params:" + params);
		requestContext.execute("setMessagePrimeFToView(" + params + ")");
	}

	public static void setMessageForMessageId(String idMessage, String textMessage, String messageType, long delay) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String params = "'#" + idMessage + "','" + textMessage + "','" + messageType + "'";
		String javascript = "setTimeout(function showMessageTimeOut(){"
				+ "setMessagePrimeFToView(" + params + ");"
				+ "}," + delay + ");";

		log.info("message javascript:" + javascript);

		requestContext.execute(javascript);
	}
}
