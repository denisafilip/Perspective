import React from 'react';
import '../css/Home.css';
import { Container, Nav, Navbar, Card, NavLink } from 'react-bootstrap';
import {Outlet} from 'react-router-dom';
 
export default function Home () {

    return (
    <div>
        <Navbar bg="dark" variant="dark">
            <Container>
                <Navbar.Brand href="/">Food Delivery</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/register">Register</Nav.Link>
                    <Nav.Link href="/login">Log In</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
        <Card className="Home">
            <Card.Body>There are no truths, only perspective</Card.Body>
        </Card>
        <Outlet />
    </div>
    );
}