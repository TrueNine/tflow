package io.tn.oss.controller;


import io.tn.core.Optimization;
import io.tn.core.res.BizRes;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("")
public class HelloController {
    @GetMapping("t")
    @ResponseBody
    Object ac() {
        return Map.of("a", 1, "b", "2");
    }

    /**
     * 注释内容
     *
     * @param m args
     * @return abc
     */
    @GetMapping("")
    String home(Model m) {
        m.addAttribute("user", "Bob");
        // 试试渲染引擎
        /* 试试渲染引擎
         *
         * */
        return "hello";
    }

    @GetMapping("dow")
    void dow(HttpServletResponse response) throws IOException {
        var res = BizRes.res("application.yaml");
        log.info(res.toString());
        var ins = BizRes.resAsInputStream("application.yaml");
        log.info(new String(ins.readAllBytes()));
        ins.close();

        var fi = BizRes.resAsBytes("application.yaml");
        log.info("{}", new String(fi));
    }

    @GetMapping("data")
    Object accept() {
        Optimization.oplz(() -> {
        }, 20000);
        return "获取到数据";
    }
}
