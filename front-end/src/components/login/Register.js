import classes from '../book/NewBook.module.css';
import Card from '../UI/Card';
import LoadingSpinner from '../UI/LoadingSpinner';
import { useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import { loginActions } from '../store/login-slice';
import axios from 'axios';
import { useHistory } from 'react-router';
import { useEffect } from 'react';

const Register = (props) => {

    const history = useHistory();
    

    const emailInputRef = useRef();
    const passwordInputRef = useRef();
    const firstNameInputRef = useRef();
    const lastNameInputRef = useRef();


    useEffect(()=> {
        
    }, []) 

    const submitFormHandler = () => {
    //    event.preventdefault();
        const email = emailInputRef.current.value;
        const password = passwordInputRef.current.value;
        const firstName = firstNameInputRef.current.value;
        const lastname = lastNameInputRef.current.value;
        console.log(email, password, firstName, lastname);


        axios.post('http://localhost:8080/api/v1/user/register',{
            email: email,
            firstName: firstName,
            id: 0,
            lastName: lastname,
            role: "USER",
            userPassword: password
          }).then(response => {
        
              console.log(response.data);
              alert('User Created !!!!');
              history.push('/login')
          })
     
          
          
       
    }

    return (
        <Card>
          
        <div className={classes.control}>
          <label htmlFor='firstName'>firstName</label>
          <input id='text' rows='5' ref={firstNameInputRef}/>
        </div>
        <div className={classes.control}>
          <label htmlFor='lastName'>Last Name</label>
          <input id='text' rows='5' ref={lastNameInputRef}/>
        </div>
        <div className={classes.control}>
          <label htmlFor='email'>Email</label>
          <input type="email" id='text' rows='5' ref={emailInputRef}/>
        </div>
        <div className={classes.control}>
          <label htmlFor='password'>Password</label>
          <input type='password' id='password' ref={passwordInputRef} />
        </div>
        <div className={classes.actions}>
          <button onClick={submitFormHandler}  className='btn'>Register</button>
        </div>
    </Card>
    );
}

export default Register;