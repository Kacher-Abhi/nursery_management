// nurseryService.js
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/nurseries";

const nurseryService = {
  createNursery: async (nurseryData) => {
    try {
      const formData = new FormData();
      formData.append("nurseryId", nurseryData.nurseryId);
      formData.append("nurseryName", nurseryData.nurseryName);
      formData.append("primaryColor", nurseryData.primaryColor);
      formData.append("secondaryColor", nurseryData.secondaryColor);
      formData.append("email", nurseryData.email);
      formData.append("phoneNumber", nurseryData.phoneNumber);
      formData.append("image", nurseryData.image);

      const response = await axios.post(
        `${API_BASE_URL}/createNursery`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      return response.data;
    } catch (error) {
      throw error;
    }
  },
  getNurseries: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
  getNurseryById: async (nurseryId) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/${nurseryId}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  },

};

export default nurseryService;
