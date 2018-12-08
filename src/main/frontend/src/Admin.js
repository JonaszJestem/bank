import React, {Component} from 'react';
import {ListGroup} from "reactstrap";
import API from "./API";
import TransferItem from "./TransferItem";

class History extends Component {
    state = {
        transfers: []
    };

    async componentDidMount() {
        const transfers = await API.adminTransfers();
        console.log(transfers);
        if (transfers) {
            this.setState(() => ({transfers: transfers}))
        }
    }


    confirm = async (event) => {
        let id = event.target.value;
        console.log(id);
        let response = await API.adminConfirm(id);
        console.log(response);
    };


    render() {
        let history = "";
        if (this.state.transfers) {
            console.log(this.state.transfers);
            history = this.state.transfers.map((transfer) => {
                return <><TransferItem {...transfer}/>
                    <button value={transfer.id} onClick={this.confirm}>Confirm</button>
                </>
            });
        }
        return <ListGroup>
            {history}
        </ListGroup>
    }
}

export default History;