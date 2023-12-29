import React, { useState } from "react";
import CareTakerService from "./caretakerService";
// import "./style.css";

const CaretakerForm = () => {
    const [caretakerData, setCaretakerData] = useState({
        name: "",
        age: "",
        email: "",
        phone_number: "",
        sex: "",
        password: "",
        yearsOfExperience: "",
        designation: "",
        role: "",
        nurseryId: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCaretakerData({ ...caretakerData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Assuming AdminService.createAdmin method exists
            await CareTakerService.createCaretaker(caretakerData);
            alert("Admin created successfully!");
            // Optionally, reset the form or redirect to another page
            setCaretakerData({
                name: "",
                age: "",
                email: "",
                phone_number: "",
                sex: "",
                password: "",
                yearsOfExperience: "",
                designation: "",
                role: "",
                nurseryId: "",
            });
        } catch (error) {
            console.error("Error creating Care Taker:", error);
            alert("Error creating admin. Please check the console for details.");
        }
    };

    return (
        <>
            <h1>Care Taker Pannel</h1>
            <div className="container">
                <div className="form-box">
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="name"> Name:</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            value={caretakerData.name}
                            onChange={handleChange}
                            required
                        />

                        <label htmlFor="age"> Age:</label>
                        <input
                            type="number"
                            id="age"
                            name="age"
                            value={caretakerData.age}
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
                            value={caretakerData.sex}
                            onChange={handleChange}
                            required
                        />
                        <label htmlFor="email">Email :</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            value={caretakerData.email}
                            onChange={handleChange}
                            required
                        />
                        {/* <br /> */}

                        <label htmlFor="phone_number">Phone Number :</label>
                        <input
                            type="number"
                            id="phone_number"
                            name="phone_number"
                            value={caretakerData.phone_number}
                            onChange={handleChange}
                            required
                        />

                        <label htmlFor="password">Password : </label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={caretakerData.password}
                            onChange={handleChange}
                            required
                        />
                        <label htmlFor="yearsOfExperience">Years Of Experience : </label>
                        <input
                            type="number"
                            id="yearsOfExperience"
                            name="yearsOfExperience"
                            value={caretakerData.yearsOfExperience}
                            onChange={handleChange}
                            required
                        />
                        <label htmlFor="designation">Designation : </label>
                        <input
                            type="text"
                            id="designation"
                            name="designation"
                            value={caretakerData.designation}
                            onChange={handleChange}
                            required
                        />
                        <label htmlFor="role">Role : </label>
                        <input
                            type="text"
                            id="role"
                            name="role"
                            value={caretakerData.role}
                            onChange={handleChange}
                            required
                        />

                        <label htmlFor="nurseryId">Nursery ID :</label>
                        <input
                            type="text"
                            id="nurseryId"
                            name="nurseryId"
                            value={caretakerData.nurseryId}
                            onChange={handleChange}
                            required
                        />

                        {/* <br /> */}
                        <button type="submit">Create Care Taker</button>
                    </form>
                </div>
            </div>
        </>
    );
};

export default CaretakerForm;
