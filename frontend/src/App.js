import './App.css';
import {BrowserRouter as Router, Outlet, Route, Routes} from 'react-router-dom';
import Home from "./components/Home";
import Login from "./components/Login";
import Register from "./components/Register";
import {Container, Nav, Navbar} from "react-bootstrap";
import React from "react";
import "./css/Home.css";


function App() {
  return (
      <Router>
      <div>
          <Navbar className="color-nav">
              <Container>
                  <Navbar.Brand className="color-nav" style={{color: "white", fontWeight: "bold"}} href="/">Perspective</Navbar.Brand>
                  <Nav className="me-auto">
                      <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/register">Register</Nav.Link>
                      <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/login">Log In</Nav.Link>
                  </Nav>
              </Container>
          </Navbar>
          <Outlet />
          <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>

      </Routes>
      </div>
      </Router>
  );
}

export default App;
