import { Fragment } from "react";
import classes from './Library.module.css';
import Header from "./Header";
const Library = (props) => {
    return(
        <Fragment>
<Header/>
<main className={classes.main}>{props.children}</main>
            </Fragment>
    )
}

export default Library;