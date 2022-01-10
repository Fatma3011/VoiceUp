import request from '../utils/client.utils'

//signUp
export async function ListFriends(id){
    try {

        const response = await request({
            method: 'GET',
            url: `Message/ListUsers/${id}`,
            headers: { 'Access-Control-Allow-Origin': true },

            
        })
        console.log("step1");
        console.log(response);
        return response

    /*
        const response = await request({
            method: 'POST',
            url: '/Message/ListUsers',
            Data
           // Headers:"Access-Control-Allow-Origin"
        })
        console.log("Friends list");
        console.log(response);
                return response
*/
    } catch (error) {
        throw (error.response || error.message)
    }
}
import axios from "axios";
import React from "react";



