package com.ar.aitestcasegenerator.dto;

import com.ar.aitestcasegenerator.enums.Priority;
import com.ar.aitestcasegenerator.enums.Severity;
import com.ar.aitestcasegenerator.enums.TestCaseStatus;
import com.ar.aitestcasegenerator.enums.TestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TestCaseRequest {

    @NotBlank(message = "Feature name is required")
    @Size(max = 150, message = "Feature name must not exceed 150 characters")
    private String featureName;

    @NotBlank(message = "Test scenario is required")
    @Size(max = 500, message = "Test scenario must not exceed 500 characters")
    private String testScenario;

    @NotBlank(message = "Test steps are required")
    @Size(max = 5000, message = "Test steps must not exceed 5000 characters")
    private String testSteps;

    @NotBlank(message = "Expected result is required")
    @Size(max = 2000, message = "Expected result must not exceed 2000 characters")
    private String expectedResult;

    @NotNull(message = "Test type is required")
    private TestType testType;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Status is required")
    private TestCaseStatus status;

    @NotNull(message = "Severity is required")
    private Severity severity;

    @NotBlank(message = "Module name is required")
    @Size(max = 150, message = "Module name must not exceed 150 characters")
    private String moduleName;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getTestScenario() {
        return testScenario;
    }

    public void setTestScenario(String testScenario) {
        this.testScenario = testScenario;
    }

    public String getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(String testSteps) {
        this.testSteps = testSteps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TestCaseStatus getStatus() {
        return status;
    }

    public void setStatus(TestCaseStatus status) {
        this.status = status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
