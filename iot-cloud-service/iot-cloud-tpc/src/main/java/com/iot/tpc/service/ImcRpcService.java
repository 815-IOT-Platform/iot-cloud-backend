package com.iot.tpc.service;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/23 18:24
 */
public interface ImcRpcService {

    List<String> queryWaitingConfirmMessageKeyList(List<String> messageKeyList);
}
