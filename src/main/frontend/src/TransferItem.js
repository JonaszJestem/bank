import React, {Component} from 'react';
import {ListGroupItem} from "reactstrap";

class TransferItem extends Component {

    render() {
        return (
            <ListGroupItem>Id: {this.props.id} Title: {this.props.title} Amount: {this.props.amount} Confirmed: {this.props.confirmedByAdmin.toString()}</ListGroupItem>
        );
    }
}

export default TransferItem;