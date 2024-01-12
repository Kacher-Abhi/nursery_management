import React, { useState, useEffect } from 'react';
import AdminService from './adminService';
import './adminList.css'; // Import CSS file for styling

const AdminList = () => {
  const [admins, setAdmins] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await AdminService.getAdmins();
        setAdmins(data);
        console.log(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  const handleUpdateClick = (adminId) => {
    console.log(`Update clicked for admin ID: ${adminId}`);
    // Implement the logic for updating an admin
  };

  const handleDeleteClick = (adminId, nurseryId) => {
    console.log(`Delete clicked for admin ID: ${adminId} , Nursery ID: ${nurseryId}`);
    // Implement the logic for deleting an admin
  };

  return (
    <>
    <div>
      <h2>Admin List</h2>
      <table className="admin-table">
        <thead>
          <tr>
            <th>Admin ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Nursery ID</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {admins.map((admin) => (
            <tr key={admin.adminId}>
              <td>{admin.adminId}</td>
              <td>{admin.firstName}</td>
              <td>{admin.lastName}</td>
              <td>{admin.email}</td>
              <td>{admin.phone_number}</td>
              <td>{admin.nursery.nurseryId}</td>
              <td>
                <button className="update-btn" onClick={() => handleUpdateClick(admin.adminId)}>Update</button>
                <button className="delete-btn" onClick={() => handleDeleteClick(admin.adminId, admin.nursery.nurseryId)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    </>
  );
};

export default AdminList;
