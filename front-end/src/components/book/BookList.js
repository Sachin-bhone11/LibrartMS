import { useState, useEffect, useRef } from "react";
import Book from "./Book";
import axios from 'axios';
import LoadingSpinner from "../UI/LoadingSpinner";
import classes from './NewBook.module.css';
import { useSelector } from "react-redux";



const BookList = () => {
    const[books, setBooks] = useState();
    const [isLoading, setIsLoading] = useState(false);
    const [userData, setUserData] = useState();
    const authorInputRef = useRef();
    const logInUser = useSelector(state => state.login)
   
    useEffect(() => {
        setIsLoading(true)
        axios.get('http://localhost:8080/api/v1/book').then((response) => {
            console.log(response.data);
            setIsLoading(false)
            setBooks(response.data);
         
          }).then((err)=>{
            setIsLoading(false)
              console.log(err);
          });
          console.log("***********");
          if(!logInUser.userId){
            const user = JSON.parse(localStorage.getItem('logInUser'));
            console.log(user);
            setUserData(user);
          } else {
            setUserData(logInUser);
          }
          console.log(userData);
      // console.log(logInUser);
    }, []);

    if (isLoading) {
        return(
            <div className='centered'>
                <LoadingSpinner/>
            </div>
        )
    }


  // console.log(books);
    return (
      <div>
        <form className={classes.form}>
          <div className={classes.control}>
            <label htmlFor="author">Search Books by Name , Author</label>
            <input type="text" id="author" ref={authorInputRef} />
           <br/>
          <button className='btn'>  Search Book  </button>
    
          </div>
        </form>
        {books &&
          books.map((item) => (
            <Book
              key={item.id}
              id={item.id}
              name={item.name}
              author={item.author}
              availableQuantity={item.quantity}
            />
          ))}
      </div>
    );
}

export default BookList;
