import './App.css';
import {BrowserRouter as Router, Outlet, Route, Routes} from 'react-router-dom';
import Home from "./components/Home";
import Login from "./components/Login";
import {Container, Nav, Navbar} from "react-bootstrap";
import React from "react";
import "./css/Home.css";
import PreRegister from "./components/PreRegister";
import AdminRegister from "./components/Admin/Register";
import UserRegister from "./components/User/Register";
import ExpertRegister from "./components/Expert/Register";
import Admin from "./components/Admin";
import User from "./components/User";
import HomeNavBar from "./components/HomeNavBar";
import Expert from "./components/Expert";


function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={<HomeNavBar/>}>
                  <Route path="/" element={<Home/>}/>
                  <Route path="login" element={<Login/>}/>
                  <Route path="preregister" element={<PreRegister/>}/>
                  <Route path="admin/register" element={<AdminRegister/>}/>
                  <Route path="user/register" element={<UserRegister/>}/>
                  <Route path="expert/register" element={<ExpertRegister/>}/>
              </Route>
              <Route path="/admin/*" element={<Admin/>}/>
              <Route path="/user/*" element={<User/>}/>
              <Route path="/expert/*" element={<Expert/>}/>
        </Routes>
      </Router>
  );
}

export default App;
