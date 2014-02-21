package me.risky.web;

import me.risky.domain.DownloadExample;
import me.risky.orm.DownloadMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chenupt@gmail.com on 14-2-21.
 * Description : TODO
 */

@Controller
@RequestMapping("main")
public class MainController {

    @Autowired
    DownloadMapper downloadMapper;

    private static final Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping("test")
    protected ModelAndView sayHello(ModelAndView mav){
        logger.info("This is a Test");
        mav.setViewName("Test");
        return mav;
    }

    @RequestMapping("data")
    protected ModelAndView testDB(ModelAndView mav){
        downloadMapper.selectByExample(new DownloadExample());
        logger.info("data");
        mav.setViewName("Test");
        return mav;
    }





}
