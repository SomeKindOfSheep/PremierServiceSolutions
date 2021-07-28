package com.pss.premierservicesolutions.controllers;

import com.pss.premierservicesolutions.services.CallCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class CallCentreController {

    @Autowired
    CallCentreService callCentreService;



}
