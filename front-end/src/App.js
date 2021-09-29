import {Route, Switch} from 'react-router-dom'

import Library from './components/container/Library';
import BookList from './components/book/BookList';
import NewBook from './components/book/NewBook';
import Login from './components/login/Login';
import EditBook from './components/book/EditBook';
import BookRquests from './components/book/BookRequest/BookRequests';
import MyBooks from './components/book/mybooks/MyBooks';
import Register from './components/login/Register';

function App() {
  return (
    <div>
      <Library>
        <Switch>
          <Route path="/" exact component={Login} />
          <Route path="/bookrequest" component={BookRquests} />
          <Route path="/mybooks" component={MyBooks} />
          <Route path="/register" component={Register} />
          <Route path="/login" exact>
            <Login />
          </Route>
          <Route path="/books" exact component={BookList} />
          <Route path="/books/:bookId">
            <EditBook />
          </Route>
          <Route path="/new-book">
            <NewBook />
          </Route>
        </Switch>
      </Library>
    </div>
  );
}

export default App;
