import request from '../utils/client.utils'

//signUp
export async function audioSave( file, id1,id2){
    try {
        const response = await request({
            method: 'POST',
            url: `Message/saveMessage/${id1}/${id2}`,
            data: file, 
            headers: { 'Access-Control-Allow-Origin': true },
        })
        console.log("step1");
        console.log(response);
                return response

    } catch (error) {
        throw (error.response || error.message)
    }
}