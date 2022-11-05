package com.platinouss.bookrecommend.api;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterApiController {

    private final UserService userService;

    @PostMapping("/api/register")
    public String register(@RequestBody User user) {
        System.out.println(user.getName() + "==");
        if(!userService.exists(user.getName())) {
            userService.addUser(user);
        }

        return "success";
    }
}
