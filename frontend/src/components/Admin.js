import React from "react";
import {Route, Routes} from 'react-router-dom';
import AdminHome from './Admin/AdminHome';
import AddSubject from "./Admin/AddSubject";
import AddTopic from "./Admin/AddTopic";

function Admin() {
    return (
        <Routes>
            <Route path="/" element={<AdminHome/>}>
                <Route path="addSubject" element={<AddSubject/>}/>
                <Route path="addTopic" element={<AddTopic/>}/>
            </Route>
        </Routes>
    );
}

export default Admin;