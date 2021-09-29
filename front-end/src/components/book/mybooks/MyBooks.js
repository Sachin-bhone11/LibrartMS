import axios from "axios";
import { useState, useEffect } from "react";
import MyBookDetail from "./MyBookDetail";
import LoadingSpinner from "../../UI/LoadingSpinner";
import classes from './MyBooks.module.css';

const MyBooks = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [myBooks, setMyBooks] = useState();

    useEffect(()=> {
        setIsLoading(true)
        const user = JSON.parse(localStorage.getItem('logInUser'));
        axios
          .get(`http://localhost:8080/api/v1/book/userbooks?userId=${user.userID}&status=ISSUED`)
          .then((response) => {
            console.log(response.data);
            setIsLoading(false);
            setMyBooks(response.data);
          })
          .then((err) => {
            setIsLoading(false);
            console.log(err);
          });
    }, [])


    if (isLoading) {
      return(
          <div className='centered'>
              <LoadingSpinner/>
          </div>
      )
    
  }

  // if (!bookrequest) {
  //   return(
  //       <div className='centered'>
  //           <h3>NO REQUESTS</h3>
  //       </div>
  //   )
  // }

    return(<div>

 {myBooks &&
          myBooks.map((item) => (
            <MyBookDetail
              key={item.bookRequest.id}
              bookRequest={item.bookRequest}
              user={item.user}
              author={item.author}
              book={item.book}
            />
          ))}
        
    </div>);
}

export default MyBooks;