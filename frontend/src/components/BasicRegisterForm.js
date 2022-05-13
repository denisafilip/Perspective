import {Button, Form} from "react-bootstrap";
import React from "react";


export default function BasicRegisterForm() {
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
    )
}