import React, { useEffect, useState } from "react";
import NurseryService from "./nurseryService"; // Adjust the path accordingly
import "./nurseryList.css";
import Layout from "../Layout/Layout";

const NurseryList = () => {
  const [nurseries, setNurseries] = useState([]);
  const [searchId, setSearchId] = useState("");
  const [searchResult, setSearchResult] = useState(null);

  const handleSearch = async () => {
    try {
      const result = await NurseryService.getNurseryById(searchId);
      setSearchResult(result);
    } catch (error) {
      console.error("Error searching for nursery:", error.response.data);
      alert("Error", error);
      setSearchResult(null);
    }
  };

  useEffect(() => {
    const fetchNurseries = async () => {
      try {
        const nurseryData = await NurseryService.getNurseries();
        setNurseries(nurseryData);
      } catch (error) {
        console.error("Error fetching nurseries:", error);
      }
    };

    fetchNurseries();
  }, []);

  return (
    <>
    <Layout>
      <h2>Nursery List</h2>
      <div className="nursery-list-container">
        <div className="search-container">
          <label htmlFor="searchId">Search by ID: </label>
          <input
            type="text"
            id="searchId"
            value={searchId}
            onChange={(e) => setSearchId(e.target.value)}
            className="search-input"
          />
          <button onClick={handleSearch} className="search-button">
            Search
          </button>
        </div>

        {searchResult ? (
          <div className="nursery-item">
            <img
              src={`data:image/png;base64, ${searchResult.image}`}
              alt="Nursery Logo"
              className="nursery-logo"
            />
            <div className="nursery-details">
              <div className="nursery-name">{searchResult.nurseryName}</div>
              <div className="contact-info">
                <div className="phone-number">{searchResult.zipcode}</div>
                <div className="email">{searchResult.state}</div>
                {/* <div className="phone-number">{searchResult.phoneNumber}</div>
                <div className="email">{searchResult.email}</div> */}
              </div>
            </div>
            <div className="buttons-container">
              <button className="sign-in-button">Sign In</button>
              <button className="sign-up-button">Sign Up</button>
            </div>
          </div>
        ) : (
          nurseries.map((nursery) => (
            <div key={nursery.nurseryId} className="nursery-item">
              <img
                src={`data:image/png;base64, ${nursery.image}`}
                alt="Nursery Logo"
                className="nursery-logo"
              />
              <div className="nursery-details">
                <div className="nursery-name">{nursery.nurseryName}</div>
                <div className="contact-info">
                  <div className="phone-number">{nursery.zipcode}</div>
                  <div className="email">{nursery.state}</div>
                  <div className="phone-number">{nursery.phoneNumber}</div>
                  <div className="email">{nursery.email}</div>
                </div>
              </div>

              <div className="buttons-container">
                <button className="sign-in-button">Sign In</button>
                <button className="sign-up-button">Sign Up</button>
              </div>
            </div>
          ))
        )}
      </div>
    </Layout>
    </>
  );
};

export default NurseryList;
