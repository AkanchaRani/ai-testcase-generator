package com.ar.aitestcasegenerator.service;

import com.ar.aitestcasegenerator.dto.GeneratedTestCaseResponse;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    public GeneratedTestCaseResponse generateTestCases(String featureDescription) {

        GeneratedTestCaseResponse response = new GeneratedTestCaseResponse();

        response.setFeatureName(featureDescription);
        response.setTestScenario("Valid User Flow");
        response.setTestSteps("Enter valid data and submit");
        response.setExpectedResult("Operation should complete successfully");

        return response;
    }
}