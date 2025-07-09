package com.mytests.spring.springkotlinmvcmodelattrs

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
@RequestMapping("/")
class Controller1 {

    @ModelAttribute("attributeName")
    fun addAttribute(): String {
        return "value"
    }

    @GetMapping("/example0")
    fun example0(): String {
        return "viewName"
    }
    @GetMapping("/example1")
    fun example1(model: Model): String {
        model.addAttribute("attributeName", "value")
        return "viewName" // return the name of the view
    }
    @GetMapping("/example11")
    fun test0(model: Model):String{
        model.asMap()["attr01"] = "attr01"
        model.addAttribute("attr02", "attr02")
        return "test0"
    }
    @GetMapping("/example2")
    fun example2(): ModelAndView {
        val modelAndView = ModelAndView("viewName")
        modelAndView.addObject("attributeName", "value")
        return modelAndView
    }

    @GetMapping("/example3")
    fun example3(modelMap: MutableMap<String, Any>): String {
        modelMap["attributeName"] = "value"
        return "viewName"
    }


    @GetMapping("/example5")
    fun example5(@ModelAttribute("attributeName") attribute: String): String {
        println("Attribute value: $attribute")
        return "viewName"
    }

    data class MyModel(val id: Int, val name: String)

    @GetMapping("/example6")
    fun example6(): MyModel {
        return MyModel(1, "Kotlin Example")
    }

    @GetMapping("/example7")
    fun example7(redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("flashAttribute", "flashValue")
        return "redirect:/anotherEndpoint"
    }

    @GetMapping("/anotherEndpoint")
    fun another(): String {
        return "viewName"
    }
}
