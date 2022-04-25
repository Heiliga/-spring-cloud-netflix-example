package net.devh.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import net.devh.feign.ServiceBClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 2016/6/3
 */
@Service
public class HystrixWrappedServiceBClient {

    @Autowired
    private ServiceBClient serviceBClient;


    @HystrixCommand(groupKey = "helloGroup", fallbackMethod = "fallBackCall")
    public String printServiceB() {
        return serviceBClient.printServiceB();
    }

    @HystrixCommand(groupKey = "guidGroup",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
            },
            fallbackMethod = "fallBackCall")
    public ResponseEntity<?> getGuid(@RequestParam(name="guid") UUID guid) {
        return serviceBClient.getGuid(guid);
    }

    public String fallBackCall() {
        return "FAILED SERVICE B CALL! - FALLING BACK";
    }
}
