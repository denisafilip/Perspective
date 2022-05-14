import {useState} from "react";
import {useNavigate} from "react-router";
import axios from "axios";
import authHeader from "../AuthHeader";
import {Card, Form, Button} from "react-bootstrap";


export default function AddTopic() {
    const [subjects, setSubjects] = useState(JSON.parse(localStorage.getItem("subjects")));
    const [topicInfo, setTopicInfo] = useState({
        name: "",
        description: "",
        subjectDTO: subjects[0],
    });
    const [error, setError] = useState("");

    const navigate = useNavigate();

    function validateForm() {
        return topicInfo.name.length > 0 && topicInfo.description.length > 0;
    }

    function handleChange(event) {
        const {name, value} = event.target
        setTopicInfo(prevState => {
            return {
                ...prevState,
                [name]: value
            };
        })
        console.log(topicInfo);
    }

    function handleSelect(event) {
        const {name, value} = event.target
        console.log(value);
        setTopicInfo(prevState => {
            return {
                ...prevState,
                subjectDTO: {
                    name: value,
                    description: ""
                }
            };
        })
        console.log(topicInfo);
    }

    const addTopic = async() => {
        await axios
            .post("http://localhost:8080/admin/addTopic", topicInfo, {headers: authHeader()})
            .then((response) => {
                alert("Success! You added the discussion topic " + topicInfo.name + "!");
                setTopicInfo({
                    name: "",
                    subjectDTO: subjects[0],
                    description: ""
                })
                console.info(response)
                navigate("/admin/addTopic");
            })
            .catch((error) => {
                setError(error.response.data.message);
                console.error("There was an error!", error.response.data.message)
            });
    }

    function handleSubmit(event) {
        addTopic().then(r => console.log(r));
        event.preventDefault();
    }

    return (
        <div className="FormStyle">
            <Card className="CardStyle">
                <Card.Body>Please select a discussion subject and start adding topics for it!</Card.Body>
            </Card>

            <br/>
            <br/>

            <div>
                <select value={topicInfo.subjectDTO.name} onChange={handleSelect}>
                    {subjects?.map(subject =>
                        <option value={subject.name} key={subject.name}>{subject.name}</option>
                    )}
                </select>
            </div>

            <br/>
            <br/>

            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="topicName" className="mb-3">
                    <Form.Label>Topic Name</Form.Label>
                    <Form.Control autoFocus name="name" type="text" value={topicInfo.name} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" controlId="topicDescription" className="mb-3">
                    <Form.Label>Description</Form.Label>
                    <Form.Control autoFocus name="description" type="text" value={topicInfo.description} onChange={handleChange} />
                </Form.Group>

                <br/>
                <text className="error-message">
                    {error}
                </text>

                <Button variant="primary" block size="lg" type="submit" disabled={!validateForm()}>
                    Add Topic
                </Button>
            </Form>
        </div>
    );
}