import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { NavLink } from 'react-router-dom';
import  classes from './Header.module.css';
import { useHistory } from "react-router";
import { loginActions } from '../store/login-slice';

const Header = () => {
    const[isAdmin, setIsAdmin] = useState(false);
    const logInUser = useSelector(state => state.login)
    const history = useHistory();
    const dispatch = useDispatch();


    useEffect( ()=> {
  
            const user = JSON.parse(localStorage.getItem('logInUser'));
            if (user) {
                setIsAdmin(user.role === 'ADMIN');
            }
            console.log(logInUser);
        
    }, [])

    const onLogOutHandler = () => {
      //localStorage.setItem('logInUser',"");
      dispatch(
        loginActions.setLogInUser( {userId:'', email:'', token:'', role:''})
      );
      localStorage.clear();
        history.push('/');
    }

    return (
        <header className={classes.header}>
          <div classes={classes.logo}><h1>Library Managmeny System</h1></div>
          <nav className={classes.nav}>
            <ul>
              <li>
                { logInUser&&<NavLink to="/books" activeClassName={classes.active}>
                  All Books
                </NavLink>}
              </li>
              {(logInUser.role === 'ADMIN' || isAdmin) && <li>
                <NavLink to="/new-book" activeClassName={classes.active}>
                 Add a Book
                </NavLink>
              </li>}
            {(logInUser.role === 'ADMIN' || isAdmin) && <li>
            <NavLink to="/bookrequest" activeClassName={classes.active}>
                Book Requests
            </NavLink>
            </li>}
              <li>
                {logInUser&&<NavLink to="/mybooks" activeClassName={classes.active}>
                  mybooks 
                </NavLink>}
              </li>
                <button onClick={onLogOutHandler} className={classes.btnheader}>
                  Log Out
                </button>
            </ul>
          </nav>
        </header>
      );
};

export default Header;
