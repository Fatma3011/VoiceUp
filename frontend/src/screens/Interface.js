import React from 'react';
import {View,Text, Button,StyleSheet,Image,TouchableOpacity} from "react-native";
import {Audio} from "expo-av";
import { useState,useRef } from "react";
import { audioSave } from '../services/audio.services';
import Logo from '../components/Logo'
import BackButton from '../components/BackButton'
import Background from '../components/Background'


const styles = StyleSheet.create({
    container: {
      flex: 1,
      justifyContent: 'center',
      backgroundColor: '#ecf0f1',
      padding: 8,
    },
    record :{
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius:15,
    backgroundColor: 'black',
    marginBottom:9,
    width: 200, 
    height: 30,
      },
      image: {
        width: 24,
        height: 24,
      },
      barre:{
        backgroundColor:'#560CCE',
      }

  });


function Interface({ route, navigation }){
    const [recording, setRecording]=useState();
    const AudioPlayer = useRef(new Audio.Sound());
    const [IsPLaying, SetIsPLaying] = useState(false);
    const [URI, setUri]=useState('');
    const [messages,setMessages]=useState([/*{
        id: 1000,Uri:"", type:"in"
    }*/])
    const [Id,setId]=useState(0)

    const user_id=route.params.idu;//à modifier
    const talk_to_id=route.params.idf//à modifier
    async function startRecording(){
      try{

        console.log('Requesting Submission ....')
        await Audio.requestPermissionsAsync();
        await Audio.setAudioModeAsync({

            allowsRecordingIOS:true, playsInSilentModeIOS:true
        });
        console.log('start recording')
        const recording=new Audio.Recording();
        await recording.prepareToRecordAsync(Audio.RECORDING_OPTIONS_PRESET_HIGH_QUALITY);
        await recording.startAsync();
        setRecording(recording)
        console.log('Recording started ...');

      } catch(err){

        console.error('Failed to start recording ',err)
      }

    }
    async function stopRecording(){

        console.log('Stop recording ');
        setRecording(undefined);
        await recording.stopAndUnloadAsync();
        const uri=recording.getURI();
        console.log("Recording stopped and stored at ",uri);
        setUri(uri);
         setMessages([... messages,{
             id: Id,Uri:uri, type:"out",time:new Date().toLocaleString('en-GB', { timeZone: 'UTC' })
         }])
        setId(Id+1)
       // console.log(messages)
        console.log(uri);
          audioSave(uri,user_id,talk_to_id).then((response)=>{console.log("success");console.log(response)})
                        .catch((e)=>{console.log("error");})
    }


    const PlayRecordedAudio = async (UR) => {
        try {
          // Load the Recorded URI
          await AudioPlayer.current.loadAsync({ uri: UR}, {}, true);
          // Get Player Status
          const playerStatus = await AudioPlayer.current.getStatusAsync();
    
          // Play if song is loaded successfully
          if (playerStatus.isLoaded) {
            if (playerStatus.isPlaying === false) {
              AudioPlayer.current.playAsync();
              SetIsPLaying(true);
            }
          }
        } catch (error) {
          console.log(error);
        }
      };
      // Function to stop the playing audio
  const StopPlaying = async () => {
    try {
      //Get Player Status
      const playerStatus = await AudioPlayer.current.getStatusAsync();

      // If song is playing then stop it
      if (playerStatus.isLoaded === true)
        await AudioPlayer.current.unloadAsync();

      SetIsPLaying(false);
    } catch (error) {
      console.log(error);
    }
  };

 const onpress=(rec)=>{
   SetIsPLaying(false);
       if(IsPLaying)
       {StopPlaying()}
       else
       PlayRecordedAudio(rec)
  }
return(

<View style={{flex: 1}} >
  <View style={styles.barre}>
  <TouchableOpacity onPress={() => navigation.navigate('FriendsList',{id:user_id})}>
<Image
        style={styles.image}
        source={require('../assets/arrow_back.png')}
      /></TouchableOpacity>
<View style={{height:"6%",justifyContent: 'center', alignItems:"center"}}>
  <Text><b>{route.params.user}</b></Text>
</View>
  </View>

<View style={styles.container}>

{messages.length > 0 ? (
        <>
          <View>
          {messages.map((record) => (< View key={record.id}><Text style={{color:"black"}}  >{record.time}</Text>
              <View style={styles.record} >
                  <View  style={{borderRadius:12}}>
                      <Button color="#560CCE" title="" onPress={()=>onpress(record.Uri)}></Button>
                  </View>
              </View></View>)  
               
          )}
          </View>
        </>
      ) : (
       <Text>Say Hello!</Text>
      )}
     

</View>
<View style={{position: 'absolute', left: 0, right: 0, bottom: 0}}>
<Button color="#560CCE" title={recording?'Stop Recording':'Start Recording'}
    onPress={recording ? stopRecording :startRecording }>
   
</Button>
</View>
</View>

);
}
export default Interface;
