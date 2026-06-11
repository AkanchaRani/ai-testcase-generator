package com.ar.aitestcasegenerator.controller;
import com.ar.aitestcasegenerator.dto.GeneratedTestCaseResponse;
import com.ar.aitestcasegenerator.dto.FeatureRequest;
import com.ar.aitestcasegenerator.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/generate")
    public GeneratedTestCaseResponse generateTestCases(
            @RequestBody FeatureRequest request) {

        return aiService.generateTestCases(
                request.getFeatureDescription()
        );

    }
}