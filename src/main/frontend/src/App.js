import React, {Component} from 'react';
import './App.css';
import {Nav, Navbar, NavbarBrand, NavItem, NavLink} from "reactstrap";
import {BrowserRouter, Route} from "react-router-dom";
import Home from "./Home";
import Signup from "./Signup";
import Login from "./Login";

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
                        <Route path="/registration" component={Signup}/>
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


export default App;
