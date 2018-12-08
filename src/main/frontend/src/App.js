import React, {Component} from 'react';
import './App.css';
import {Nav, Navbar, NavbarBrand, NavItem, NavLink} from "reactstrap";
import {BrowserRouter, Route} from "react-router-dom";
import Home from "./Home";
import Signup from "./Signup";
import Login from "./Login";
import History from "./History";
import Transfer from "./Transfer";
import Admin from "./Admin";

class App extends Component {
    render() {
        return (
            <div className="App mx-auto col-md-8">
                <BrowserRouter>
                    <>
                        <Navbar color="light">
                            <NavbarBrand href="/">Bank</NavbarBrand>
                            <Nav>
                                <NavItem>
                                    <NavLink href="/transfer">Przelew</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink href="/history">Historia</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink href="/login">Logowanie</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink href="/registration">Rejestracja</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink href="/admin">Admin</NavLink>
                                </NavItem>
                            </Nav>
                        </Navbar>

                        <Route exact path="/" component={Home}/>
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/history" component={History}/>
                        <Route exact path="/transfer" component={Transfer}/>
                        <Route path="/registration" component={Signup}/>
                        <Route path="/admin" component={Admin}/>
                    </>
                </BrowserRouter>
            </div>
        );
    }
}


export default App;
