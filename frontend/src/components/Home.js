import React from 'react';
import '../css/Home.css';
import perspective from '../css/perspective-logo.png';
import {Container, Nav, Navbar, Card, Image} from 'react-bootstrap';
import {Outlet} from 'react-router-dom';
 
export default function Home () {

    return (
    <div>
        <Image
            img src={perspective} alt="cur" className="center"
            style={{ alignSelf: 'center', justifyContent: 'center', alignContent: 'center' }}
        />
    </div>
    );
}