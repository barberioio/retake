import { useEffect, useState } from "react";
import axios from "../axios";
import InsertForm from "../component/insertForm";
import Todo from "../component/todo";
import "./home.css";
const Landing = () => {
  const [todoList, setTodoList] = useState([]);
  useEffect(() => {
    fetchTodoList();
  }, []);
  const fetchTodoList = () => {
    axios
      .get("/TodoServlet")
      .then((res) => {
        setTodoList(res.data);
      })
      .catch((error) => {
        alert(error.response.message);
      });
  };

  const addTodo = ({ content, description, date }) => {
    const formData = new FormData();
    formData.append("content", content);
    formData.append("description", description);
    formData.append("date", date);
    axios
      .post("/TodoServlet", formData)
      .then((res) => {
        setTodoList(res.data);
      })
      .catch((error) => {
        alert(error.response.data.message);
      });
  };

  const editTodo = ({ id, content, description, date }) => {
    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);
    formData.append("description", description);
    formData.append("date", date);
    axios
      .put("/TodoServlet", formData)
      .then((res) => {
        setTodoList(res.data);
      })
      .catch((error) => {
        alert(error.response.data.message);
      });
  };
  const deleteTodo = (id) => {
    axios
      .delete("/TodoServlet", { params: { id: id } })
      .then((res) => {
        setTodoList(res.data);
      })
      .catch((error) => {
        alert(error);
      });
  };
  const setFinish = (id) => {
    const formData = new FormData();
    formData.append("id", id);
    axios
      .patch("/TodoServlet", formData)
      .then((res) => {
        setTodoList(res.data);
      })
      .catch((error) => {
        alert(error);
      });
  };

  return (
    <div className="container">
      <div className="title"> My Todo List </div>
      <InsertForm key={"insertForm"} addHandle={addTodo} />
      {todoList.map((el) => {
        return (
          <div>
            <Todo
              key={el.id}
              todo={el}
              handleDelete={deleteTodo}
              handleEdit={editTodo}
              handleFinish={setFinish}
            />
          </div>
        );
      })}
    </div>
  );
};

export default Landing;
