package eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-07-07
 */
@RestController
public class ConfigRegistryController
{
    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping ("/service-instance/{applicationName}")
    public List<ServiceInstance> getServiceInstance(@PathVariable String applicationName)
    {
        return discoveryClient.getInstances(applicationName);
    }


    @RequestMapping ("/hello")
    public String sayHello()
    {
        return "This is my spring cloud app!";
    }
}
