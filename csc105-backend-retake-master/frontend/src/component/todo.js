import { useState } from "react";
import "./TodoItem.css";

const Todo = ({ todo, handleEdit, handleDelete, handleFinish }) => {
  const [content, setContent] = useState(todo.content);
  const [description, setDescription] = useState(todo.description);
  const [date, setDate] = useState(formatDate(todo.date));
  const [isEdit, setEdit] = useState(false);
  const editTogger = () => {
    if (isEdit) {
      setContent(todo.content);
      setDescription(todo.description);
      setDate(todo.date);
    }
    setEdit(!isEdit);
  };
  const editSubmit = (event) => {
    event.preventDefault();
    if (content !== "" && description !== "" && date !== "") {
      handleEdit({
        id: todo.id,
        content: content,
        description: description,
        date: date,
      });
      setEdit(!isEdit);
    }
  };
  return (
    <div className={`todoitem ${todo.is_finish ? "isFinish" : "isNotFinish"}`}>
      {isEdit ? (
        <form>
          <div>
            <input
              type="text"
              value={content}
              onChange={(e) => setContent(e.target.value)}
              placeholder="content"
            />
          </div>
          <div>
            <textarea
              style={{
                width: "40vw",
              }}
              type="text"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="description"
            />
          </div>
          <div>
            <input
              type="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
            />
          </div>
        </form>
      ) : (
        <div>
          <div style={{ margin: ".25rem" }}>{todo.content}</div>
          <div style={{ margin: ".25rem" }}>{todo.description}</div>
          <div style={{ margin: ".25rem" }}>{todo.date}</div>
        </div>
      )}
      <div>
        <div>
          {isEdit ? <button onClick={editSubmit}>Save</button> : <div></div>}
          <button onClick={editTogger}>{isEdit ? "Cancel" : "Edit"}</button>
        </div>
        <div>
          <button
            onClick={() => {
              handleDelete(todo.id);
            }}
          >
            Delete
          </button>
        </div>
        <div>
          <button
            onClick={() => {
              handleFinish(todo.id);
            }}
          >
            Done!!
          </button>
        </div>
      </div>
    </div>
  );
};
export default Todo;
function formatDate(date) {
  var d = new Date(date),
    month = "" + (d.getMonth() + 1),
    day = "" + d.getDate(),
    year = d.getFullYear();

  if (month.length < 2) month = "0" + month;
  if (day.length < 2) day = "0" + day;

  return [year, month, day].join("-");
}
