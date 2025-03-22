package com.pys.course.admin.support.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageUtil {
	@Value("${pys.course.msg91.authKey}")
	private static String authkey;

	@Value("${pys.course.msg91.url}")
	private static String msg91URL;
	
	@Value("${pys.course.senderId}")
	private static String senderId;

	@Value("${pys.course.flowIdToSendPaymentRemainder}")
	private static String flowIdToSendPaymentRemainder;

	@SuppressWarnings("unused")
	public static void sendMessage(String jsonObject) throws IOException {
		// Send SMS API
		HttpResponse<String> response;
		try {
			response = Unirest.post(msg91URL).header("authkey", authkey).header("content-type", "application/JSON")
					.body(jsonObject).asString();
		} catch (UnirestException e) {
			log.error("Exception occured while sending a message to customer:{}", e.getLocalizedMessage());
		}
	}
	
	public static String getJsonObjectToSendPaymentRemainder(String mobiles, String name, String courseName, String paymentDate) {
		String jsonObject = "{\n  \"flow_id\": \"" + flowIdToSendPaymentRemainder + "\",\n  \"sender\": \"" + senderId
				+ "\",\n  \"mobiles\": \"91" + mobiles + "\",\n \"courseName\": \"" + courseName + "\",\n\"paymentDate\":\""
				+ paymentDate + "\",\n \"name\":\"" + name + "\"\n}";
		return jsonObject;
	}
}
