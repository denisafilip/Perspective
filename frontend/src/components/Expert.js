import React from "react";
import {Route, Routes } from 'react-router-dom';
import ExpertHome from "./Expert/ExpertHome";
import ViewSubjects from "./Expert/ViewSubjects";

function Expert() {
    return (
        <Routes>
            <Route path="/" element={<ExpertHome/>}>
                <Route path="viewSubjects" element={<ViewSubjects/>}/>
            </Route>
        </Routes>
    );
}

export default Expert;