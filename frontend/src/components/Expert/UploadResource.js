import {Button} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import axios from "axios";
import authHeader from "../AuthHeader";
import AuthService from "../AuthService";


export default function UploadResource() {
    const [subjects, setSubjects] = useState([]);
    const [chosenSubjectName, setChosenSubjectName] = useState(undefined);
    const [chosenTopicName, setChosenTopicName] = useState(undefined);
    const [topics, setTopics] = useState([]);
    const [expert, setExpert] = useState(JSON.parse(localStorage.getItem("user")));
    const [fileInfos, setFileInfos] = useState([]);
    const [selectedFiles, setSelectedFiles] = useState(undefined);
    const [currentFile, setCurrentFile] = useState(undefined);
    const [message, setMessage] = useState("");

    const getSubjects = async () => {
        axios
            .get("http://localhost:8080/expert/getSubjects", {
                headers: authHeader(),
                params: {
                    expertEmail: AuthService.getJWT().email
                }
            })
            .then((response) => {
                console.info(response);
                setSubjects(response.data);
                setChosenSubjectName(response.data[0].name)
            })
            .catch((error) => {
                console.error("There was an error!", error.response.data.message)
            });
    }

    const getTopicsBySubject = async(subjectName) => {
        axios
            .get("http://localhost:8080/getSubjectTopics", {
                headers: authHeader(),
                params: {
                    subjectName: subjectName
                }
            })
            .then((response) => {
                console.info(response);
                setTopics(response.data);
                setChosenTopicName(response.data[0].name);
            })
            .catch((error) => {
                console.error("There was an error!", error.response.data.message)
            });
    }

    useEffect(() => {
        getResources();
        getSubjects();
    }, []);

    const getResources = async() => {
        axios
            .get("http://localhost:8080/expert/getResourcesByExpert", {
                headers: authHeader(),
                params: {
                    expertEmail: AuthService.getJWT().email
            }})
            .then((response) => {
                setFileInfos(response.data);
            })
            .catch((error) => {
                console.error(error.response.data.message);
            });
    }

    const upload = () => {
        let currentFile = selectedFiles[0];
        setCurrentFile(currentFile);

        let formData = new FormData();
        formData.append("file", currentFile);
        formData.append('topicName', new Blob([chosenTopicName], {
            type: "application/json"
        }));
        formData.append('expertDTO', new Blob([JSON.stringify(expert)], {
            type: "application/json"
        }));

        axios
            .post("http://localhost:8080/expert/uploadResource", formData,
                {headers: authHeader()})
            .then((response) => {
                console.log(response.data);
                setMessage("The resource was uploaded successfully!");
                getResources();
            })
            .catch((error) => {
                setMessage("Could not upload the file!");
                setCurrentFile(undefined);
                console.error(error.response.data.message);
            })
        setSelectedFiles(undefined);
    }

    const selectFile = (event) => {
        setSelectedFiles(event.target.files);
    }

    function handleSubjectSelection(event) {
        const {name, value} = event.target
        setChosenSubjectName(value);
        getTopicsBySubject(value);
    }

    return (
        <div>
            <div className="CustomSelect">
                <select value={chosenSubjectName} onChange={handleSubjectSelection}>
                    {subjects?.map(subject =>
                        <option value={subject.name} key={subject.name}>{subject.name}</option>
                    )}
                </select>
            </div>
            <br/>
            <br/>
            <div className="CustomSelect">
                <select value={chosenTopicName} onChange={(event) => setChosenTopicName(event.target.value)}>
                    {topics?.map(topic =>
                        <option value={topic.name} key={topic.name}>{topic.name}</option>
                    )}
                </select>
            </div>
            <br/>
            <br/>
            <label className="btn btn-default">
                <input type="file" onChange={selectFile} />
            </label>
            <Button className="btn btn-success" disabled={!selectedFiles} onClick={upload}>
                Upload
            </Button>
            <div className="alert alert-light" role="alert">
                {message}
            </div>
            <div className="card">
                <div className="card-header">List of Uploaded Resources</div>
                <ul className="list-group list-group-flush">
                    {fileInfos &&
                    fileInfos.map((file, index) => (
                        <li className="list-group-item" key={index}>
                            <p>{file.subjectDTO.name + ":    "}</p>
                            <a href={file.url}>{file.name}</a>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}