import React, { useState } from 'react'
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
import { ListFriends } from '../services/ListingFriends'



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
  
    // to get user's id 
    const id = route.params;
    const friends = ListFriends(id)
    const [data,setData]=React.useState(friends)
    console.log(friends)
    console.log(id)
  return (
    <Background>
      <BackButton goBack={navigation.goBack} />
      <Logo />
      <Header>Talk with your friends !</Header>
      <View style={styles.container}>
        <FlatList
            data={data}
            keyExtractor={(item) => item.id.toString()}
            renderItem={({item}) => 
            <Text style={styles.item} onPress={()=>{}}>{item.nom_user}</Text>
            
        }
        />
    </View>
    </Background>
  )


}
