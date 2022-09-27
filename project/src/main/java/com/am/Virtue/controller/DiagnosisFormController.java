package com.am.Virtue.controller;

import com.am.Virtue.Service.*;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.DiagnosisFormRepo;
import com.am.Virtue.resources.DiagnosisFormResource;
import com.am.Virtue.resources.QuestionsResource;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/Form")
public class DiagnosisFormController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private OperationLanguageService languageService;
    @Autowired
    private SystemMessageService systemMessageService;
    @Autowired
    private SystemMessageService messageService;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SystemMessageCaptionService systemMessageCaptionService;
    @Autowired
    OperationLanguageService operationLanguageService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private AppointmentsService appointmentsService;
    @Autowired
    private DiagnosisFormRepo diagnosisFormRepo;
    @Autowired
    private QuestionsSerivce questionsSerivce;
    //TODO badly coded Fix
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createNewUser(HttpServletRequest request, @RequestBody DiagnosisFormResource diagnosisFormResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (
//                diagnosisFormResource.getId() == null ||
                diagnosisFormResource.getQ1() == null ||
                        diagnosisFormResource.getQ2() == null ||
                        diagnosisFormResource.getQ3() == null ||
                        diagnosisFormResource.getQ4() == null ||
                        diagnosisFormResource.getQ5() == null ||
                        diagnosisFormResource.getQ6() == null ||
                        diagnosisFormResource.getQ7() == null ||
                        diagnosisFormResource.getQ8() == null ||
                        diagnosisFormResource.getQ9() == null ||
                        diagnosisFormResource.getQ10() == null ||
                        diagnosisFormResource.getQ11() == null ||
                        diagnosisFormResource.getQ12() == null ||
                        diagnosisFormResource.getQ13() == null ||
                        diagnosisFormResource.getQ14() == null ||
                        diagnosisFormResource.getQ15() == null ||
                        diagnosisFormResource.getQ16() == null ||
                        diagnosisFormResource.getQ17() == null ||
                        diagnosisFormResource.getQ18() == null ||
                        diagnosisFormResource.getQ19() == null ||
                        diagnosisFormResource.getQ20() == null ||
                        diagnosisFormResource.getQ21() == null ||
                        diagnosisFormResource.getQ22() == null ||
                        diagnosisFormResource.getQ23() == null ||
                        diagnosisFormResource.getQ24() == null ||
                        diagnosisFormResource.getDiagnosis() == null

        )
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());

        //TODO  diagnosisFormResource.getQ1().forEach( (n) -> { ; } );
        //TODO make this dynamic
        Questions questions = new Questions();
        Long sum = 0L;
        for (Long i = 1L; i < 24L; i++) {
            questions = questionsSerivce.findQuestionsById(i);
            sum += questions.getRating();
        }

        Long pointSum = 0L;
        pointSum += diagnosisFormResource.getQ1();
        pointSum += diagnosisFormResource.getQ2();
        pointSum += diagnosisFormResource.getQ3();
        pointSum += diagnosisFormResource.getQ4();
        pointSum += diagnosisFormResource.getQ5();
        pointSum += diagnosisFormResource.getQ6();
        pointSum += diagnosisFormResource.getQ7();
        pointSum += diagnosisFormResource.getQ8();
        pointSum += diagnosisFormResource.getQ9();
        pointSum += diagnosisFormResource.getQ10();
        pointSum += diagnosisFormResource.getQ11();
        pointSum += diagnosisFormResource.getQ12();
        pointSum += diagnosisFormResource.getQ13();
        pointSum += diagnosisFormResource.getQ14();
        pointSum += diagnosisFormResource.getQ15();
        pointSum += diagnosisFormResource.getQ16();
        pointSum += diagnosisFormResource.getQ17();
        pointSum += diagnosisFormResource.getQ18();
        pointSum += diagnosisFormResource.getQ19();
        pointSum += diagnosisFormResource.getQ20();
        pointSum += diagnosisFormResource.getQ21();
        pointSum += diagnosisFormResource.getQ22();
        pointSum += diagnosisFormResource.getQ23();
        pointSum += diagnosisFormResource.getQ24();

        if (pointSum <= 20)
            diagnosisFormResource.setDiagnosis("Mild");
        else if (pointSum > 20 || pointSum < 40)
            diagnosisFormResource.setDiagnosis("Mild/Severe");
        else if (pointSum < 40)
            diagnosisFormResource.setDiagnosis("Severe");
        DiagnosisForm diagnosisForm = diagnosisFormResource.toDiagnosisForm();
        if (diagnosisForm.getDiagnosis() == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "Diagnoses400", operationLanguage.getCode());
        }
        diagnosisFormRepo.save(diagnosisForm);


        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, "200"), HttpStatus.CREATED);
    }

}
