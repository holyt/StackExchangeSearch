import React, { Component } from "react";
import moment from "moment"

class Question extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr className={"question" + (this.props.question.is_answered ? ' is_answered' : '')}>
                <th scope="row">{moment(this.props.question.creation_date * 1000).format('LLL') }</th>
                <td><a href={this.props.question.link}  target="_blank">{this.props.question.title}</a></td>
                <td>{this.props.question.owner.display_name}</td>
                <td>{this.props.question.answer_count}</td>
            </tr>
        );
    }
}

export default Question;