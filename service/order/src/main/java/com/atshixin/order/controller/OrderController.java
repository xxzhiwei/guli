package com.atshixin.order.controller;


import com.atshixin.order.entity.Order;
import com.atshixin.order.service.OrderService;
import com.atshixin.order.service.PayLogService;
import com.atshixin.util.JWT;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Autowired
    private PayLogService payLogService;

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

    // 获取订单状态
    @GetMapping("/{outTradeNo}/status")
    public R getOrderStatusByNo(@PathVariable String outTradeNo) {

        Map<String,String> map = payLogService.getPaymentStatusByNo(outTradeNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return R.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updatePaymentStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }

    // 根据订单号生成微信支付二维码
    @GetMapping("/{orderNo}/wx/QRCode")
    public R getWechatPayQRCodeByNo(@PathVariable String orderNo) {
        Map map = payLogService.createPaymentQRCodeByNo(orderNo);
        System.out.println("****返回二维码map集合:"+map);
        return ResultHelper.format(map);
    }

    // 获取订单支付状态
    @GetMapping("/{memberId}/{courseId}/status")
    public boolean isPayOrder(@PathVariable String memberId, @PathVariable String courseId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("member_id", memberId);
        Order order = orderService.getOne(queryWrapper);
        return order != null;
    }
}

