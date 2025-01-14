package ru.aveskin.twitter.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/usual-auth")
    public String hitUsualAuth() {
        return "this is protected resource";
    }

    @GetMapping("/role-user-auth")
    public String hitUserAuth() {
        return "this is user protected resource";
    }

    @GetMapping("/role-admin-auth")
    public String hitAdminAuth() {
        return "this is admin protected resource";
    }

}
