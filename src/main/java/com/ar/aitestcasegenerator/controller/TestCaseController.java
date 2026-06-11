package com.ar.aitestcasegenerator.controller;
import org.springframework.web.bind.annotation.*;
import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping
    public TestCase saveTestCase(@RequestBody TestCase testCase) {
        return testCaseService.saveTestCase(testCase);
    }
    @GetMapping
    public List<TestCase> getAllTestCases() {
        return testCaseService.getAllTestCases();
    }
    @GetMapping("/{id}")
    public TestCase getTestCaseById(@PathVariable Long id) {
        return testCaseService.getTestCaseById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteTestCase(@PathVariable Long id) {
        testCaseService.deleteTestCase(id);
        return "Test Case Deleted Successfully";
    }
    @PutMapping("/{id}")
    public TestCase updateTestCase(
            @PathVariable Long id,
            @RequestBody TestCase testCase) {

        return testCaseService.updateTestCase(id, testCase);
    }
}