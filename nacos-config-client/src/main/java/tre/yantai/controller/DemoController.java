package tre.yantai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DemoController {
    @Value("${name:空name}")
    public String name;

    @GetMapping("/test")
    public String test(){
        return name;
    }
}
