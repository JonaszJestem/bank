import {Component} from "react";
import API from "./API";
import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import React from "react";

export default class Transfer extends Component {
    state = {
        response: undefined,
        message: undefined
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const response = await API.saveForConfirmation(formData);
        console.log(response);
        this.setState(() => ({response}))
    };

    render() {
        let response = "";

        if(this.state.response) {
            response = <div>Data from server:
                <p>{this.state.response.title}</p>
                <p>{this.state.response.amount}</p>
                <Button onClick={this.confirm}>Confirm</Button>
            </div>
        }

        if(this.state.message) {
            response = <p>{this.state.message}</p>
        }
        return <>
            {response}
            <Form className="mx-auto col-md-6" method="POST" onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="title">Tytuł</Label>
                    <Input type="text" name="title" id="title" placeholder="Tytuł"/>
                </FormGroup>
                <FormGroup>
                    <Label for="amount">Wartość</Label>
                    <Input type="text" name="amount" id="amount" placeholder="Wartość"/>
                </FormGroup>
                <Button>Submit</Button>
            </Form>
        </>
    }

    confirm = async () => {
        let response = await API.confirm();
        this.setState(() => ({message: response.message}))
    }
}
