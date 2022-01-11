import { TouchableOpacity, StyleSheet, View ,FlatList,Alert,Image} from 'react-native'
import { Text } from 'react-native-paper'
import Background from '../components/Background'
import Logo from '../components/Logo'
import Header from '../components/Header'
import Button from '../components/Button'
import TextInput from '../components/TextInput'
import BackButton from '../components/BackButton'
import { theme } from '../core/theme'
import {useFormik} from 'formik';
import test, { ListFriends } from '../services/ListingFriends'
import axios from "axios";
import User from '../components/UserLogo'
import React, { useState ,useEffect} from 'react';






const styles = StyleSheet.create({
    container: {
      flex: 1,
      paddingTop: 22
     },
     item: {
       padding: 10,
       fontSize: 18,
       height: 44,
     },
  })


  

export default function FriendsList({ route, navigation }) {
  const list_ami=[];
  const [data,setData]=React.useState([]);
 // console.log(data);
  function test3(){
    fetch("http://localhost:8080/Message/ListUsers/id").then(res=>
    console.log(res.json())
    
    )

    
  }
 


    const id = route.params.id;
    console.log(id)
    useEffect(()=>{const friends=ListFriends(id)
      .then((res)=>{
        var arr=res.data;
        var distinct = [];
        var values=[];
        for (var i = 0; i < arr.length; i++){
          if (arr[i].id  in distinct){
          }
          else{           
             distinct.push(arr[i].id);
              values.push(arr[i]);
          }
        };
          
        setData(values);
        console.log("data",data);
        console.log(distinct);
      }
        )
    .catch(e=>console.log(e));},[]);

  return (
    <Background>
      <BackButton goBack={navigation.goBack} />
      <Logo />
      <Header>Talk with your friends !</Header>
      <View style={styles.container}>

      <View>
            {
               data.map((item, index) => (
                  <TouchableOpacity
                     key = {item.id}
                     style = {styles.container}
                     onPress={() => navigation.navigate('Interface',{idf:item.id,idu:id, user: item.nom_User})}
                 
                     >
                     <User></User>
                     <Text style = {{}}>
                        {item.nom_User}
                     </Text>
                     
                  </TouchableOpacity>
               ))
            }
         </View>
         <Button
         style={{marginTop: "90%" }}
        mode="outlined"
        onPress={() =>
          navigation.reset({
            index: 0,
            routes: [{ name: 'StartScreen' }],
          })
        }
      >
        Logout
      </Button>
    </View>
    </Background>
 
  )


}
