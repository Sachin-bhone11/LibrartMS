import { Fragment, useRef, useState } from "react";
import { useHistory } from "react-router";
import classes from './NewBook.module.css';
import LoadingSpinner from "../UI/LoadingSpinner";
import Card from "../UI/Card";
import axios from "axios";

const NewBook = (props) => {

    const history = useHistory();


    const authorInputRef = useRef();
    const nameInputRef = useRef();
    const quantityRef = useRef();
  
    function submitFormHandler(event) {
      event.preventDefault();
    
  
      const enteredAuthor = authorInputRef.current.value;
      const enteredName = nameInputRef.current.value;
      const enteredQuantity = quantityRef.current.value
  
      // optional: Could validate here

      axios
        .post("http://localhost:8080/api/v1/book", {
          author: enteredAuthor,
          name: enteredName,
          quantity: enteredQuantity,
        })
        .then((response) => {
        //  setPost(response.data);
        history.push('/books');
        });
  
    }


    return (<Fragment>
    <Card>
      <form  className={classes.form} onSubmit={submitFormHandler}>
        {props.isLoading && (
          <div className={classes.loading}>
            <LoadingSpinner />
          </div>
        )}
     
        <div className={classes.control}>
          <label htmlFor='name'>Text</label>
          <input id='text' rows='5' ref={nameInputRef}/>
        </div>
        <div className={classes.control}>
          <label htmlFor='author'>Author</label>
          <input type='text' id='author' ref={authorInputRef} />
        </div>
        <div className={classes.control}>
          <label htmlFor='quantity'>Quantity</label>
          <input type='text' id='quantity' ref={quantityRef} />
        </div>
        <div className={classes.actions}>
          <button  className='btn'>Add Book</button>
        </div>
      </form>
    </Card>
    </Fragment>);
}


export default NewBook;