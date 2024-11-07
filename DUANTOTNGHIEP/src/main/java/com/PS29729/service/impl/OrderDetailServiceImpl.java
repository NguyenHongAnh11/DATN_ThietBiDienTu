package com.PS29729.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS29729.dao.OrderDetailDAO;
import com.PS29729.entity.OrderDetail;
import com.PS29729.service.OrderDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDAO orderDetailRepository;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findById(Long id) {
        Optional<OrderDetail> result = orderDetailRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail update(Long id, OrderDetail orderDetail) {
        if (orderDetailRepository.existsById(id)) {
            orderDetail.setId(id);
            return orderDetailRepository.save(orderDetail);
        } else {
            return null;
        }
    }
    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public boolean delete(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
