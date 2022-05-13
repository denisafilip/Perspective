import React, { useState } from "react";
import {Form, Button,} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import AuthService from "./AuthService";
import "../css/FormStyle.css";
import axios from "axios";

function Register() {
    const [userInfo, setUserInfo] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: "",
        username: ""
    });
    
    const [error, setError] = useState("");

    const navigate = useNavigate();

    function goToLogIn() {
        navigate("/login");
    }

    function validateForm() {
        return userInfo.email.length > 0 && userInfo.password.length > 0 && 
            userInfo.firstName.length > 0 && userInfo.lastName.length > 0 &&
            userInfo.username.length > 0 && userInfo.confirmPassword === userInfo.password;
    }

    function handleChange(event) {
        // get name and value properties from event target
        const {name, value} = event.target
        setUserInfo(prevState => {
            // update your 'list' property
            return {
              // spread old values into this object so you don't lose any data
              ...prevState,
              // update this field's value
              [name]: value
            };
        })
        console.log(userInfo);
    }

    const registerCustomer = async(userInfo) => {
        await axios
          .post("http://localhost:8080/customer/register", userInfo)
          .then((response) => {
              console.info(response);
              goToLogIn();
          })
          .catch((error) => {
            setError(error.response.data.message);
            console.error("There was an error!", error.response.data.message)
          });
    }

    function handleSubmit(event) {
        registerCustomer(userInfo);
        console.log(userInfo);
        event.preventDefault();
    }

    return (
        <div className="FormStyle">
            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="formBasicFirstName" className="mb-3">
                    <Form.Label>First Name</Form.Label>
                        <Form.Control autoFocus name="firstName" type="text" value={userInfo.firstName} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" controlId="formBasicLastName" className="mb-3">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control name="lastName" type="text" value={userInfo.lastName} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" controlId="formBasicEmail" className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control name="email" type="email" value={userInfo.email} onChange={handleChange}/>
                </Form.Group>

                <Form.Group size="lg" controlId="formBasicUsername" className="mb-3">
                    <Form.Label>Username</Form.Label>
                    <Form.Control autoFocus name="username" type="text" value={userInfo.username} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control name="password" type="password" value={userInfo.password} onChange={handleChange}/>
                </Form.Group>

                <Form.Group size="lg" className="mb-3" controlId="formBasicConfirmPassword">
                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control name="confirmPassword" type="password" value={userInfo.confirmPassword} onChange={handleChange}/>
                </Form.Group>

                <br/>

                <text className="error-message">
                    {error}
                </text>

                <Button variant="primary" block size="lg" type="submit" disabled={!validateForm()}>
                    Register
                </Button>
                <Button variant="primary" block size="lg" onClick={goToLogIn}>
                    Log In
                </Button>
            </Form>
        </div>
    );
}

export default Register;