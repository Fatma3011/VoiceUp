import axios from 'axios'
import { API_BASE_URL } from '../constants/api.const'

const client = axios.create({ baseURL: API_BASE_URL })
function request(options) {
    // success handler
    function onSuccess (response) {
      console.log('Request Success !')
        return response
    }
  
    // failed handler
    function onFailed (error) {
      console.log('Request Failed !')
      console.log(error)
  
      return Promise.reject(error)
    }
  
    return (
      client(options)
        .then(onSuccess) // success
        .catch(onFailed) // failed
    )
  } 
  
  export default request
