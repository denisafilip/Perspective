import React from 'react';
import '../css/Home.css';
import perspective from '../css/perspective-logo.png';
import {Image} from 'react-bootstrap';
 
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