import React, {useState} from "react";
import RangeSlider from 'react-bootstrap-range-slider';
import perspective from "../../css/perspective-logo.png";
import {Table, Image, Button, Card} from "react-bootstrap";
import spectrum from "../../css/spectrum.png";
import "../../css/Home.css";
import axios from "axios";
import authHeader from "../AuthHeader";


export default function SetPerspective() {
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));
    const [perspective, setPerspective] = useState(0);
    const [subjects, setSubjects] = useState(JSON.parse(localStorage.getItem("subjects")));
    const [topics, setTopics] = useState(JSON.parse(localStorage.getItem("topics")));

    return(
        <div>
            <Image
                img src={spectrum} alt="cur" className="center"
                style={{ justifyContent: 'center', width: "800px" }}
            />
            <Button variant="danger"  style={{ alignSelf: 'center', justifyContent: 'center', alignContent: 'center', marginLeft: "350px"}} className="center" onClick={() => setPerspective(0)}>
                Strongly Disagree
            </Button>{'   '}
            <Button style={{backgroundColor: "#EE991E", borderColor: "#EE991E", boxShadow: "#EE991E"}} onClick={() => setPerspective(1)}>
                Disagree
            </Button>{'   '}
            <Button variant="warning" onClick={() => setPerspective(2)}>
                Somewhat Disagree
            </Button>{'   '}
            <Button style={{backgroundColor: "#B3E61F", borderColor: "#B3E61F", boxShadow: "#B3E61F"}} onClick={() => setPerspective(3)}>
                Somewhat Agree
            </Button>{'   '}
            <Button style={{backgroundColor: "#58E018", borderColor: "#58E018", boxShadow: "#58E018"}} onClick={() => setPerspective(4)}>
                Agree
            </Button>{'   '}
            <Button variant="success" onClick={() => setPerspective(5)}>
                Strongly Agree
            </Button>
        </div>
    )
}