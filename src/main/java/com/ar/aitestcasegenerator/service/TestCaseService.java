package com.ar.aitestcasegenerator.service;

import com.ar.aitestcasegenerator.dto.TestCaseRequest;
import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.exception.ResourceNotFoundException;
import com.ar.aitestcasegenerator.repository.TestCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    private static final Logger log = LoggerFactory.getLogger(TestCaseService.class);

    private final TestCaseRepository testCaseRepository;

    public TestCaseService(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    public TestCase saveTestCase(TestCaseRequest request) {
        TestCase testCase = new TestCase();
        updateEntity(testCase, request);
        TestCase savedTestCase = testCaseRepository.save(testCase);
        log.info("Created test case with id {}", savedTestCase.getId());
        return savedTestCase;
    }

    public List<TestCase> getAllTestCases() {
        log.debug("Retrieving all test cases");
        return testCaseRepository.findAll();
    }

    public TestCase getTestCaseById(Long id) {
        return findTestCase(id);
    }

    public void deleteTestCase(Long id) {
        TestCase testCase = findTestCase(id);
        testCaseRepository.delete(testCase);
        log.info("Deleted test case with id {}", id);
    }

    public TestCase updateTestCase(Long id, TestCaseRequest request) {
        TestCase existingTestCase = findTestCase(id);
        updateEntity(existingTestCase, request);
        TestCase savedTestCase = testCaseRepository.save(existingTestCase);
        log.info("Updated test case with id {}", id);
        return savedTestCase;
    }

    private TestCase findTestCase(Long id) {
        return testCaseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Test case not found with id: " + id));
    }

    private void updateEntity(TestCase testCase, TestCaseRequest request) {
        testCase.setFeatureName(request.getFeatureName().trim());
        testCase.setTestScenario(request.getTestScenario().trim());
        testCase.setTestSteps(request.getTestSteps().trim());
        testCase.setExpectedResult(request.getExpectedResult().trim());
        testCase.setTestType(request.getTestType());
        testCase.setPriority(request.getPriority());
        testCase.setStatus(request.getStatus());
        testCase.setSeverity(request.getSeverity());
        testCase.setModuleName(request.getModuleName().trim());
    }
}
