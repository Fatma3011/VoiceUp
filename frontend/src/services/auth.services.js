import request from '../utils/client.utils'

//signUp
export async function register(login){
    try {
        const response = await request({
            method: 'POST',
            url: '/auth/signup',
            data: login, 
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
            url: '/auth/signin',
            data: login, 
        })
        console.log(response);
                return response

    } catch (error) {
        throw (error.response || error.message)
    }
}
