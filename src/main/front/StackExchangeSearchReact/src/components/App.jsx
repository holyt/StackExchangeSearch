import React, { Component } from "react";
import ReactDOM from "react-dom";
import axios from "axios";


import QuestionsList from "./QuestionsList.jsx";
import NextPage from "./NextPage.jsx";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchString: '',
            questions: [],
            currentPage: 0,
            hasMore: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleNextPage = this.handleNextPage.bind(this);
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
                    questions: [],
                    currentPage: 0,
                    hasMore: false
                });
            return;
        }

        this.searchRequest(this.state.searchString, 1)
    }

    handleNextPage()
    {
        let nextPage = this.state.currentPage + 1;
        this.searchRequest(this.state.searchString, nextPage)

    }

    searchRequest(searchString, page)
    {
        let that = this;
        console.log()
        axios.get('search', {params: {searchString: searchString, page: page}}).then(response =>
            that.setState({questions: response.data.items, hasMore: response.data.has_more, currentPage: page})
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
                    <QuestionsList questions={this.state.questions} />
                    { this.state.hasMore ? <NextPage handleNextPage={this.handleNextPage} /> : null }
                </div>
           </div>
        );
    }
}


const wrapper = document.getElementById("app");
wrapper ? ReactDOM.render(<App />, wrapper) : false;