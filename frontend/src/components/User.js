import React from "react";
import {Route, Routes } from 'react-router-dom';
import UserHome from './User/UserHome';
import SetPerspective from "./User/SetPerspective";
import ViewTopics from "./User/ViewTopics";
import Conversation from "./User/Conversation";
import ConsultResources from "./User/ConsultResources";

function User() {
    return (
        <Routes>
            <Route path="/" element={<UserHome/>}>
                <Route path="setPerspective" element={<SetPerspective/>}/>
                <Route path="viewTopics" element={<ViewTopics/>}/>
                <Route path="conversation" element={<Conversation/>}/>
                <Route path="consultResources" element={<ConsultResources/>}/>
            </Route>
        </Routes>
    );
}

export default User;