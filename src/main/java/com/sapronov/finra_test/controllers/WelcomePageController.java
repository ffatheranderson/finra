package com.sapronov.finra_test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fa on 08/12/17.
 */
@Controller
public class WelcomePageController {


    @RequestMapping("/")
    public ModelAndView getUploadNewFilePage() {
        return new ModelAndView("upload-file");
    }

}
