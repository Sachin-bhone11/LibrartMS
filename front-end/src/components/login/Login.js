import classes from '../book/NewBook.module.css';
import Card from '../UI/Card';
import LoadingSpinner from '../UI/LoadingSpinner';
import { useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import { loginActions } from '../store/login-slice';
import axios from 'axios';
import { useHistory } from 'react-router';
import { useEffect } from 'react';

const Login = (props) => {

    const history = useHistory();
    const logInUser = useSelector(state => state)
    const dispatch = useDispatch();
    

    const emailInputRef = useRef();
    const passwordInputRef = useRef();

    useEffect(()=> {
        
    }, []) 

    const submitFormHandler = () => {
    //    event.preventdefault();
        const email = emailInputRef.current.value;
        const password = passwordInputRef.current.value;
        console.log(email, password);


        axios.post('http://localhost:8080/api/v1/user/login',{
            email: email,
            password: password
          }).then(response => {
              const data = response.data;
              console.log(data);
              localStorage.setItem('logInUser', JSON.stringify(data));
              dispatch(
                loginActions.setLogInUser({
                  userId: data.userID,
                  email: data.email,
                  token: data.token,
                  role: data.role,
                })
              );
              console.log('testing data')
              console.log(logInUser)
              history.push('/books')
          })
     
          
          
       
    }

    const onRegisterHandler = () => {
      history.push('/register');
    }

    return (
        <Card>
          
        <div className={classes.control}>
          <label htmlFor='name'>Email</label>
          <input id='text' rows='5' ref={emailInputRef}/>
        </div>
        <div className={classes.control}>
          <label htmlFor='author'>Password</label>
          <input type='password' id='password' ref={passwordInputRef} />
        </div>
        <div className={classes.actions}>
        <button onClick={onRegisterHandler}  className='btn'>Register</button>
          <button onClick={submitFormHandler}  className='btn'>Log In</button>
        </div>
    </Card>
    );
}

export default Login;