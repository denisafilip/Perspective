import React, {useEffect, useState} from "react";
import {OverlayTrigger, Toast, Table, Image, Button, Card, Popover} from "react-bootstrap";
import spectrum from "../../css/spectrum.png";
import "../../css/Home.css";
import axios from "axios";
import authHeader from "../AuthHeader";
import UserHome from "./UserHome";
import {useNavigate} from "react-router-dom";
import AuthService from "../AuthService";


export default function SetPerspective() {
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));
    const [perspective, setPerspective] = useState("UNDEFINED");
    const [subjects, setSubjects] = useState(JSON.parse(localStorage.getItem("subjects")));
    const [topics, setTopics] = useState(JSON.parse(localStorage.getItem("topics")));
    const [perspectives, setPerspectives] = useState(JSON.parse(localStorage.getItem("perspectives")));

    const [showA, setShowA] = useState(false);

    const toggleShowA = () => setShowA(!showA);

    const navigate = useNavigate();

    const getPerspectives = async() => {
        await axios
            .get("http://localhost:8080/user/getPerspectives", {
                headers: authHeader(),
                params: {
                    userEmail: AuthService.getJWT().email
                }
            })
            .then((response) => {
                localStorage.setItem('perspectives', JSON.stringify(response.data));
                console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting the perspectives of the current user!", error)
                navigate("/user");
            } );
    }

    useEffect(() => {
        getPerspectives();
    }, [perspectives]);

    const modifyPerspective = async(perspective, belief) => {
        setPerspectives(JSON.parse(localStorage.getItem("perspectives")));
        await axios
            .post("http://localhost:8080/user/modifyPerspective",
                {"topicDTO": perspective.topicDTO,
                    "userDTO": perspective.userDTO,
                    "belief": belief,
                    "notes": ""},
                {headers: authHeader()})
            .then((response) => {
                console.info(response);
                //getPerspectives();
                setPerspectives(JSON.parse(localStorage.getItem("perspectives")));
            })
            .catch((error) => {
                alert("You have already marked this topic!");
                console.error("There was an error!", error.response.data.message)
            });
    }

    if (perspectives?.length === 0) {
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
                                        <th>Modify Perspective</th>
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
                                                        <Button onClick={toggleShowA} variant="outline-success" className="mb-2">
                                                            Modify Perspective
                                                        </Button>
                                                        <Toast show={showA} onClose={toggleShowA}>
                                                            <Toast.Header>
                                                                <strong className="me-auto">Modify Perspective</strong>
                                                            </Toast.Header>
                                                            <Toast.Body>
                                                                <Button variant="danger" style={{
                                                                    alignSelf: 'center',
                                                                    justifyContent: 'center',
                                                                    alignContent: 'center'
                                                                }} onClick={() => modifyPerspective(perspective, "STRONGLY_DISAGREE")}>
                                                                    Strongly Disagree
                                                                </Button>{'   '}
                                                                <Button style={{backgroundColor: "#EE991E", borderColor: "#EE991E", boxShadow: "#EE991E"}}
                                                                        onClick={() => modifyPerspective(perspective, "DISAGREE")}>
                                                                    Disagree
                                                                </Button>{'   '}
                                                                <Button variant="warning"  onClick={() => modifyPerspective(perspective, "SOMEWHAT_DISAGREE")}>
                                                                    Somewhat Disagree
                                                                </Button>{'   '}
                                                                <Button style={{backgroundColor: "#B3E61F", borderColor: "#B3E61F", boxShadow: "#B3E61F"}}
                                                                        onClick={() => modifyPerspective(perspective, "SOMEWHAT_AGREE")}>
                                                                    Somewhat Agree
                                                                </Button>{'   '}
                                                                <Button style={{backgroundColor: "#58E018", borderColor: "#58E018", boxShadow: "#58E018"}}
                                                                        onClick={() => modifyPerspective(perspective, "AGREE")}>
                                                                    Agree
                                                                </Button>{'   '}
                                                                <Button variant="success" onClick={() => modifyPerspective(perspective, "STRONGLY_AGREE")}>
                                                                    Strongly Agree
                                                                </Button>
                                                            </Toast.Body>
                                                        </Toast>
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