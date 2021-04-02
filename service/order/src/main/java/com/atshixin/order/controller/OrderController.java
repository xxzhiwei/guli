package com.atshixin.order.controller;


import com.atshixin.order.entity.Order;
import com.atshixin.order.service.OrderService;
import com.atshixin.util.JWT;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-03-31
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单
    @PostMapping
    public R createOrder(@RequestBody Map<String, String> orderDTO, HttpServletRequest request) {
        String userId = JWT.getUserIdByToken(request);
        String courseId = orderDTO.get("courseId");
        String orderNo = orderService.createOrder(userId, courseId);
        return R.ok().data("orderNo", orderNo);
    }

    // 获取订单信息
    @GetMapping("/{orderNo}")
    public R getOrderByNo(@PathVariable String orderNo) {
        Order order = orderService.getOrderByNo(orderNo);
        return ResultHelper.format(order);
    }

    // 根据订单号生成微信支付二维码
    @PostMapping
    public R createWechatPayQRCodeByNo() {
        return R.ok();
    }
}

