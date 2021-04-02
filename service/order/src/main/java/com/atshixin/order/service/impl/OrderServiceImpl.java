package com.atshixin.order.service.impl;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.base.types.CallTypes;
import com.atshixin.order.client.EduFeignClient;
import com.atshixin.order.client.UserCenterFeignClient;
import com.atshixin.order.entity.Order;
import com.atshixin.order.mapper.OrderMapper;
import com.atshixin.order.service.OrderService;
import com.atshixin.order.util.OrderNoGenerator;
import com.atshixin.util.R;
import com.atshixin.util.vo.CourseVo;
import com.atshixin.util.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-03-31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduFeignClient eduFeignClient;

    @Autowired
    private UserCenterFeignClient userCenterFeignClient;

    // 创建订单
    @Override
    public String createOrder(String userId, String courseId) {
        // RPC获取课程和用户信息
        R courseR = eduFeignClient.getCourseById(courseId, CallTypes.RPC);
        R userInfoR = userCenterFeignClient.getUserInfoById(userId);

        Map<String, Object> courseRecord = getRecord(courseR);
        Map<String, Object> userInfoRecord = getRecord(userInfoR);

        if (courseRecord == null || userInfoRecord == null) {
            throw new GuliException(200001, "远程调用获取数据为空: userId -> " + userId + "; courseId -> " + courseId);
        }

        Order order = new Order();
        order.setOrderNo(OrderNoGenerator.generate());

        // 设置课程信息
        order.setCourseId(courseId);
        order.setCourseTitle(courseRecord.get("title").toString());
        order.setCourseCover(courseRecord.get("cover").toString());
        order.setTeacherName(courseRecord.get("teacherName").toString());
        order.setTotalFee(new BigDecimal(String.valueOf(courseRecord.get("price"))));

        // 设置用户信息
        order.setMemberId(userId);
        order.setNickname(userInfoRecord.get("nickname").toString());
        order.setMobile(userInfoRecord.get("mobile").toString());
        order.setPayType(1);
        order.setStatus(0);

        save(order);

        return order.getOrderNo();
    }

    @Override
    public Order getOrderByNo(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);

        return getOne(queryWrapper);
    }

    private Map<String, Object> getRecord(R r) {
        Object record = r.getData().get("record");
        return (Map<String, Object>) record;
    }
}
