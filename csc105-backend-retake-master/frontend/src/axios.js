import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/backend_war_exploded/",
  withCredentials: false,
});

export default instance;
