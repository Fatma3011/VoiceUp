import request from '../utils/client.utils'

//signUp
export async function ListFriends(id){
    try {
        const response = await request({
            method: 'POST',
            url: '/Message/ListUsers',
            data : id
        })
        console.log("Friends list");
        console.log(response);
                return response

    } catch (error) {
        throw (error.response || error.message)
    }
}

