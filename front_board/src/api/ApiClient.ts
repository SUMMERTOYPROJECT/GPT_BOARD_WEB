import axios from 'axios'
export const apiClinet = axios.create(
    {
<<<<<<< Updated upstream
        baseURL: 'http://localhost:8080',
        // baseURL: 'http://3.34.74.243:8080',
        
=======
        baseURL: 'http://3.34.74.243:8080',
>>>>>>> Stashed changes
    }
)