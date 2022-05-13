import React from 'react';
import '../css/Home.css';
import perspective from '../css/perspective-logo.png';
import {Image} from 'react-bootstrap';
import axios from "axios";
 
export default function Home() {

    axios
        .get('http://localhost:8080/getSubjects')
        .then(response => {
            localStorage.setItem('subjects', JSON.stringify(response.data));
        });

    return (
        <div>
            <Image
                img src={perspective} alt="cur" className="center"
                style={{ alignSelf: 'center', justifyContent: 'center', alignContent: 'center' }}
            />
        </div>
    );
}