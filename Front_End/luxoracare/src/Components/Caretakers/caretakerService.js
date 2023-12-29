import axios from "axios";

const API_BASE_URL = "http://localhost:8080/caretakers"; // Replace with your Spring Boot backend URL

const CareTakerService = {
  createCaretaker: async (caretakerData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/createCaretaker`,
      caretakerData
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  getCaretaker: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
};

export default CareTakerService;
