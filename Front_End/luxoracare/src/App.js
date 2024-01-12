// App.js
import "./App.css";
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AdminForm from "./Components/Admin/adminForm.js";
import AdminList from "./Components/Admin/adminList.js";
import NurseryForm from "./Components/Nursery/nurseryForm.js";
import CareTakerForm from "./Components/Caretakers/caretakerForm.js";
import PatientForm from "./Components/Patients/patientForm.js";
import NurseryList from "./Components/Nursery/nurseryList.js";
import TestForm from "./Components/Test/testForm.js";
import PatientList from "./Components/Patients/patientList.js";
import CaretakerDetails from "./Components/Caretakers/CaretakerDetails.js";
import AboutUs from "./Components/AboutUs/AboutUs.js";
import TestList from "./Components/Test/testList.js";

function App() {
  return (
    <>
    {/* <Navbar/> */}
    <Router>
      <div className="App">
        <Routes>
          <Route path="/createNursery" exact element={<NurseryForm />} />
          <Route path="/getAllNurseries" exact element={<NurseryList />} />

          <Route path="/createAdmin" exact element={<AdminForm />} />
          <Route path="/fetchedAdmins" exact element={<AdminList />} />

          <Route path="/createPatient" exact element={<PatientForm />} />
          <Route path="/getAllPatients/" exact element={<PatientList />} />

          <Route path="/createCaretaker" exact element={<CareTakerForm />} />

          <Route path="/createTest" exact element={<TestForm />} />
          <Route path="/getAllTests" exact element={<TestList />} />

          <Route path="/about-us/" exact element={<AboutUs />} />
          <Route
            path="/caretaker/:caretakerId"
            element={<CaretakerDetails />}
          />
        </Routes>
      </div>
    </Router>
    </>
  );
}

export default App;
