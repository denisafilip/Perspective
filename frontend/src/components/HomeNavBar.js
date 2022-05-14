import {Container, Nav, Navbar} from "react-bootstrap";
import React from "react";
import {Outlet} from "react-router-dom";

export default function HomeNavBar() {
    return (
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
            <Outlet/>
        </div>
    )
}