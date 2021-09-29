import axios from "axios";
import { useState, useEffect } from "react";
import classes from './BookRequestDetail.module.css';
const BookRequestDetail = (props) => {
   
    const onApproveHandler = () => {
        let requestUrl;
        if (props.bookRequest.status === 'NEW') {
            requestUrl = `http://localhost:8080/api/v1/bookrequest/admin/action?reruestId=${props.bookRequest.id}&action=ISSUED`;
        }
        if (props.bookRequest.status === 'RETURN') {
            requestUrl = `http://localhost:8080/api/v1/bookrequest/admin/action?reruestId=${props.bookRequest.id}&action=COMPLETE`;
        }
        console.log(requestUrl);

        axios.put(requestUrl).then(response => {
            console.log('Book Request Approved');
            alert('Book Request Approved');
        })
    }



    return (
      <div>
        <li className={classes.item}>
          <figure>
            <blockquote>
              <p>{props.book.name}</p>
            </blockquote>
            <figcaption>{props.book.author}</figcaption>
            <figcaption>
              Available Quantity : {props.book.quantity}
            </figcaption>
            <figcaption>Requested By: {props.user.firstName} {props.user.lastName}</figcaption>
            <figcaption>Request Status: {props.bookRequest.status}</figcaption>
          </figure>
        
          {(props.bookRequest.status==='NEW' || props.bookRequest.status==='RETURN') && <button onClick={onApproveHandler} className={classes.btn_request}>Approve</button>}
          <br/>
     
        </li>
      </div>
    );
}



export default BookRequestDetail;