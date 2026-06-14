package com.ar.aitestcasegenerator.service;

import com.ar.aitestcasegenerator.dto.GeneratedTestCaseResponse;
import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.repository.TestCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private static final Logger log = LoggerFactory.getLogger(AIService.class);

    private final TestCaseRepository testCaseRepository;

    public AIService(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    public GeneratedTestCaseResponse generateTestCases(String featureDescription) {
        String normalizedDescription = featureDescription.trim();
        GeneratedTestCaseResponse response =
                new GeneratedTestCaseResponse();

        response.setFeatureName(normalizedDescription);

        response.setTestScenario(
                "Verify functionality of " + normalizedDescription);

        response.setTestSteps(
                "1. Open application\n" +
                        "2. Navigate to feature\n" +
                        "3. Perform required action");

        response.setExpectedResult(
                "Feature should work successfully");

        TestCase testCase = new TestCase();

        testCase.setFeatureName(response.getFeatureName());
        testCase.setTestScenario(response.getTestScenario());
        testCase.setTestSteps(response.getTestSteps());
        testCase.setExpectedResult(response.getExpectedResult());

        TestCase savedTestCase = testCaseRepository.save(testCase);
        log.info(
                "Generated and saved test case with id {} for feature '{}'",
                savedTestCase.getId(),
                normalizedDescription);

        return response;
    }
}
