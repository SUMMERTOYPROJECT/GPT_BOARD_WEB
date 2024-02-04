import axios from 'axios'
export const apiClinet = axios.create(
    {
        baseURL: 'http://3.34.164.80:8080',
    }
)