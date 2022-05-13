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


function App() {
  return (
      <Router>
      <div>
          <Navbar className="color-nav">
              <Container>
                  <Navbar.Brand className="color-nav" style={{color: "white", fontWeight: "bold"}} href="/">Perspective</Navbar.Brand>
                  <Nav className="me-auto">
                      <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/preregister">Register</Nav.Link>
                      <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/login">Log In</Nav.Link>
                  </Nav>
              </Container>
          </Navbar>
          <Outlet />
          <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/preregister" element={<PreRegister/>}/>
          <Route path="/admin/register" element={<AdminRegister/>}/>
          <Route path="/user/register" element={<UserRegister/>}/>
          <Route path="/expert/register" element={<ExpertRegister/>}/>

      </Routes>
      </div>
      </Router>
  );
}

export default App;
