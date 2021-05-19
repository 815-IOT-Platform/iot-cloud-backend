package com.iot.device.controller;


import com.iot.common.core.controller.BaseController;
import com.iot.device.model.domain.Node;
import com.iot.device.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("node")
@Api(value = "iot-cloud-device",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NodeController extends BaseController {

    @Autowired
    private NodeService nodeService;

    @PostMapping("addEdgeNode")
    @ApiOperation("创建边缘节点")
    public void addEdgeNode(@RequestBody Node node) {
        nodeService.createEdgeNode(node);
    }

    @GetMapping("getAllEdgeNode")
    @ApiOperation( "获取全部边缘节点")
    public List<Node> getAllDevice() {
        return nodeService.getAllEdgeNode();
    }
}
