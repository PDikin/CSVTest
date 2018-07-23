package csvtest.controller;

import csvtest.csvWorker.CsvParser;
import csvtest.service.impl.MyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Controller
public class MyController {

    @Autowired
    MyServiceImpl myService;

    @Autowired
    CsvParser csvParser;

    @RequestMapping("/parse")
    public ModelAndView parse(ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("indexBar");

        csvParser.readWithCsvMapReader("test.csv");

        return modelAndView;
    }


    @RequestMapping("/formStatus")
    public ModelAndView formStatus(ModelAndView modelAndView) throws Exception {
        Map<String, String> formStatus = myService.formStatus();
        modelAndView.addObject("formStatus", formStatus);
        modelAndView.setViewName("formStatus");
        return modelAndView;
    }

    @RequestMapping("/top")
    public ModelAndView top(ModelAndView modelAndView) throws Exception {
        Map<String, Integer> topFive = myService.topFive();
        modelAndView.addObject("topFive", topFive);
        modelAndView.setViewName("top");
        return modelAndView;
    }

    @RequestMapping("/userAndForm")
    public ModelAndView userAndForm(ModelAndView modelAndView) throws Exception {
        Map<String, String> userFrom = myService.userAndForm();
        modelAndView.addObject("userForm", userFrom);
        modelAndView.setViewName("userAndForm");
        return modelAndView;
    }


//    2017-07-11-09
    @ResponseBody
    @RequestMapping("/test")
    public Date test() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd-hh");
        Date docDate= format.parse("2017-07-11-09");
        return docDate;
    }

}
