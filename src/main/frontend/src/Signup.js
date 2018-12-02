import {Component} from "react";
import API from "./API";
import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import React from "react";

class Signup extends Component {
    state = {
        response: ""
    };

    handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const response = API.signup(formData);
        this.setState(() => ({response: response}))
    };

    render() {
        return <>
            {this.state.response}
            <Form className="mx-auto col-md-6" method="POST" onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="username">Login</Label>
                    <Input type="text" name="username" id="username" placeholder="Login"/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Email</Label>
                    <Input type="email" name="email" id="email" placeholder="Email"/>
                </FormGroup>
                <FormGroup>
                    <Label for="password">Password</Label>
                    <Input type="password" name="password" id="password" placeholder="Password"/>
                </FormGroup>
                <Button>Submit</Button>
            </Form>
        </>
    }
}

export default Signup;
