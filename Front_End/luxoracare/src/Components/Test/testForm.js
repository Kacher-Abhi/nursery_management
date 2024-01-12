import React, { useState } from "react";
import TestService from "./testService";
// import "./style.css";

const TestForm = () => {
  const [testData, setTestData] = useState({
    testName: "",
    result: "",
    testTakenDate: "",
    testTakenTime: "",
    nurseryId: "",
    patientId: "",
    caretakerId: "",
    testResult: null, // Updated to handle file upload
    prescription: null, // Updated to handle file upload
  });

  const handleChange = (e) => {
    if (e.target.type === "file") {
      setTestData({
        ...testData,
        [e.target.name]: e.target.files[0],
      });
    } else {
      setTestData({
        ...testData,
        [e.target.name]: e.target.value,
      });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      for (const key in testData) {
        formData.append(key, testData[key]);
      }

      // Assuming TestService.createTest method exists
      await TestService.createTest(formData);
      alert("Test created successfully!");
      // Optionally, reset the form or redirect to another page
      setTestData({
        testName: "",
        result: "",
        testTakenDate: "",
        testTakenTime: "",
        nurseryId: "",
        patientId: "",
        caretakerId: "",
        testResult: null,
        prescription: null,
      });
    } catch (error) {
      console.error("Error creating test:", error);
      alert("Error creating test. Please check the console for details.");
      console.log(setTestData);
    }
  };

  return (
    <>
      <h1>Test Form</h1>
      <div className="container">
        <div className="form-box">
          <form onSubmit={handleSubmit}>
            <label htmlFor="testName">Test Name:</label>
            <input
              type="text"
              id="testName"
              name="testName"
              value={testData.testName}
              onChange={handleChange}
              required
            />

            <label htmlFor="result">Result:</label>
            <input
              type="text"
              id="result"
              name="result"
              value={testData.result}
              onChange={handleChange}
              required
            />

            <label htmlFor="testTakenDate">Test Taken Date:</label>
            <input
              type="text"
              id="testTakenDate"
              name="testTakenDate"
              value={testData.testTakenDate}
              onChange={handleChange}
              required
            />

            <label htmlFor="testTakenTime">Test Taken Time:</label>
            <input
              type="text"
              id="testTakenTime"
              name="testTakenTime"
              value={testData.testTakenTime}
              onChange={handleChange}
              required
            />

            <label htmlFor="nurseryId">Nursery ID:</label>
            <input
              type="text"
              id="nurseryId"
              name="nurseryId"
              value={testData.nurseryId}
              onChange={handleChange}
              required
            />

            <label htmlFor="patientId">Patient ID:</label>
            <input
              type="text"
              id="patientId"
              name="patientId"
              value={testData.patientId}
              onChange={handleChange}
              required
            />

            <label htmlFor="caretakerId">Caretaker ID:</label>
            <input
              type="text"
              id="caretakerId"
              name="caretakerId"
              value={testData.caretakerId}
              onChange={handleChange}
              required
            />

            <label htmlFor="testResult">Test Result (Image):</label>
            <input
              type="file"
              id="testResult"
              name="testResult"
              accept="image/*"
              onChange={handleChange}
            />

            <label htmlFor="prescription">Prescription (Image):</label>
            <input
              type="file"
              id="prescription"
              name="prescription"
              accept="image/*"
              onChange={handleChange}
            />

            <button type="submit">Create Test</button>
          </form>
        </div>
      </div>
    </>
  );
};

export default TestForm;
