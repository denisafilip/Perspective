import React from 'react';
import '../css/Home.css';
import {useNavigate} from 'react-router-dom';

export default function PreRegister () {

    const navigate = useNavigate();

    function goToExpertRegister() {
        navigate("/expert/register");
    }

    function goToAdminRegister() {
        navigate("/admin/register");
    }

    function goToUserRegister() {
        navigate("/user/register");
    }

    return (
        <div>
            <div className="col-md-12 text-center">
                <button className="first-button" onClick={goToAdminRegister}>Administrator</button>
                <button className="register-button" onClick={goToExpertRegister}>Expert</button>
                <button className="register-button" onClick={goToUserRegister}>User</button>
            </div>
        </div>
    );
}