import React, { useState } from "react";
import PatientService from "./patientService";

// import "./style.css";

const PatientForm = () => {
    const [patientData, setPatientData] = useState({
        firstName: "",
        lastName: "",
        age: "",
        email: "",
        phoneNumber: "",
        sex: "",
        password: "",
        nurseryId: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setPatientData({ ...patientData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Assuming AdminService.createAdmin method exists
            await PatientService.createPatient(patientData);
            alert("Admin created successfully!");
            // Optionally, reset the form or redirect to another page
            setPatientData({
                firstName: "",
                lastName: "",
                age: "",
                email: "",
                phoneNumber: "",
                sex: "",
                password: "",
                nurseryId: "",
            });
        } catch (error) {
            console.error("Error creating Patient:", error);
            alert("Error creating Patient. Please check the console for details.");
        }
    };

    return (
        <>
            <h1>Patient Pannel</h1>
            <div className="container">
                <div className="form-box">
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="firstName">First Name:</label>
                        <input
                            type="text"
                            id="firstName"
                            name="firstName"
                            value={patientData.firstName}
                            onChange={handleChange}
                            required
                        />
                        {/* <br /> */}
                        <label htmlFor="lastName">Last Name:</label>
                        <input
                            type="text"
                            id="lastName"
                            name="lastName"
                            value={patientData.lastName}
                            onChange={handleChange}
                            required
                        />

                        <label htmlFor="age"> Age:</label>
                        <input
                            type="number"
                            id="age"
                            name="age"
                            value={patientData.age}
                            onChange={handleChange}
                            required
                        />
                        {/* <br /> */}
                        {/* <br /> */}
                        <label htmlFor="sex">Sex :</label>
                        <input
                            type="text"
                            id="sex"
                            name="sex"
                            value={patientData.sex}
                            onChange={handleChange}
                            required
                        />
                        <label htmlFor="email">Email :</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            value={patientData.email}
                            onChange={handleChange}
                            required
                        />
                        {/* <br /> */}

                        <label htmlFor="phoneNumber">Phone Number :</label>
                        <input
                            type="number"
                            id="phoneNumber"
                            name="phoneNumber"
                            value={patientData.phoneNumber}
                            onChange={handleChange}
                            required
                        />

                        <label htmlFor="password">Password : </label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={patientData.password}
                            onChange={handleChange}
                            required
                        />

                        <label htmlFor="nurseryId">Nursery ID :</label>
                        <input
                            type="text"
                            id="nurseryId"
                            name="nurseryId"
                            value={patientData.nurseryId}
                            onChange={handleChange}
                            required
                        />

                        {/* <br /> */}
                        <button type="submit">Create Patient</button>
                    </form>
                </div>
            </div>
        </>
    );
};

export default PatientForm;
