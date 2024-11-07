package com.PS29729.rest.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PS29729.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;
import vn.payos.type.PaymentLinkData;

@RestController
@RequestMapping("/rest/payment")
public class OrderRest1Controller {
	private final PayOS payOS;

	public OrderRest1Controller(PayOS payOS) {
		super();
		this.payOS = payOS;
	}

	@PostMapping(path = "/create/PayOS")
	public ObjectNode createPaymentLink(@RequestBody Order RequestBody) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode response = objectMapper.createObjectNode();
		try {
			// Prepare order data
			final String productName = RequestBody.getFistname();
			final String description = RequestBody.getDistricts();
			final String returnUrl = "/index";
			final String cancelUrl = "/index";
			final int price = RequestBody.getOrderamount();
			String currentTimeString = String.valueOf(new Date().getTime());
			long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));

			ItemData item = ItemData.builder().name(productName).price(price).quantity(1).build();
			PaymentData paymentData = PaymentData.builder().orderCode(orderCode).description(description).amount(price)
					.item(item).returnUrl(returnUrl).cancelUrl(cancelUrl).build();

			// Invoke createPaymentLink
			Object rawResponse = payOS.createPaymentLink(paymentData);

			// Check and convert response if necessary
			CheckoutResponseData data;
			if (rawResponse instanceof LinkedHashMap) {
				// Convert LinkedHashMap to CheckoutResponseData
				data = objectMapper.convertValue(rawResponse, CheckoutResponseData.class);
			} else {
				data = (CheckoutResponseData) rawResponse;
			}

			// Construct response
			response.put("error", 0);
			response.put("message", "success");
			response.set("data", objectMapper.valueToTree(data));
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", -1);
			response.put("message", "fail");
			response.set("data", null);
			return response;
		}
	}

	@GetMapping(path = "/{orderId}")
	public ObjectNode getOrderById(@PathVariable("orderId") long orderId) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode response = objectMapper.createObjectNode();

		try {
			PaymentLinkData order = payOS.getPaymentLinkInformation(orderId);

			response.set("data", objectMapper.valueToTree(order));
			response.put("error", 0);
			response.put("message", "ok");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", -1);
			response.put("message", e.getMessage());
			response.set("data", null);
			return response;
		}

	}

	@PutMapping(path = "/{orderId}")
	public ObjectNode cancelOrder(@PathVariable("orderId") int orderId) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode response = objectMapper.createObjectNode();
		try {
			PaymentLinkData order = payOS.cancelPaymentLink(orderId, null);
			response.set("data", objectMapper.valueToTree(order));
			response.put("error", 0);
			response.put("message", "ok");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", -1);
			response.put("message", e.getMessage());
			response.set("data", null);
			return response;
		}
	}

	@PostMapping(path = "/confirm-webhook")
	public ObjectNode confirmWebhook(@RequestBody Map<String, String> requestBody) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode response = objectMapper.createObjectNode();
		try {
			String str = payOS.confirmWebhook(requestBody.get("webhookUrl"));
			response.set("data", objectMapper.valueToTree(str));
			response.put("error", 0);
			response.put("message", "ok");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", -1);
			response.put("message", e.getMessage());
			response.set("data", null);
			return response;
		}
	}
}
