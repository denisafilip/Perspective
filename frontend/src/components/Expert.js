import React from "react";
import {Route, Routes } from 'react-router-dom';
import ExpertHome from "./Expert/ExpertHome";
import ViewSubjects from "./Expert/ViewSubjects";
import UploadResource from "./Expert/UploadResource";

function Expert() {
    return (
        <Routes>
            <Route path="/" element={<ExpertHome/>}>
                <Route path="viewSubjects" element={<ViewSubjects/>}/>
                <Route path="uploadResource" element={<UploadResource/>}/>
            </Route>
        </Routes>
    );
}

export default Expert;