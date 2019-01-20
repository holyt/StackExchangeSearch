import React, { Component } from "react";
import ReactDOM from "react-dom";
import Question from "./Question.jsx";


function QuestionsLIst(props) {
    const questions = props.questions;

    const listQuestions = questions.map((question) =>
        < Question key={question.question_id} question={question}/>    );
    return (
        <table className="table">
            <thead className="thead-dark">
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Title</th>
                <th scope="col">Сreator</th>
                <th scope="col">Answers</th>
            </tr>
            </thead>
            <tbody>
                {listQuestions}
            </tbody>
        </table>
    );
}

export default QuestionsLIst;