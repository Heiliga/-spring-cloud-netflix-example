package net.devh.controller;

import net.devh.dto.JsonResponse;
import net.devh.dto.JsonResponseBuilder;
import net.devh.hystrix.HystrixWrappedServiceBClient;

import net.devh.service.GuidService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import java.util.UUID;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 2016/6/3
 */
@RefreshScope
@RestController
@Api
public class AServiceController {

    @Value("${name:unknown}")
    private String name;

    @Autowired
    private HystrixWrappedServiceBClient serviceBClient;
    @Autowired
    private Registration registration;
    @Autowired
    private GuidService guidService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printServiceA() {
        return registration.getServiceId() + " (" + registration.getHost() + ":" + registration.getPort() + ")" + "===>name:" + name + "<br/>" + serviceBClient.printServiceB();
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<UUID> getGuid() {
        return new ResponseEntity<>(guidService.getGuid(), HttpStatus.OK);
    }

    @RequestMapping(value = "/start-ping-pong", method = RequestMethod.GET)
    public JsonResponse<?> processGuid() {
        return JsonResponseBuilder.success(guidService.compareGuid());
    }
}
