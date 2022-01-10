import React, { useState } from 'react'
import { TouchableOpacity, StyleSheet, View } from 'react-native'
import { Text } from 'react-native-paper'
import Background from '../components/Background'
import Logo from '../components/Logo'
import Header from '../components/Header'
import Button from '../components/Button'
import TextInput from '../components/TextInput'
import BackButton from '../components/BackButton'
import { theme } from '../core/theme'
import { emailValidator } from '../helpers/emailValidator'
import { passwordValidator } from '../helpers/passwordValidator'
import {useFormik} from 'formik';
import * as Yup from 'yup';
import {signin} from "../services/auth.services"


export default function LoginScreen({ navigation }) {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [emailError, setEmailError] = useState('')
  const [passwordError, setPasswordError] = useState('')
  
  const validationSchema = (values) => {
      const emailError = emailValidator(values.email)
      const passwordError = passwordValidator(values.password)
      if (emailError || passwordError) {
        setEmailError(emailError )
        setPasswordError(passwordError )
      
        return 
      }
    return 1;
  }


  const initialValues = {
    email: '',
    password: '',
  };
  const onSubmit = values => {
    console.log(values.email);
    console.log(values.password);
    const k=validationSchema(values);
    if (k==1){
      console.log("values.email");

      const registered = {
        email: values.email,
        password: values.password,
   };
      signin(registered).then(     
        navigation.reset({
          index: 0,
          routes: [{ name: 'Dashboard' }],
        })
        );
    }

  };

  const formik = useFormik({
    initialValues,
    onSubmit,
  });
  const {
    values,
    errors,
    handleChange,
    isSubmitting,
    handleSubmit,
  } = formik;


  return (
    <Background>
      <BackButton goBack={navigation.goBack} />
      <Logo />
      <Header>Welcome back.</Header>
      <TextInput
        label="Email"
        returnKeyType="next"
        value={values.email}
        onChangeText={handleChange('email')}
        error={!!emailError}
        errorText={emailError}
        autoCapitalize="none"
        autoCompleteType="email"
        textContentType="emailAddress"
        keyboardType="email-address"
      />
      <TextInput
        label="Password"
        returnKeyType="done"
        value={values.password}
        onChangeText={handleChange('password')}
        error={!!passwordError}
        errorText={passwordError}
        secureTextEntry
      />
      <View style={styles.forgotPassword}>
        <TouchableOpacity
          onPress={() => navigation.navigate('ResetPasswordScreen')}
        >
          <Text style={styles.forgot}>Forgot your password?</Text>
        </TouchableOpacity>
      </View>
      <Button mode="contained" onPress={handleSubmit}>
        Login
      </Button>
      <View style={styles.row}>
        <Text>Don’t have an account? </Text>
        <TouchableOpacity onPress={() => navigation.replace('RegisterScreen')}>
          <Text style={styles.link}>Sign up</Text>
        </TouchableOpacity>
      </View>
    </Background>
  )
}

const styles = StyleSheet.create({
  forgotPassword: {
    width: '100%',
    alignItems: 'flex-end',
    marginBottom: 24,
  },
  row: {
    flexDirection: 'row',
    marginTop: 4,
  },
  forgot: {
    fontSize: 13,
    color: theme.colors.secondary,
  },
  link: {
    fontWeight: 'bold',
    color: theme.colors.primary,
  },
})
