import axios from "axios";

const API_BASE_URL = "http://localhost:8080/tests"; // Replace with your Spring Boot backend URL

const TestService = {
  createTest: async (testData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/createTest`,
      testData
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  getTests: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
};

export default TestService;
