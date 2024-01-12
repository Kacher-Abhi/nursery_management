import React, { useState } from "react";
import AdminService from "./adminService";
import "./style.css";
import Layout from "../Layout/Layout";

const AdminForm = () => {
  const [adminData, setAdminData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone_number: "",
    password: "",
    nurseryId: "",
    superAdmin: 0,
  });

  const handleChange = (e) => {
    setAdminData({
      ...adminData,
      [e.target.name]: e.target.type === 'checkbox' ? e.target.checked : e.target.value,
    });
  };
  

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Assuming AdminService.createAdmin method exists
      await AdminService.createAdmin(adminData);
      alert("Admin created successfully!");
      // Optionally, reset the form or redirect to another page
      setAdminData({
        firstName: "",
        lastName: "",
        email: "",
        phone_number: "",
        password: "",
        nurseryId: "",
        superAdmin: 0,
      });
    } catch (error) {
      console.error("Error creating admin:", error);
      alert("Error creating admin. Please check the console for details.");
      console.log(setAdminData);
    }
  };

  return (
    <Layout>
    <>
    <h1>Admin Pannel</h1>
    <div className="container">
      <div className="form-box">
        <form onSubmit={handleSubmit}>
          <label htmlFor="firstName">First Name:</label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            value={adminData.firstName}
            onChange={handleChange}
            required
          />
          {/* <br /> */}
          <label htmlFor="lastName">Last Name:</label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            value={adminData.lastName}
            onChange={handleChange}
            required
          />
          {/* <br /> */}

          <label htmlFor="email">Email :</label>
          <input
            type="email"
            id="email"
            name="email"
            value={adminData.email}
            onChange={handleChange}
            required
          />
          {/* <br /> */}

          <label htmlFor="phone_number">Phone Number :</label>
          <input
            type="number"
            id="phone_number"
            name="phone_number"
            value={adminData.phone_number}
            onChange={handleChange}
            required
          />
          {/* <br /> */}
          {/* <br /> */}

          <label htmlFor="password">Password : </label>
          <input
            type="password"
            id="password"
            name="password"
            value={adminData.password}
            onChange={handleChange}
            required
          />

          <label htmlFor="nurseryId">Nursery ID :</label>
          <input
            type="text"
            id="nurseryId"
            name="nurseryId"
            value={adminData.nurseryId}
            onChange={handleChange}
            required
          />

          {/* <br /> */}
          <label htmlFor="isSuperAdmin">Super Admin : </label>
          <input
            type="checkbox"
            id="isSuperAdmin"
            name="isSuperAdmin"
            value={adminData.isSuperAdmin}
            onChange={handleChange}
          />
          <button type="submit">Create Admin</button>
        </form>
      </div>
    </div>
    </>
    </Layout>
  );
};

export default AdminForm;