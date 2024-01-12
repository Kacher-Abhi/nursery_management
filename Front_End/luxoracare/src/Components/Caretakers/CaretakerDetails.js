// CaretakerDetails.js
import React, { useState, useEffect } from "react";
import PatientService from "../Patients/patientService";
import { useParams } from "react-router-dom";
import StarRating from "./StarRating";
import "./CaretakerDetails.css"; // Import CSS file for styling
import Avatar from "../../public/avatar.png";


const CaretakerDetails = () => {
  const [caretaker, setCaretaker] = useState({});
  const [loading, setLoading] = useState(true);

  const params = useParams();

  useEffect(() => {
    const fetchCaretaker = async () => {
      try {
        const caretakerId = params.caretakerId;
        const data = await PatientService.getCareTaker(caretakerId);
        setCaretaker(data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching caretaker details:", error);
        setLoading(false);
      }
    };

    fetchCaretaker();
  }, [params.caretakerId]);

  if (loading) {
    return <div>Loading caretaker details...</div>;
  }

  if (!caretaker) {
    return <div>Caretaker not found</div>;
  }

  return (
    <div className="caretaker-details-container">
      <div className="banner">
      <div className="caretaker-info">
          <h2>{caretaker.name}</h2>
          <p> Rating: <StarRating rating={caretaker.averageRating} /></p>
        </div>
        </div>
      <div className="caretaker-header">
        <img
          src={caretaker.image || Avatar}
          alt="Caretaker"
          className="caretaker-image"
        />
      </div>
      <div className="caretaker-details">
        <p>Caretaker ID: {caretaker.caretakerId}</p>
        <p>Age: {caretaker.age}</p>
        <p>Sex: {caretaker.sex}</p>
        <p>Designation: {caretaker.designation}</p>
        {/* <p>Phone Number: {caretaker.phoneNumber}</p> */}
        <p>Years of Experience: {caretaker.yearsOfExperience}</p>
        {/* <p>Nursery ID: {caretaker.nurseryId}</p> */}
        {/* Add more details as needed */}
      </div>
    </div>
  );
};

export default CaretakerDetails;
