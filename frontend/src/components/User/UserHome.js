import React, {useEffect} from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import {Outlet} from 'react-router-dom';
import AuthService from '../AuthService';
import axios from "axios";
import authHeader from "../AuthHeader";
 
export default function UserHome() {
    
    useEffect(() => {
        axios
            .get("http://localhost:8080/user/get", {
                headers: authHeader(),
                params: {
                    userEmail: AuthService.getJWT().email
                }
            })
            .then((response) => {
                localStorage.setItem('user', JSON.stringify(response.data));
                console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting the current user!", error)
                navigate("/");
            } );
        }, []);
    
    const navigate = useNavigate();
    
    function logOut() {
        AuthService.logout();
    }

    return (
        <div>
            <Navbar className="color-nav">
                <Container>
                    <Navbar.Brand style={{color: "white", fontWeight: "bold"}}  href="/user/">User</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link style={{color: "white", fontWeight: "bold"}} href="/user/startConversation">Start Conversation</Nav.Link>
                        <Nav.Link style={{color: "white", fontWeight: "bold"}}  href="/user/viewTopics">View Topics</Nav.Link>
                        <Nav.Link style={{color: "white", fontWeight: "bold"}}  href="/" onClick={logOut}>Log Out</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <Outlet />
        </div>
    );
}