import React, {Component} from 'react';
import {ListGroupItem} from "reactstrap";

class TransferItem extends Component {

    render() {
        return (
            <ListGroupItem>{this.props.title} : {this.props.amount}</ListGroupItem>
        );
    }
}

export default TransferItem;