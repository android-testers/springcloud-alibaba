package tre.yantai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tre.yantai.service.DemoFeignService;

@RestController
public class DemoController {
    @Autowired
    private DemoFeignService demoFeignService;

    @GetMapping("/test1")
    public String test1(String name){
        return demoFeignService.demo(name);
    }
}
