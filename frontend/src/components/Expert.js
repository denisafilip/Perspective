import React from "react";
import {Route, Routes } from 'react-router-dom';
import ExpertHome from "./Expert/ExpertHome";

function Expert() {
    return (
        <Routes>
            <Route path="/" element={<ExpertHome/>}>
            </Route>
        </Routes>
    );
}

export default Expert;