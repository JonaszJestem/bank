import {Component} from "react";
import API from "./API";
import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import React from "react";

export default class Login extends Component {
    state = {
        response: ""
    };

    handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const response = await API.login(formData);
        console.log(response)
        if (response) {
            sessionStorage.setItem("token", response.token)
        }

        this.setState(() => ({message: response.message}))
    };

    handleReset = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const response = await API.reset(formData);
        console.log(response);

    };

    render() {
        return <>
            {this.state.response}
            <Form className="mx-auto col-md-6" method="POST" onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="usernameOrEmail">Login or email</Label>
                    <Input type="text" name="usernameOrEmail" id="usernameOrEmail" placeholder="Login"/>
                </FormGroup>
                <FormGroup>
                    <Label for="password">Password</Label>
                    <Input type="password" name="password" id="password" placeholder="Password"/>
                </FormGroup>
                <Button>Submit</Button>

            </Form>
            <Form className="mx-auto col-md-6" method="POST" onSubmit={this.handleReset}>
                <FormGroup>
                    <Label for="email">email</Label>
                    <Input type="email" name="email" id="email" placeholder="email"/>
                </FormGroup>
                <Button>Reset password</Button>

            </Form>

        </>
    }
}
