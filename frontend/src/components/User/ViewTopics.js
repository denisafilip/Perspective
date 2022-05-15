import {Card, Table, Button} from "react-bootstrap";
import React, {useState} from "react";
import "../../css/FormStyle.css";
import axios from "axios";
import authHeader from "../AuthHeader";
import {useNavigate} from "react-router-dom";

export default function ViewTopics() {

    const [subjects, setSubjects] = useState(JSON.parse(localStorage.getItem("subjects")));
    const [topics, setTopics] = useState(JSON.parse(localStorage.getItem("topics")));
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));

    const navigate = useNavigate();

    const markTopic = async(topic) => {
        await axios
            .post("http://localhost:8080/user/addPerspective",
                {"topicDTO": topic,
                    "userDTO": user,
                    "belief": "UNDEFINED",
                    "notes": ""},
                {headers: authHeader()})
            .then((response) => {
                console.info(response);
                navigate("/user/viewTopics");
            })
            .catch((error) => {
                alert("You have already marked this topic!");
                console.error("There was an error!", error.response.data.message)
            });
    }

    return (
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
                                <th>Mark as Interested</th>
                            </tr>
                            </thead>
                            <tbody>
                            {topics?.filter(topic => topic.subjectDTO.name === subject.name)
                                .map(topic => {
                                    return (
                                        <tr key={topic.name}>
                                            <td key={topic.name}>{topic.name}</td>
                                            <td key={topic.description}>{topic.description}</td>
                                            <td key={topic.name + "Button"}>
                                                <Button variant="outline-success" onClick={() => markTopic(topic)}>Interested</Button>
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
    );
}