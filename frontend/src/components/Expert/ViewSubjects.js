import {Table} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import "../../css/FormStyle.css";
import axios from "axios";
import authHeader from "../AuthHeader";
import {useNavigate} from "react-router-dom";
import AuthService from "../AuthService";

export default function ViewSubjects() {

    const [subjects, setSubjects] = useState([]);

    const navigate = useNavigate();

    const getSubjects = async () => {
        await axios
            .get("http://localhost:8080/expert/getSubjects", {
                headers: authHeader(),
                params: {
                    expertEmail: AuthService.getJWT().email
                }
            })
            .then((response) => {
                console.info(response);
                setSubjects(response.data);
            })
            .catch((error) => {
                console.error("There was an error!", error.response.data.message);
            });
    }

    useEffect(() => {
        getSubjects();
    }, []);

    return (
        <div>
            <Table className="TableStyle" striped bordered hover key={1}>
                <thead>
                <tr>
                    <th>Subject Name</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                {subjects?.map(subject => {
                        return (
                            <tr key={subject.name}>
                                <td key={subject.name}>{subject.name}</td>
                                <td key={subject.description}>{subject.description}</td>
                            </tr>
                        );
                    })}
                </tbody>
            </Table>
        </div>
    );
}