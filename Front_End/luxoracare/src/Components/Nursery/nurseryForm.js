import React, { useState } from "react";
import nurseryService from "./nurseryService";

const NurseryForm = () => {
  const [nurseryData, setNurseryData] = useState({
    nurseryId: "",
    nurseryName: "",
    primaryColor: "",
    secondaryColor: "",
    email: "",
    phoneNumber: "",
    image: null,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNurseryData({ ...nurseryData, [name]: value });
  };

  const handleImageChange = (e) => {
    const imageFile = e.target.files[0];
    setNurseryData({ ...nurseryData, image: imageFile });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const createdNursery = await nurseryService.createNursery(nurseryData);
      console.log("Nursery created:", createdNursery);
      setNurseryData({
        nurseryId: "",
        nurseryName: "",
        primaryColor: "",
        secondaryColor: "",
        email: "",
        phoneNumber: "",
        image: undefined // Set to undefined to properly reset the file input
      });

    } catch (error) {
      console.error("Error creating nursery:", error);
      alert(error.response.data);

    }
  };
  return (
    <>
      <h1>Add Nursery</h1>
      <div className="container">
        <div className="form-box">
          <form onSubmit={handleSubmit}>
            <label htmlFor="firstName">Nursery ID : </label>
            <input
              type="text"
              id="nurseryId"
              name="nurseryId"
              value={nurseryData.nurseryId}
              onChange={handleChange}
              required
            />
            {/* <br /> */}
            <label htmlFor="nurseryName">Nursery Name :</label>
            <input
              type="text"
              id="nurseryName"
              name="nurseryName"
              value={nurseryData.nurseryName}
              onChange={handleChange}
              required
            />
            {/* <br /> */}

            <label htmlFor="primaryColor">Primary Color :</label>
            <input
              type="text"
              id="primaryColor"
              name="primaryColor"
              value={nurseryData.primaryColor}
              onChange={handleChange}
              required
            />

            <label htmlFor="secondaryColor">Secondary Color :</label>
            <input
              type="text"
              id="secondaryColor"
              name="secondaryColor"
              value={nurseryData.secondaryColor}
              onChange={handleChange}
              required
            />

            <label htmlFor="email">Email :</label>
            <input
              type="email"
              id="email"
              name="email"
              value={nurseryData.email}
              onChange={handleChange}
              required
            />
            {/* <br /> */}

            <label htmlFor="phoneNumber">Phone Number :</label>
            <input
              type="number"
              id="phoneNumber"
              name="phoneNumber"
              value={nurseryData.phoneNumber}
              onChange={handleChange}
              required
            />
            <label htmlFor="image">Nursery Logo:</label>
            <input
              type="file"
              id="image"
              name="image"
              accept="image/*"
              onChange={handleImageChange}
            />

            <button type="submit">Create Nursery</button>
          </form>
        </div>
      </div>
    </>
  );
};
export default NurseryForm;
