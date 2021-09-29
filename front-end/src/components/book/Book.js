import classes from './Book.module.css';
import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';
const Book = (props) => {
    const[isAdmin, setIsAdmin] = useState(false);
    const [logInUserData, setlogInUserData] = useState();
    const logInUser = useSelector(state => state.login)
   // console.log(logInUser);

    const onEditHandler = () => {
        //TODO///
    }

    useEffect( ()=> {
        if(!logInUser.userId){
            const user = JSON.parse(localStorage.getItem('logInUser'));
            console.log(user);
            setIsAdmin(user.role === 'ADMIN');
            setlogInUserData(user);
            console.log(logInUserData);

          } else {
            setIsAdmin(logInUser.role === 'ADMIN');
            setlogInUserData(logInUser);
            console.log(logInUser);
          }
    }, [])

    const onBookRequestHandler = () => {
        console.log(logInUserData)

        axios.post("http://localhost:8080/api/v1/bookrequest", {
            bookId: props.id,
            quantity: 1,
            status: "NEW",
            userId: logInUserData.userId
          })
        .then((response) => {
            alert('Book Request sent !!!')
        console.log(response)
        });
    }

    const onDeleteHandler = () => {
        const bookData = {
            id: props.id,
            name: props.name,
            quantity: props.availableQuantity,
        author: props.author
          }
        console.log(props
            .id)
            console.log(bookData);
        axios.delete(`http://localhost:8080/api/v1/book/admin?bookId=${ props.id}`)
        .then((response) => {
            alert('Book Deleted !!!')
        console.log(response)
        });
    }

    return(
        <div className={classes.bookdiv}>
    {!(isAdmin) && <div className={classes.bookdiv}>
        <article className={classes.book}>
            <h1 className={classes.book_title}>{props.name}</h1>
            <h2 className={classes.book__price}>{props.author}</h2>

                   <div>
                        <button onClick={onBookRequestHandler} className="btn">Request for Book</button>
                    </div>
        </article>
    </div>}
    { (isAdmin) && <li className={classes.item}>
      <figure>
        <blockquote>
          <p>{props.name}</p>
        </blockquote>
        <figcaption>{props.author}</figcaption>
        <figcaption>Available Quantity : {props.availableQuantity}</figcaption>
      </figure>
      <button className="btn" onClick={onEditHandler}>Edit</button>
      <button className="btn" onClick={onDeleteHandler}>Delete</button>
    </li> }
    </div>
    // </li>
  );

}

export default Book;