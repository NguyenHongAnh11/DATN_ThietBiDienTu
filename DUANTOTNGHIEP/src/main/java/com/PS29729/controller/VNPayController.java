package com.PS29729.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.PS29729.entity.Order;
import com.PS29729.service.OrderService;
import com.PS29729.service.VNPayService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import vn.payos.PayOS;


@Controller
public class VNPayController {
	@Autowired
	private VNPayService vnPayService;

	@Autowired
	OrderService orderService;
	@PostMapping("/rest/orders/VNPAY")
	public ResponseEntity<ObjectNode> purchase(@RequestBody JsonNode orderData, HttpServletRequest request, HttpSession session) {
		try {
			Order order = orderService.create(orderData);
			int orderAmount = orderData.get("orderamount").asInt();
			String username = orderData.get("account").get("username").asText();
			String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode responseJson = mapper.createObjectNode();
			String vnpayUrl = vnPayService.createOrder(orderAmount, username, baseUrl);
			responseJson.put("urlpay", vnpayUrl);
			responseJson.putPOJO("order", order);
            session.setAttribute("orderData", order);
			return ResponseEntity.ok(responseJson);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/vnpay-payment")
	public String GetMapping(HttpServletRequest request, HttpSession session, Model model) {
		int paymentStatus = vnPayService.orderReturn(request);
        Order orderData = (Order) session.getAttribute("orderData");
        Long id = orderData.getId();
		model.addAttribute("order", orderService.findById(id));
		String orderInfo = request.getParameter("vnp_OrderInfo");
		String paymentTime = request.getParameter("vnp_PayDate");
		String transactionId = request.getParameter("vnp_TransactionNo");
		String totalPrice = request.getParameter("vnp_Amount");
		model.addAttribute("orderId", orderInfo);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("paymentTime", paymentTime);
		model.addAttribute("transactionId", transactionId);
		return paymentStatus == 1 ? "order/ordersuccess" : "order/orderfail";
	}
}
