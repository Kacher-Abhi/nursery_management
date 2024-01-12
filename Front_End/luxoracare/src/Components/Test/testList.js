import React, { useState, useEffect } from "react";
import TestService from "./testService";
import "./testList.css"; // Import CSS file for styling

const TestList = () => {
  const [tests, setTests] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await TestService.getTests();
        setTests(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  const handleUpdateClick = (testId) => {
    console.log(`Update clicked for admin ID: ${testId}`);
    // Implement the logic for updating an admin
  };

  const handleDeleteClick = (testId, nurseryId) => {
    console.log(
      `Delete clicked for admin ID: ${testId} , Nursery ID: ${nurseryId}`
    );
    // Implement the logic for deleting an admin
  };

  return (
    <>
      <h2>Test List</h2>
      <div className="test-list-container">
        <table className="admin-table">
          <thead>
            <tr>
              <th>Test Name</th>
              <th>Test Result</th>
              <th>Patient ID</th>
              <th>Caretaker ID</th>
              <th>Test Data</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {tests.map((test) => (
              <tr key={test.testId}>
                <td>{test.testName}</td>
                <td>{test.result}</td>
                <td>{test.patient.patientId}</td>
                <td>{test.caretaker.caretakerId} </td>
               <td> <img
              src={`data:image/png;base64, ${test.image}`}
              alt="Nursery Logo"
              className="nursery-logo"
            /></td>
                <td>
                  <button
                    className="update-btn"
                    onClick={() => handleUpdateClick(test.testId)}
                  >
                    Update
                  </button>
                  <button
                    className="delete-btn"
                    onClick={() =>
                      handleDeleteClick(test.testId, test.nursery.nurseryId)
                    }
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};

export default TestList;
