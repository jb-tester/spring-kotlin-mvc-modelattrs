package com.mytests.spring.springkotlinmvcmodelattrs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.util.HashMap

/**
 ********************************
 * Created by Irina.Petrovskaya on 9/28/2018.
 * Project: mvc-kotlin-test0
 ********************************
 */
@Controller
class MyController {



    @ModelAttribute(name = "commonModelAttr")
    fun common() = "single model attribute added via @ModelAttribute-annotated method"

    @ModelAttribute()
    fun multipleAttr(model: Model){
        model.addAttribute("multi_attr1","first of multiple model attributes added via @ModelAttribute-annotated method with Model-type parameter")
        model["multi_attr2"] = "second of multiple model attributes added via @ModelAttribute-annotated method with Model-type parameter"
    }

    @ModelAttribute
    fun populateCommonModelWithMap(model: Model) {
        val myMap = HashMap<String, String>()
        myMap["map_attr1"] = "first attribute added using addAllAttributes(Map) in @MethodAttribute-annotated method with Model-type parameter"
        myMap["map_attr2"] = "second attribute added using addAllAttributes(Map) in @MethodAttribute-annotated method with Model-type parameter"
        model.addAllAttributes(myMap)
    }

    @GetMapping("/")
    fun home() = "home"

    @RequestMapping("/test1")
    fun test1(model: Model): String {
        model["test1_attr1"] = "mapping method-level attribute added using kt shortcut"
        model.addAttribute("test1_attr2","mapping method-level attribute added using addAttribute()")
        val myMap1 = HashMap<String, String>()
        myMap1.put("map1_attr1","first mapping-level attribute added using addAllAttributes(Map)")
        myMap1["map1_attr2"] = "second mapping-level attribute added using addAllAttributes(Map)"
        model.addAllAttributes(myMap1)
        return "test1_page"
    }

    @GetMapping("/test2")
    @ModelAttribute("myData")
    fun handleTest2(): DataClass {

        return DataClass("model attribute added as @ModelAttribute annotation on request mapping method", 100)
    }

    @RequestMapping("/test3")
    fun test3(@ModelAttribute(name = "utilCompoModelAttribute") compo :UtilComponent): String {
        return "test3_page"
    }

    @RequestMapping("/test4")
    fun test4(): ModelAndView {


        val modelAndView = ModelAndView()
        //val modelAndView = ModelAndView("test4_page")
        modelAndView.modelMap["test4_attr1"] = "mapping method-level attribute added using kt shortcut"
        modelAndView.addObject("test4_attr2","mapping method-level attribute added using addObject()")
        val myMap1 = HashMap<String, String>()
        myMap1.put("map4_attr1","first mapping-level attribute added using addAllObjects(Map)")
        myMap1["map4_attr2"] = "second mapping-level attribute added using addAllObjects(Map)"
        modelAndView.addAllObjects(myMap1)
        modelAndView.model["model4_attr1"] = "mapping-level attribute added as mav.model[]"
        modelAndView.model.put("model4_attr2","mapping-level attribute added as mav.model.put")
        modelAndView.viewName = "test4_page"
        return modelAndView
        /*
        TODO - add comments with links to youtrack issues;
         group and comment all not resolved model attributes definitions
        */
    }
}