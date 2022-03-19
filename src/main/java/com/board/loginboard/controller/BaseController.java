package com.board.loginboard.controller;

import com.board.loginboard.config.auth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class BaseController {
    private final HttpSession httpSession;

    @GetMapping({"/","/index"})
    public ResponseEntity index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            //model.addAttribute("userName", user.getName());
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
