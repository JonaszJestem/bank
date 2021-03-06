import React, {Component} from 'react';
import {ListGroup} from "reactstrap";
import API from "./API";
import TransferItem from "./TransferItem";

class History extends Component {
    state = {
        transfers: []
    };

    async componentDidMount() {
        const transfers = await API.history();
        console.log(transfers);
        if (transfers) {
            this.setState(() => ({transfers: transfers}))
        }
    }
    render() {
        let history = "";
        if (this.state.transfers) {
            console.log("STATE TRANSFERS: " + this.state.transfers);
            history = this.state.transfers.map((transfer) => {
                return <TransferItem {...transfer}/>
            });
        }
        return <ListGroup>
            {history}
        </ListGroup>
    }
}

export default History;