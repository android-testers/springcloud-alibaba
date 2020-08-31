package tre.yantai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/demo")
public class DemoController {
    @Value("server.port")
    private String port;

    @GetMapping("/test1")
    public String test1(String name){
        return "Hello " + name + ":" + port;
    }
}
