//cấu hình lib axios lib hỗ trợ call api từ backend lên client 
import axios from "axios";

const axiosInstant = axios.create({
    //lay dia chi api tu backend -> dia chi root api
    baseURL: "http://localhost:8080/api/v1/",
    headers:{
        "Content-Type": "application/json",
    }
})

export default axiosInstant;