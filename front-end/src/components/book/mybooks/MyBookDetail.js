import axios from "axios";

import classes from './MyBookDetail.module.css';
const MyBookDetail = (props) => {
   
   

    const onReturnHandler = () => {
        const requestUrl = `http://localhost:8080/api/v1/bookrequest/admin/action?reruestId=${props.bookRequest.id}&action=RETURN`;
        console.log(requestUrl);
        axios.put(`http://localhost:8080/api/v1/bookrequest/admin/action?reruestId=${props.bookRequest.id}&action=RETURN`).then(response => {
            console.log('Return Book Request Sent!!');
            alert('Return Book Request Sent!!');
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
        
          <button onClick={onReturnHandler} className={classes.btn_request}>Return Book</button>
              
        </li>
      </div>
    );
}



export default MyBookDetail;