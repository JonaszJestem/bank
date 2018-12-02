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
        console.log(transfers)
        if (transfers) {
            this.setState(() => ({transfers: transfers}))
        }
    }

    render() {
        let history = this.state.transfers.map((tranfser) => {
            return <TransferItem key={tranfser.id} id={tranfser.id} title={tranfser.title} amount={tranfser.amount}/>
        });
        return <ListGroup>
            {history}
        </ListGroup>
    }
}

export default History;