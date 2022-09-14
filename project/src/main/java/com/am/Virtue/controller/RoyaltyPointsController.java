package com.am.Virtue.controller;

import com.am.Virtue.Service.OTPService;
import com.am.Virtue.Service.RoyaltyPointsService;
import com.am.Virtue.Service.SystemMessageCaptionService;
import com.am.Virtue.Service.SystemMessageService;
import com.am.Virtue.entities.RoyaltyPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/Rylty")
public class RoyaltyPointsController {
  //  @Autowired
 //   private RoyaltyPointsService ryltyService;

    @Autowired
    private SystemMessageService messageService;

    @Autowired
    private SystemMessageCaptionService systemMessageCaptionService;

}
