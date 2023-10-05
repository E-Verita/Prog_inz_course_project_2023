package lv.venta.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    private final LocaleResolver localeResolver;

    public TestController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        Locale currentLocale = localeResolver.resolveLocale(request);
        System.out.println("Current Locale: " + currentLocale);
        return "hello";
    }
}