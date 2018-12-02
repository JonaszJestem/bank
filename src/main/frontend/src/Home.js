import {Component} from "react";
import React from "react";

export default class Home extends Component {
    render() {
        return <div><button onClick={this.handleLogout}>Wyloguj</button></div>
    }

    handleLogout = (event) => {
        event.preventDefault();
        sessionStorage.removeItem("token")
    }
}