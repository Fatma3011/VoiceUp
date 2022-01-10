import React, { useState ,useEffect} from 'react'
import { TouchableOpacity, StyleSheet, View ,FlatList} from 'react-native'
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



//const dummydata = ListFriends();
/*const liste = [
    {

    id:"8",
    nom_user:"rania",
    email:"rani555a@gmail",
    motdepasse:"dddd"
    },
    {

    id:"9",
    nom_user:"nourhene",
    email:"ccccc@gmail",
    motdepasse:"kjkj"
    },
   {

    id:"2",
    nom_user:"sawsen",
    email:"rani555a@gmail",
    motdepasse:"dddd"
    },
    {
      id:"6",
      nom_user:"ichrak",
      email:"fff@gmail",
      motdepasse:"klklk"
    }

  ]
*/

/*const dummydata = [
    {
      id: 1,
      name: "orange card",
      color: "orange",
    },
    {
      id: 2,
      name: "red card",
      color: "red",
    },
    {
      id: 3,
      name: "green card",
      color: "green",
    },
    {
      id: 4,
      name: "blue card",
      color: "blue",
    },
    {
      id: 5,
      name: "cyan card",
      color: "cyan",
    },
  ];*/
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
    fetch("http://localhost:8080/Message/ListUsers/14").then(res=>
    console.log(res.json())
    
    )

    
  }
 


    const id = route.params;
    
    useEffect(()=>{const friends=ListFriends(14)
      .then(res=>{setData(res.data);console.log(res.data);})
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
                     onPress={() => navigation.navigate('RegisterScreen',item.id)}
                     >
                     <Text style = {styles.text}>
                        {item.nom_User}
                     </Text>
                  </TouchableOpacity>
               ))
            }
         </View>
    </View>
    </Background>
 
  )


}
