package com.ar.aitestcasegenerator.service;

import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    public TestCase saveTestCase(TestCase testCase) {
        return testCaseRepository.save(testCase);
    }
    public List<TestCase> getAllTestCases() {
        return testCaseRepository.findAll();
    }
    public TestCase getTestCaseById(Long id) {
        return testCaseRepository.findById(id).orElse(null);
    }
    public void deleteTestCase(Long id) {
        testCaseRepository.deleteById(id);
    }
    public TestCase updateTestCase(Long id, TestCase updatedTestCase) {

        TestCase existingTestCase =
                testCaseRepository.findById(id).orElse(null);

        if(existingTestCase != null) {

            existingTestCase.setFeatureName(
                    updatedTestCase.getFeatureName());

            existingTestCase.setTestScenario(
                    updatedTestCase.getTestScenario());

            existingTestCase.setTestSteps(
                    updatedTestCase.getTestSteps());

            existingTestCase.setExpectedResult(
                    updatedTestCase.getExpectedResult());

            return testCaseRepository.save(existingTestCase);
        }

        return null;
    }
}