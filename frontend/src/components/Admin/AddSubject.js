import React, { useState } from "react";
import {Form, Button, Card} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "../../css/FormStyle.css";
import axios from "axios";
import authHeader from "../AuthHeader";

export default function AddSubject() {
    const [subjectInfo, setSubjectInfo] = useState({
        name: "",
        description: ""
    });
    const [error, setError] = useState("");

    const navigate = useNavigate();

    function validateForm() {
        return subjectInfo.name.length > 0 && subjectInfo.description.length > 0;
    }

    function handleChange(event) {
        const {name, value} = event.target
        setSubjectInfo(prevState => {
            return {
                ...prevState,
                [name]: value
            };
        })
        console.log(subjectInfo);
    }

    const addSubject = async() => {
        await axios
            .post("http://localhost:8080/admin/addSubject", subjectInfo, {headers: authHeader()})
            .then((response) => {
                alert("Success! You added the subject " + subjectInfo.name + "!");
                setSubjectInfo({
                    name: "",
                    description: ""
                })
                console.info(response);
                navigate("/admin/addSubject");
            })
            .catch((error) => {
                setError(error.response.data.message);
                console.error("There was an error!", error.response.data.message)
            });
    }

    function handleSubmit(event) {
        addSubject().then(() => console.log("Success!"));
        event.preventDefault();
    }

    return (
        <div className="FormStyle">
            <Card className="CardStyle">
                <Card.Body>Please add a name and description for the new Discussion Subject!</Card.Body>
            </Card>

            <br/>
            <br/>

            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="subjectName" className="mb-3">
                    <Form.Label>Subject Name</Form.Label>
                    <Form.Control autoFocus name="name" type="text" value={subjectInfo.name} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" controlId="subjectDescription" className="mb-3">
                    <Form.Label>Description</Form.Label>
                    <Form.Control autoFocus name="description" type="text" value={subjectInfo.description} onChange={handleChange} />
                </Form.Group>

                <br/>
                <text className="error-message">
                    {error}
                </text>

                <Button variant="primary" block size="lg" type="submit" disabled={!validateForm()}>
                    Add Discussion Subject
                </Button>
            </Form>
        </div>
    );
}