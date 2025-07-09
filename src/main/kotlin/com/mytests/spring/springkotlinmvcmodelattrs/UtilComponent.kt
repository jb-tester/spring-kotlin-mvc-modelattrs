package com.mytests.spring.springkotlinmvcmodelattrs

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class UtilComponent {

    var id: String = "model attribute added using @ModelAttribute-annotated parameter of UtilComponent type"


}