import React, { Component } from "react";
import ReactDOM from "react-dom";
import axios from "axios";


import QuestionsLIst from "./QuestionsLIst.jsx";


class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchString: '',
            questions: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({searchString: event.target.value});
    }

    handleSubmit(event) {
        let that = this;
        event.preventDefault();

        let searchString =  this.state.searchString.trim();
        if (!searchString) {
            that.setState({
                questions: []
            });
            return;
        }

        axios.get('search', {params: {searchString: searchString}}).then(response =>
            that.setState({questions: response.data.items})
        );
    }

    render() {
        return (
            <div>
                <nav className="navbar navbar-light bg-light">
                    <div className="container">
                        <a className="navbar-brand">StackExchange</a>
                        <form className="form-inline" onSubmit={this.handleSubmit}>
                            <input
                                className="form-control mr-sm-2"
                                type="search"
                                placeholder="Search"
                                aria-label="Search"
                                onChange={this.handleChange}
                            />
                            <button className="btn btn-outline-success my-2 my-sm-0"  type="submit">Search</button>
                        </form>
                    </div>
                </nav>

                <div className="container">
                    < QuestionsLIst questions={this.state.questions} />
                </div>
            </div>
        );
    }
}

const wrapper = document.getElementById("app");
wrapper ? ReactDOM.render(<App />, wrapper) : false;