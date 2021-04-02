package com.atshixin.order.service;

import java.util.Map;

public interface PayLogService {
    Map<String, Object> createPaymentQRCodeByNo(String orderNo);
    Map<String, String> getPaymentStatusByNo(String orderNo);
}
