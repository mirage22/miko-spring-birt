package com.miko.demo.birt.controller;

import com.miko.demo.birt.core.BirtEngineFactory;
import com.miko.demo.birt.core.BirtView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: miroslavkopecky
 * Date: 5/23/14
 */
@Controller
@RequestMapping("/reports")
public class BirtController implements ApplicationContextAware {


    private final Logger logger = LoggerFactory.getLogger(BirtController.class);


    private ApplicationContext context ;

    @RequestMapping(method = RequestMethod.GET)
    public BirtView testRequest(HttpServletRequest request, HttpServletResponse response){

        logger.debug("BIRT response");
        logger.debug("BIRT test :)");

        logger.debug("MV Created");

        return birtView();
    }

    @Bean
    public BirtView birtView(){

        logger.debug("birtView START");

        BirtView bv = new BirtView();
        bv.setReportFormatRequestParameter("ReportFormat");
        //bv.setReportNameRequestParameter("ReportName");
        bv.setBirtEngine(engine().getObject());
        return bv;
    }

    @Bean
    protected BirtEngineFactory engine(){
        BirtEngineFactory factory = new BirtEngineFactory() ;
        factory.setApplicationContext(context);
        //factory.setLogLevel( Level.FINEST);
        //factory.setLogDirectory ( new File ("c:/logs"));
        //factory.setLogDirectory( new FileSystemResource("/logs"));

        logger.debug("engine CALLED");

        return factory ;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
    }
}