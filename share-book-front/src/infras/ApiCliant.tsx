import axios from "axios";

export function init() {
  axios.defaults.baseURL = "http://localhost:8080";
}
