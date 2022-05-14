import {Card, Table} from "react-bootstrap";
import React, {useState} from "react";
import "../../css/FormStyle.css";

export default function ViewTopics() {

    const [subjects, setSubjects] = useState(JSON.parse(localStorage.getItem("subjects")));
    const [topics, setTopics] = useState(JSON.parse(localStorage.getItem("topics")));
    console.log(topics);

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
                            </tr>
                            </thead>
                            <tbody>
                            {topics?.filter(topic => topic.subjectDTO.name === subject.name)
                                .map(topic => {
                                    return (
                                        <tr key={topic.name}>
                                            <td key={topic.name}>{topic.name}</td>
                                            <td key={topic.description}>{topic.description}</td>
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