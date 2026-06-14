package com.ar.aitestcasegenerator.service;

import com.ar.aitestcasegenerator.dto.GeneratedTestCaseItemResponse;
import com.ar.aitestcasegenerator.dto.GeneratedTestCaseResponse;
import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.enums.Priority;
import com.ar.aitestcasegenerator.enums.Severity;
import com.ar.aitestcasegenerator.enums.TestCaseStatus;
import com.ar.aitestcasegenerator.enums.TestType;
import com.ar.aitestcasegenerator.repository.TestCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AIService {

    private static final Logger log = LoggerFactory.getLogger(AIService.class);

    private final TestCaseRepository testCaseRepository;

    public AIService(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    @Transactional
    public GeneratedTestCaseResponse generateTestCases(String featureDescription) {
        String normalizedDescription = featureDescription.trim();
        String moduleName = toModuleName(normalizedDescription);

        List<TestCase> generatedTestCases = List.of(
                createTestCase(
                        normalizedDescription,
                        moduleName,
                        "Valid " + normalizedDescription + " workflow",
                        "1. Open the application\n2. Navigate to " + normalizedDescription
                                + "\n3. Enter valid data\n4. Submit the request",
                        "The operation should complete successfully",
                        TestType.POSITIVE,
                        Priority.HIGH,
                        Severity.HIGH),
                createTestCase(
                        normalizedDescription,
                        moduleName,
                        "Invalid data for " + normalizedDescription,
                        "1. Open " + normalizedDescription
                                + "\n2. Enter invalid data\n3. Submit the request",
                        "The operation should be rejected with a clear error message",
                        TestType.NEGATIVE,
                        Priority.HIGH,
                        Severity.MEDIUM),
                createTestCase(
                        normalizedDescription,
                        moduleName,
                        "Required fields are empty",
                        "1. Open " + normalizedDescription
                                + "\n2. Leave required fields empty\n3. Submit the request",
                        "Required-field validation messages should be displayed",
                        TestType.VALIDATION,
                        Priority.HIGH,
                        Severity.MEDIUM),
                createTestCase(
                        normalizedDescription,
                        moduleName,
                        "Maximum input length boundary",
                        "1. Open " + normalizedDescription
                                + "\n2. Enter data at the maximum allowed length\n3. Submit the request",
                        "The maximum allowed input should be accepted without truncation",
                        TestType.BOUNDARY,
                        Priority.MEDIUM,
                        Severity.MEDIUM),
                createTestCase(
                        normalizedDescription,
                        moduleName,
                        "Very long input beyond the allowed limit",
                        "1. Open " + normalizedDescription
                                + "\n2. Enter data longer than the allowed limit\n3. Submit the request",
                        "The application should reject excess input without crashing",
                        TestType.EDGE,
                        Priority.MEDIUM,
                        Severity.HIGH),
                createTestCase(
                        normalizedDescription,
                        moduleName,
                        "SQL injection attempt",
                        "1. Open " + normalizedDescription
                                + "\n2. Enter ' OR '1'='1 in an input field\n3. Submit the request",
                        "The input should be treated as text and no unauthorized access should occur",
                        TestType.SECURITY,
                        Priority.HIGH,
                        Severity.CRITICAL),
                createTestCase(
                        normalizedDescription,
                        moduleName,
                        "Repeat previously successful workflow",
                        "1. Complete the valid workflow\n2. Repeat the same workflow\n3. Verify the result",
                        "Existing functionality should continue to work consistently",
                        TestType.REGRESSION,
                        Priority.MEDIUM,
                        Severity.MEDIUM));

        List<TestCase> savedTestCases = testCaseRepository.saveAll(generatedTestCases);
        log.info(
                "Generated and saved {} test cases for feature '{}'",
                savedTestCases.size(),
                normalizedDescription);

        List<GeneratedTestCaseItemResponse> responseItems = savedTestCases.stream()
                .map(GeneratedTestCaseItemResponse::new)
                .toList();
        return new GeneratedTestCaseResponse(normalizedDescription, responseItems);
    }

    private TestCase createTestCase(
            String featureName,
            String moduleName,
            String scenario,
            String steps,
            String expectedResult,
            TestType testType,
            Priority priority,
            Severity severity) {
        TestCase testCase = new TestCase();
        testCase.setFeatureName(featureName);
        testCase.setModuleName(moduleName);
        testCase.setTestScenario(scenario);
        testCase.setTestSteps(steps);
        testCase.setExpectedResult(expectedResult);
        testCase.setTestType(testType);
        testCase.setPriority(priority);
        testCase.setStatus(TestCaseStatus.OPEN);
        testCase.setSeverity(severity);
        return testCase;
    }

    private String toModuleName(String featureDescription) {
        return featureDescription.length() <= 150
                ? featureDescription
                : featureDescription.substring(0, 150);
    }
}
