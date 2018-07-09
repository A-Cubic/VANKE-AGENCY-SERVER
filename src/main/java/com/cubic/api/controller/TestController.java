package com.cubic.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasAuthority('user:add')")
    @GetMapping
    public String test() {
    	return "a";
    }

}
