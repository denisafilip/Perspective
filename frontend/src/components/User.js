import React from "react";
import {Route, Routes } from 'react-router-dom';
import UserHome from './User/UserHome';

function User() {
    return (
        <Routes>
            <Route path="/" element={<UserHome/>}>
            </Route>
        </Routes>
    );
}

export default User;