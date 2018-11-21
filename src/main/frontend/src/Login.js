import {Component} from "react";
import API from "./API";
import Form from "reactstrap/src/Form";
import FormGroup from "reactstrap/src/FormGroup";
import Label from "reactstrap/src/Label";
import Input from "reactstrap/src/Input";
import Button from "reactstrap/src/Button";
import React from "react";


export default class Login extends Component {
    state = {
        login: "",
        password: "",
        response: ""
    };

    handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const response = await API.login(formData);

        if (response.status === 200) {
            this.props.setToken(response.payload);
        }

        this.setState(() => ({message: response.message}))
    };

    render() {
        return <>
            {this.state.response}
            <Form className="mx-auto col-md-6" method="POST" onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="login">Login</Label>
                    <Input type="text" name="login" id="login" placeholder="Login"/>
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
