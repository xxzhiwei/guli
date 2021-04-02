package com.atshixin.order.service;

import com.atshixin.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-03-31
 */
public interface OrderService extends IService<Order> {
    String createOrder(String userId, String courseId);
    Order getOrderByNo(String orderNo);
    void updateOrderStatusByNo(String orderNo, Integer status);
}
