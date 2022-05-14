import React, {useEffect} from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import {Outlet} from 'react-router-dom';
import axios from "axios";
import AuthService from '../AuthService';
import authHeader from '../AuthHeader';
 
export default function AdminHome() {

    const navigateTo = useNavigate();

    useEffect(() => {
        axios
            .get("http://localhost:8080/admin/get", {
                headers: authHeader(),
                params: {
                    adminEmail: AuthService.getJWT().email
                }
            })
            .then((response) => {
                  localStorage.setItem('user', JSON.stringify(response.data));
                  console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting the current admin!", error)
                navigateTo("/");
            } );
    }, []);

    function logOut() {
        AuthService.logout();
    }

    return (
        <div>
            <Navbar className="color-nav">
                <Container>
                    <Navbar.Brand style={{color: "white", fontWeight: "bold"}}  href="/admin/">Administrator</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/admin/addSubject">Add Subject</Nav.Link>
                        <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/admin/addTopic">Add Topic</Nav.Link>
                        <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/" onClick={logOut}>Log Out</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <Outlet />
        </div>
    );
}