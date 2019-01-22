import React, { Component } from "react";

class NextPage extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return <button type="button" className="btn btn-primary" onClick={this.props.handleNextPage}>Next page</button>;
    }
}

export default NextPage;