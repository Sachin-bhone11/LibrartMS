import axios from "axios";
import { useState, useEffect } from "react";
import BookRequestDetail from "./BookRequestDetail";
import LoadingSpinner from "../../UI/LoadingSpinner";
import classes from './BookRequestDetail.module.css';

const BookRquests = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [bookrequest, setBookRequest] = useState();

    useEffect(()=> {
        setIsLoading(true)
        axios
          .get('http://localhost:8080/api/v1/bookrequest/userbookrequests')
          .then((response) => {
            console.log(response.data);
            setIsLoading(false);
            setBookRequest(response.data);
          })
          .then((err) => {
            setIsLoading(false);
            console.log(err);
          });
    }, [])

    const onNewRequestHandler = ()=> {
      setIsLoading(true)
      axios
        .get('http://localhost:8080/api/v1/bookrequest/userbookrequestsbystatus?status=NEW')
        .then((response) => {
          console.log(response.data);
          setIsLoading(false);
          setBookRequest(response.data);
        })
        .then((err) => {
          setIsLoading(false);
          console.log(err);
        });
    }

    const onReturnRequestHandler =() => {
      setIsLoading(true)
      axios
        .get('http://localhost:8080/api/v1/bookrequest/userbookrequestsbystatus?status=RETURN')
        .then((response) => {
          console.log(response.data);
          setIsLoading(false);
          setBookRequest(response.data);
        })
        .then((err) => {
          setIsLoading(false);
          console.log(err);
        });
    }

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
       <button onClick={onNewRequestHandler} className={classes.btn_request}>New Requests</button>
       <button onClick={onReturnRequestHandler} className={classes.btn_request}>Return Requests</button>
 {bookrequest &&
          bookrequest.map((item) => (
            <BookRequestDetail
              key={item.bookRequest.id}
              bookRequest={item.bookRequest}
              user={item.user}
              author={item.author}
              book={item.book}
            />
          ))}
        
    </div>);
}

export default BookRquests;