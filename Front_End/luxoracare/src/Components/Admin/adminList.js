// AdminList.js
import React, { useState, useEffect } from 'react';
import AdminService from './adminService';

const AdminList = () => {
  const [admins, setAdmins] = useState([]);

  useEffect(() => {
    // Fetch admins when the component mounts
    AdminService.getAdmins()
      .then((adminList) => {
        setAdmins(adminList);
      })
      .catch((error) => {
        console.error('Error fetching admins:', error);
      });
  }, []); // Empty dependency array to run the effect only once

  return (
    <div>
      <h1>Admin List</h1>
      <ul>
        {admins.map((admin) => (
          <li key={admin.adminId}>
            {admin.firstName} {admin.lastName} - {admin.email}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminList;
