import React, { useState ,useEffect} from 'react';
import { TouchableOpacity, StyleSheet, View ,FlatList,Alert,Image} from 'react-native'
const styles = StyleSheet.create({
    image: {
      width: 60,
      height: 60
    },
  })
export default function User() {
    return <Image source={require('../assets/logo2.png')} style={styles.image} />
  }