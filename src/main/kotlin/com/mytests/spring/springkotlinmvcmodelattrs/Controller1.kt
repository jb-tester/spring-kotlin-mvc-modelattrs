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

    @ModelAttribute("classLevelAttr")
    fun addAttribute(): String {
        // ok:
        return "classLevelAttrValue"
    }

    @GetMapping("/example0")
    fun example0(): String {
        return "example0"
    }
    @GetMapping("/example1")
    fun example1(modelMap: ModelMap): String {
        // ok:
        modelMap["ex1_attr00"] = "ex1_value00"
        // ok:
        modelMap.addAttribute("ex1_attr0", "ex1_value0")
        // these attributes are not resolved:
        modelMap.addAllAttributes(mapOf("ex1_attr1" to "ex1_value1", "ex1_attr2" to "ex1_value2"))
        println("ModelMap attributes: ${modelMap.keys.joinToString(", ")}")
        println("ModelMap attributes: ${modelMap.values.joinToString(", ")}")
        return "example1"
    }

    @GetMapping("/example2")
    fun example2(): ModelAndView {
        val modelAndView = ModelAndView("example2")
        // ok:
        modelAndView.addObject("ex2_attr1", "ex2_attr1Value")
        return modelAndView
    }

    @GetMapping("/example3")
    fun example3(mutableMap: MutableMap<String, Any>): String {
        // this attribute is not resolved:
        mutableMap["ex3_attr1"] = "ex3_attr1Value"
        return "example3"
    }
    @GetMapping("/example4")
    fun example4(model: Model):String{
        // this attribute is not resolved:
        model.asMap()["ex4_attr1"] = "ex4_attr1Value"
        // ok:
        model.addAttribute("ex4_attr2", "ex4_attr2Value")
        // ok:
        model["ex4_attr3"] = "ex4_attr3Value"
        // these attributes are not resolved:
        model.addAllAttributes(mapOf("ex4_attr4" to "ex4_attr4Value", "ex4_attr5" to "ex4_attr5Value"))
        println("ModelMap attributes: ${model.asMap().keys.joinToString(", ")}")
        println("ModelMap attributes: ${model.asMap().values.joinToString(", ")}")
        return "example4"
    }
    // the correct HTTP request is not generated:
   // GET http://localhost:8080/example5?ex5_attr1=foo
    @GetMapping("/example5")
    fun example5(@ModelAttribute("ex5_attr1") attribute: String): String {
        println("Attribute value: $attribute")
        return "example5"
    }

    data class MyModel(val id: Int, val name: String)

    // the view is not resolved, the model attribute there is also n/r obviously:
    // https://youtrack.jetbrains.com/issue/IDEA-376027/Spring-MVC-implicit-view-name-is-not-resolved
    @ModelAttribute("myModel")
    @GetMapping("/example6")
    fun example6(): MyModel {
        return MyModel(1, "example6")
    }

    @GetMapping("/example7")
    fun example7(redirectAttributes: RedirectAttributes): String {
        // this attribute is not resolved:
        redirectAttributes["ex7_attr1"] = "ex7_attr1Value"
        redirectAttributes.addFlashAttribute("ex7_flashAttribute", "ex7_flashValue")
        return "redirect:/anotherEndpoint"
    }
    @GetMapping("/anotherEndpoint")
    fun another(): String {
        return "example7"
    }
}
