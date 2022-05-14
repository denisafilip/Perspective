import React from "react";
import {Route, Routes } from 'react-router-dom';
import UserHome from './User/UserHome';
import SetPerspective from "./User/SetPerspective";
import ViewTopics from "./User/ViewTopics";

function User() {
    return (
        <Routes>
            <Route path="/" element={<UserHome/>}>
                <Route path="setPerspective" element={<SetPerspective/>}/>
                <Route path="viewTopics" element={<ViewTopics/>}/>
            </Route>
        </Routes>
    );
}

export default User;