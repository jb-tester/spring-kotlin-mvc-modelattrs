package com.mytests.spring.springkotlinmvcmodelattrs

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
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
    fun example1(modelMap: ModelMap): String {
        modelMap["attributeName"] = "value"
        modelMap.addAttribute("attributeName", "value")
        modelMap.addAllAttributes(mapOf("attr1" to "value1", "attr2" to "value2"))
        modelMap.addAllAttributes(listOf(Pair("attr3", "value3"), Pair("attr4", "value4")))
        return "viewName"
    }
    @GetMapping("/example11")
    fun test0(model: Model):String{
        model.asMap()["attr111"] = "attr111"
        model.addAttribute("attr112", "attr112")
        model["attr113"] = "attr113"
        model.addAllAttributes(mapOf("attr1" to "value1", "attr2" to "value2"))
        model.addAllAttributes(listOf(Pair("attr3", "value3"), Pair("attr4", "value4")))
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
