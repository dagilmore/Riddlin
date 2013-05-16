package com.riddlin.app.controller.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;

public class AbstractPublicPageController extends AbstractPageController{
    public static final int RECENT_POST_COUNT = 4;
    
    public AbstractPublicPageController(){
    }

    @ModelAttribute("pageVisit")
    public long addPageVisit() {
        return counterService.getVisitCount();
    }

}
