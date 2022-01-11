import React, { useState } from 'react'
import { View, StyleSheet, TouchableOpacity } from 'react-native'
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
import { nameValidator } from '../helpers/nameValidator'
import {useFormik} from 'formik';
import { register } from '../services/auth.services'

export default function RegisterScreen({ navigation }) {
  const [nom_User, setNom_User] = useState('')
  const [email, setEmail] = useState('')
  const [motdepasse, setPassword] = useState('')
  const [emailError, setEmailError] = useState('')
  const [passwordError, setPasswordError] = useState('')
  const [nameError, setNameError] = useState('')
  const validationSchema = (values) => {
    const emailError = emailValidator(values.email)
    const passwordError = passwordValidator(values.motdepasse)
    const nameError = nameValidator(values.nom_User)
    if (emailError || passwordError || nameError) {
      setEmailError(emailError )
      setPasswordError(passwordError )
      setNameError(nameError)
      return 
    }
  return 1;
}
const initialValues = {
  nom_User:'',
  email: '',
  motdepasse: '',
};
const onSubmit = values => {
  console.log(values.nom_User);
  console.log(values.email);
  console.log(values.motdepasse);
  const k=validationSchema(values);
  if (k==1){
    console.log("values.email");
    const registered = {
      nom_User:values.nom_User,
      email: values.email,
      motdepasse: values.motdepasse,
 };
    register(registered).then(     
      console.log(registered),
      navigation.replace('LoginScreen')
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
    <Header>Create Account</Header> 
    <TextInput
      label="Name"
      returnKeyType="next"
      value={values.nom_User}
      onChangeText={handleChange('nom_User')}
      error={!!nameError}
      errorText={nameError}
    />
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
      value={values.motdepasse}
      onChangeText={handleChange('motdepasse')}
      error={!!passwordError}
      errorText={passwordError}
      secureTextEntry
    />
    <Button mode="contained" onPress={handleSubmit}>
      signup
    </Button>
      <View style={styles.row}>
        <Text>Already have an account? </Text>
        <TouchableOpacity onPress={() => navigation.replace('LoginScreen')}>
          <Text style={styles.link}>Login</Text>
        </TouchableOpacity>
      </View>
    </Background>
  )
}

const styles = StyleSheet.create({
  row: {
    flexDirection: 'row',
    marginTop: 4,
  },
  link: {
    fontWeight: 'bold',
    color: theme.colors.primary,
  },
})
