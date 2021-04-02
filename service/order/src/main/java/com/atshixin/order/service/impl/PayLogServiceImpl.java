package com.atshixin.order.service.impl;

import com.atshixin.order.entity.PayLog;
import com.atshixin.order.mapper.PayLogMapper;
import com.atshixin.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {
    @Override
    public Map<String, Object> createPaymentQRCodeByNo(String orderNo) {
        return null;
    }

    @Override
    public Map<String, String> getPaymentStatusByNo(String orderNo) {
        return null;
    }
}
