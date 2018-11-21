import React, {Component} from 'react';
import './App.css';
import {Button, Form, FormGroup, Input, Label, Nav, Navbar, NavbarBrand, NavItem, NavLink} from "reactstrap";
import {BrowserRouter, Route} from "react-router-dom";
import API from "./API";

class App extends Component {
    state = {
        token: ""
    };

    render() {
        return (
            <div className="App mx-auto col-md-8">
                <BrowserRouter>
                    <>
                        <Navbar color="light">
                            <NavbarBrand href="/">Bank</NavbarBrand>
                            <Nav>
                                <NavItem>
                                    <NavLink href="/login">Logowanie</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink href="/registration">Rejestracja</NavLink>
                                </NavItem>
                            </Nav>
                        </Navbar>

                        <Route exact path="/" component={Home}/>
                        <Route exact path="/login" component={() => <Login setToken={this.setJWTToken}/>}/>
                        <Route path="/registration" component={Singup}/>
                    </>
                </BrowserRouter>
            </div>
        );
    }

    setJWTToken = (token) => {
        console.log("Setting token!");
        this.setState(() => ({token}));
    }
}

class Home extends Component {
    render() {
        return <div>Home</div>
    }
}

class Login extends Component {
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

class Singup extends Component {
    state = {
        login: "",
        email: "",
        password: "",
        response: ""
    };

    handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const response = API.signup(formData);

        this.setState(() => ({response}))
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

export default App;
