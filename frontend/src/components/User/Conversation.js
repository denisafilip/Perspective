import React, {useEffect, useState} from "react";
import axios from "axios";
import authHeader from "../AuthHeader";
import AuthService from "../AuthService";
import {Button} from "react-bootstrap";

export default function Conversation() {
    const [perspectives, setPerspectives] = useState([]);
    const [chosenTopicName, setChosenTopicName] = useState(undefined);
    const [messages, setMessages] = useState([]);
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));
    const [receiver, setReceiver] = useState("");
    const [receiverUsername, setReceiverUsername] = useState("");
    const [allUsers, setAllUsers] = useState([]);
    const [conversation, setConversation] = useState({
        "sender": JSON.parse(localStorage.getItem("user")),
        "receiver": undefined,
        "message": "",
        "topicDTO": ""
    })

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
                setPerspectives(response.data);
                setChosenTopicName(response.data[0].topicDTO.name);
                setConversation({...conversation, topicDTO: response.data[0].topicDTO.name})
                console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting the perspectives of the current user!", error)
            } );
    }

    const getAllUsers = async () => {
        await axios
            .get("http://localhost:8080/user/getAll", {
                headers: authHeader()
            })
            .then((response) => {
                localStorage.setItem('allUsers', JSON.stringify(response.data));
                setAllUsers(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting all the users from the database!", error.response.data.message)
            });
    }

    const getReceiver = async(receiver) => {
        await axios
            .get("http://localhost:8080/user/getByUsername", {
                headers: authHeader(),
                params: {
                    username: receiver
                }
            })
            .then((response) => {
                setReceiver(response.data);
                setConversation({...conversation, receiver: response.data})
                console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting the user by username!", error)
            } );
    }

    const getConversationTopic = async (topicName) => {
        await axios
            .get("http://localhost:8080/conversation/getConversationTopic", {
                headers: authHeader(),
                params: {
                    topicName: topicName
                }
            })
            .then((response) => {
                setConversation({...conversation, topicDTO: response.data})
                getAllMessagesInConversation(conversation.receiver.username)
                console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting the conversation topic!", error)
            } );
    }

    useEffect(() => {
        getPerspectives();
        getAllUsers();
    }, []);

    const handleMessage = (event) => {
        const {value} = event.target;
        setConversation({...conversation, "message": value});
    }

    const handleSendMessage = () => {
        sendMessage().then(() => {
                console.log("Message sent!")
            })
    }

    const handleReceiverSelection = (event) => {
        setReceiverUsername(event.target.value);
        getReceiver(event.target.value);
        console.log(receiverUsername);
        getAllMessagesInConversation(event.target.value);
    }

    useEffect(() => {
        getAllMessagesInConversation(receiverUsername)
    }, [receiverUsername]);

    const getAllMessagesInConversation = async(receiver) => {
        await axios
            .get("http://localhost:8080/conversation/getAll", {
                headers: authHeader(),
                params: {
                    receiverUsername: receiver,
                    senderUsername: user.username,
                    topicName: chosenTopicName
                }
            })
            .then((response) => {
                setMessages(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.error("There was an error when getting all the messages in the conversation!", error)
            } );
    }

    const sendMessage = async() => {
        console.log(conversation);
        await axios
            .post("http://localhost:8080/conversation/sendMessage", conversation, {headers: authHeader()})
            .then((response) => {
                setConversation({
                    ...conversation,
                    "message": ""
                })
                console.info(response.data);
            })
            .catch((error) => {
                console.error("There was an error!", error.response.data.message)
            });
    }

    return (
        <div>
            <div className="CustomSelect">
                <select value={chosenTopicName} onChange={(event) => {
                    setChosenTopicName(event.target.value);
                    getConversationTopic(event.target.value)
                }}>
                    {perspectives?.map(perspective =>
                        <option value={perspective.topicDTO.name} key={perspective.topicDTO.name}>{perspective.topicDTO.name}</option>
                    )}
                </select>
            </div>
            <br/>
            <br/>
            <div className="CustomSelect">
                <select value={receiverUsername} onChange={handleReceiverSelection}>
                    {allUsers?.map(user =>
                        <option value={user.username} key={user.username}>{user.username}</option>
                    )}
                </select>
            </div>
            <br/>
            <div className="container">
                <div className="chat-box">
                    <div className="chat-content">
                        <ul className="chat-messages">
                            {messages?.map(message => (
                                <li className="message" key={message.message}>
                                    <div className="avatar">{"From:  " + message.sender.username}</div>
                                    <div className="message-data">{message.message}</div>
                                    <div className="avatar self">{"To:  " + message.receiver.username}</div>
                                </li>
                            ))}
                        </ul>
                        <div className="send-message">
                            <input type="text" className="input-message" placeholder="enter the message" value={conversation.message} onChange={handleMessage} />
                            <Button variant="success" onClick={handleSendMessage}>Send</Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}