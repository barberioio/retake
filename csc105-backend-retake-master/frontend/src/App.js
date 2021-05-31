import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Landing from "./view/landing";
function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/">
            <Landing />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
