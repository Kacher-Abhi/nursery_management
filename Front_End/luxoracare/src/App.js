// App.js
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AdminForm from "./Components/Admin/adminForm.js";
import AdminList from "./Components/Admin/adminList.js";
import NurseryForm from "./Components/Nursery/nurseryForm.js";
import CareTakerForm from "./Components/Caretakers/caretakerForm.js";
import PatientForm from "./Components/Patients/patientForm.js";

import "./App.css";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/createAdmin" exact element={ <AdminForm/> }/>
          <Route path="/fetchedAdmins" exact element={<AdminList/>} />
          <Route path="/createNursery" exact element={<NurseryForm/>} />
          <Route path="/createCaretaker" exact element={<CareTakerForm/>} />
          <Route path="/createPatient" exact element={<PatientForm/>} />

        </Routes>
      </div>
    </Router>
  );
}

export default App;
