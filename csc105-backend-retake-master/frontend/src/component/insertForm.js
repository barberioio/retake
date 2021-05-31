import { useState } from "react";

const InsertForm = ({ addHandle }) => {
  const [content, setContent] = useState("");
  const [description, setDescription] = useState("");
  const [date, setDate] = useState("");

  const insert = (event) => {
    event.preventDefault();
    if (content !== "" && description !== "" && date !== "") {
      addHandle({ content: content, description: description, date: date });
      setContent("");
      setDescription("");
      setDate("");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        flexFlow: "column",
        alignItems: "center",
      }}
    >
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
            onChange={(e) => {
              setDate(e.target.value);
              console.log(date);
              console.log(e.target.value);
            }}
          />
        </div>

        <button onClick={insert}>Add new todo</button>
      </form>
    </div>
  );
};
export default InsertForm;
