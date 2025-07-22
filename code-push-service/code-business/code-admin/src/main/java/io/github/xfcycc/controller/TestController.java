package io.github.xfcycc.controller;

import io.github.xfcycc.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试接口
     *
     * @return 测试结果
     */
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello, Code Push!");
    }

} 