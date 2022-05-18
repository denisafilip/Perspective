import axios from "axios";
import authHeader from "../AuthHeader";
import AuthService from "../AuthService";
import {Button, Card, Image, Table, Toast} from "react-bootstrap";
import spectrum from "../../css/spectrum.png";
import React, {useEffect, useState} from "react";

export default function ConsultResources() {
    const [subjects, setSubjects] = useState(JSON.parse(localStorage.getItem("subjects")));
    const [resources, setResources] = useState([]);

    const getResources = async() => {
        axios
            .get("http://localhost:8080/getResources")
            .then((response) => {
                localStorage.setItem('resources', JSON.stringify(response.data));
                setResources(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    useEffect(() => {
        getResources();
    }, []);

    if (JSON.parse(localStorage.getItem("perspectives"))?.length === 0) {
        return (
            <Card className="CardStyle">
                <Card.Body>You haven't shown interest in any available discussion topics so far.</Card.Body>
                <Card.Body>🍇🍇🍇🍇</Card.Body>
                <Card.Body>Make sure to start broadening your mind by looking through the available topics.</Card.Body>
            </Card>
        );
    } else {
        return (
            <div>
                <Image
                    img src={spectrum} alt="cur" className="center"
                    style={{justifyContent: 'center', width: "800px"}}
                />

                <div>
                    {subjects?.map(subject => {
                        return (
                            <div key={subject.name}>
                                <Card className="CardStyle">
                                    <Card.Body>{subject.name}</Card.Body>
                                </Card>
                                <Table className="TableStyle" striped bordered hover key={subject.name}>
                                    <thead>
                                    <tr>
                                        <th>Topic Name</th>
                                        <th>Description</th>
                                        <th>Perspective</th>
                                        <th>Resources</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {JSON.parse(localStorage.getItem("perspectives"))
                                        ?.filter(perspective => perspective.topicDTO.subjectDTO.name === subject.name)
                                        .map(perspective => {
                                            return (
                                                <tr key={perspective.topicDTO.name}>
                                                    <td key={perspective.topicDTO.name}>{perspective.topicDTO.name}</td>
                                                    <td key={perspective.topicDTO.description}>{perspective.topicDTO.description}</td>
                                                    <td key={perspective.topicDTO.name + perspective.belief}>{perspective.belief}</td>
                                                    <td key={perspective.topicDTO.name + "Button"}>
                                                        <div className="card">
                                                            <div className="card-header">List of Uploaded Resources</div>
                                                            <ul className="list-group list-group-flush">
                                                                {JSON.parse(localStorage.getItem("resources"))?.filter(resource => resource.subjectDTO.name === perspective.topicDTO.subjectDTO.name)
                                                                    .map((file, index) => (
                                                                    <li className="list-group-item" key={index}>
                                                                        <a href={file.url}>{file.name}</a>
                                                                    </li>
                                                                ))}
                                                            </ul>
                                                        </div>
                                                    </td>
                                                </tr>
                                            );
                                        })}
                                    </tbody>
                                </Table>
                                <br/>
                                <br/>
                            </div>
                        );
                    })}
                </div>
            </div>
        );
    }
}