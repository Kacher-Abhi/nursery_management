import axios from "axios";

const API_BASE_URL = "http://localhost:8080/patients"; // Replace with your Spring Boot backend URL

const PatientService = {
  createPatient: async (patientData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/createPatient`,
      patientData
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  getPatient: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
};

export default PatientService;
