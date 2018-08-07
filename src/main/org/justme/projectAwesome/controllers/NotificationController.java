package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.Notification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotificationController {

    @PostMapping("users/{userId}/send-message")
    public ModelAndView sendNotificationMessage(ModelAndView modelAndView,
                                                @PathVariable String userId) {
//        Notification notification = new Notification();
        modelAndView.setViewName("redirect:/users/"+userId);
        return modelAndView;
    }

    @PostMapping("users/{userId}/send-friend-request")
    public ModelAndView sendNotificationFriendRequest(ModelAndView modelAndView,
                                                      @PathVariable String userId) {

        modelAndView.setViewName("redirect:/users/"+userId);
        return modelAndView;
    }
}
