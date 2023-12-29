import axios from "axios";

const API_BASE_URL = "http://localhost:8080"; // Replace with your Spring Boot backend URL

const AdminService = {
  createAdmin: async (adminData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/admins/createAdmin`,
        adminData
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  getAdmins: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/admins`);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
};

export default AdminService;
