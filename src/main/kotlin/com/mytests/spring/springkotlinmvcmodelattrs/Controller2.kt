package com.mytests.spring.springkotlinmvcmodelattrs

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.util.HashMap


@Controller
class Controller2 {



    @ModelAttribute(name = "common_modelAttr0")
    fun common() = "single model attribute added via @ModelAttribute-annotated method"

    @ModelAttribute()
    fun multipleAttr(model: Model){
        model.addAttribute("common_multi_attr1","first of multiple model attributes added via @ModelAttribute-annotated method with Model-type parameter")
        model["common_multi_attr2"] = "second of multiple model attributes added via @ModelAttribute-annotated method with Model-type parameter"
    }

    @ModelAttribute
    fun populateCommonModelWithMap(model: Model) {
        val myMap = HashMap<String, String>()
        myMap["common_map_attr1"] = "first attribute added using addAllAttributes(Map) in @MethodAttribute-annotated method with Model-type parameter"
        myMap["common_map_attr2"] = "second attribute added using addAllAttributes(Map) in @MethodAttribute-annotated method with Model-type parameter"
        model.addAllAttributes(myMap)
    }

    @GetMapping("/")
    fun home() = "home"

    @RequestMapping("/test1")
    fun test1(model: Model): String {
        // ok:
        model["test1_attr1"] = "mapping method-level attribute added using kt shortcut"
        // ok:
        model.addAttribute("test1_attr2","mapping method-level attribute added using addAttribute()")
        val myMap1 = HashMap<String, String>()
        // this attribute is not resolved:
        myMap1.put("map1_attr1","first mapping-level attribute added using addAllAttributes(Map)")
        // ok:
        myMap1["map1_attr2"] = "second mapping-level attribute added using addAllAttributes(Map)"
        model.addAllAttributes(myMap1)
        return "test1_page"
    }

    // view is not resolved, the model attribute there is also n/r obviously:
    @GetMapping("/test2")
    @ModelAttribute("test2Data")
    fun handleTest2(): DataClass {

        return DataClass("model attribute added as @ModelAttribute annotation on request mapping method", 100)
    }

    // ok:
    @RequestMapping("/test3")
    fun test3(@ModelAttribute(name = "utilCompoModelAttribute") compo :UtilComponent): String {
        return "test3_page"
    }

    @RequestMapping("/test4")
    fun test4(): ModelAndView {


        val modelAndView = ModelAndView()
        //val modelAndView = ModelAndView("test4_page")
        // ok:
        modelAndView.modelMap["test4_attr1"] = "mapping method-level attribute added using kt shortcut"
        // ok:
        modelAndView.addObject("test4_attr2","mapping method-level attribute added using addObject()")
        val myMap1 = HashMap<String, String>()
        // ok:
        myMap1.put("map4_attr1","first mapping-level attribute added using addAllObjects(Map)")
        // this attribute is not resolved:
        myMap1["map4_attr2"] = "a second mapping-level attribute is added using addAllObjects(Map)"
        modelAndView.addAllObjects(myMap1)
        // this attribute is not resolved:
        modelAndView.model["model4_attr1"] = "mapping-level attribute added as mav.model[]"
        // ok:
        modelAndView.model.put("model4_attr2","mapping-level attribute added as mav.model.put")
        modelAndView.viewName = "test4_page"
        return modelAndView

    }
}