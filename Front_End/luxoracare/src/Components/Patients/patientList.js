import React, { useState, useEffect } from "react";
import PatientService from "./patientService";
import "./patientList.css"; // Import CSS file for styling
import { Link } from "react-router-dom";

const PatientList = () => {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await PatientService.getPatient();
        setPatients(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  const handleUpdateClick = (adminId) => {
    console.log(`Update clicked for admin ID: ${adminId}`);
    // Implement the logic for updating an admin
  };

  const handleDeleteClick = (adminId, nurseryId) => {
    console.log(
      `Delete clicked for admin ID: ${adminId} , Nursery ID: ${nurseryId}`
    );
    // Implement the logic for deleting an admin
  };

  return (
    <>
      <h2>Patient List</h2>
    <div className="patient-list-container">
      <table className="admin-table">
        <thead>
          <tr>
            <th>Patient ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Careteker ID</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {patients.map((patient) => (
            <tr key={patient.patientId}>
              <td>{patient.patientId}</td>
              <td>{patient.firstName}</td>
              <td>{patient.lastName}</td>
              <td>{patient.email}</td>
              <td>{patient.phoneNumber}</td>
              {/* <td>{patient.nursery.nurseryId}</td> */}
              <td>
                <Link to={`/caretaker/${patient.caretaker.caretakerId}`}>
                  {patient.caretaker.caretakerId}
                </Link>
              </td>
              <td>
                <button
                  className="update-btn"
                  onClick={() => handleUpdateClick(patient.patientId)}
                >
                  Update
                </button>
                <button
                  className="delete-btn"
                  onClick={() =>
                    handleDeleteClick(
                      patient.patientId,
                      patient.nursery.nurseryId
                    )
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

export default PatientList;
