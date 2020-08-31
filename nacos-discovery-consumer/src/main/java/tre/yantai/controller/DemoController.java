package tre.yantai.controller;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class DemoController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 这种方法，RestTemplate 不需要 @LoadBalanced 注解
     * @param name
     * @return
     */
    @GetMapping("/test")
    public String test(String name){
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/demo?name="+name, String.class);
    }

    /**
     * 这种方法，RestTemplate 需要加上 @LoadBalanced 注解
     * cmd测试负载均衡：for /l %i in (1,1,10) do curl "http://192.168.3.9:8070/test1?name=shaohui"
     * @param name
     * @return
     */
    @GetMapping("/test1")
    public String test1(String name){
        return restTemplate.getForObject("http://nacos-discovery-provider/demo?name="+name, String.class);
    }

    /**
     * 元数据
     * 这种方法，RestTemplate 不需要 @LoadBalanced 注解
     * @param name
     * @return
     */
    @GetMapping("/test2")
    public String test2(String name){
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        URI uri = serviceInstance.getUri();
        RibbonLoadBalancerClient.RibbonServer ribbonServer = (RibbonLoadBalancerClient.RibbonServer) serviceInstance;
        NacosServer nacosServer = (NacosServer) ribbonServer.getServer();
        System.out.println("--> " + nacosServer.getMetadata());
        return restTemplate.getForObject(uri+"/demo?name="+name, String.class);
    }
}
