import request from '../utils/client.utils'

//signUp
export async function register(login){
    try {
        const response = await request({
            method: 'POST',
            url: 'user/add',
            data: login, 
            headers: { 'Access-Control-Allow-Origin': true },
        })
        console.log("step1");
        console.log(response);
                return response

    } catch (error) {
        throw (error.response || error.message)
    }
}
export async function signin(login){
    try{

        const response = await request({
            method: 'POST',
            url: 'user/loginUser',
            data: login, 
            headers: { 'Access-Control-Allow-Origin': true },

        })
        console.log(response);
                return response

    } catch (error) {
        throw (error.response || error.message)
    }
}
