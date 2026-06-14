package com.ar.aitestcasegenerator.dto;

import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.enums.Priority;
import com.ar.aitestcasegenerator.enums.Severity;
import com.ar.aitestcasegenerator.enums.TestCaseStatus;
import com.ar.aitestcasegenerator.enums.TestType;

public class GeneratedTestCaseItemResponse {

    private final Long id;
    private final String featureName;
    private final String testScenario;
    private final String testSteps;
    private final String expectedResult;
    private final TestType testType;
    private final Priority priority;
    private final TestCaseStatus status;
    private final Severity severity;
    private final String moduleName;

    public GeneratedTestCaseItemResponse(TestCase testCase) {
        this.id = testCase.getId();
        this.featureName = testCase.getFeatureName();
        this.testScenario = testCase.getTestScenario();
        this.testSteps = testCase.getTestSteps();
        this.expectedResult = testCase.getExpectedResult();
        this.testType = testCase.getTestType();
        this.priority = testCase.getPriority();
        this.status = testCase.getStatus();
        this.severity = testCase.getSeverity();
        this.moduleName = testCase.getModuleName();
    }

    public Long getId() {
        return id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getTestScenario() {
        return testScenario;
    }

    public String getTestSteps() {
        return testSteps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public TestType getTestType() {
        return testType;
    }

    public Priority getPriority() {
        return priority;
    }

    public TestCaseStatus getStatus() {
        return status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getModuleName() {
        return moduleName;
    }
}
