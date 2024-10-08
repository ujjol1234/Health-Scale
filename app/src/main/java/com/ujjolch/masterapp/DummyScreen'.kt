package com.ujjolch.masterapp

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.yml.charts.common.extensions.isNotNull
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DummyScreen() {
    var date by remember { mutableStateOf("") }
    var hist by remember {
        mutableStateOf<List<hist>>(emptyList())
    }
    var devices by remember {
        mutableStateOf<List<themistoscale>>(emptyList())
    }
    var userDetails by remember {
        mutableStateOf(UserData())
    }
    var subUserList by remember {
        mutableStateOf<List<subuser>>(emptyList())
    }
    val UserDetailRepository: UserDetailRepository
    UserDetailRepository = UserDetailRepository(Injection.instance())
    var upload by remember {
        mutableStateOf(false)
    }
    val userRepository: UserRepository
        userRepository= UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    LaunchedEffect (upload){
        if(upload) {
            UserDetailRepository.addSubUser("we@gmail.com", subuser("We","Yaga"))
            val subusers = UserDetailRepository.getSubUserList("we@gmail.com")
            UserDetailRepository.uploadDetails("we@gmail.com",
                UserData(173.52,"Male",19),"Wee48c24a0-848a-4327-977c-280cd8a2e892"
            )
            val userd = UserDetailRepository.getUserData("we@gmail.com","Wee48c24a0-848a-4327-977c-280cd8a2e892")

            when(userd){
                is Result.Success -> {
                    userDetails = userd.data
                }
                is Result.Error ->{
                    Log.d("UJERROR","${userd.exception}")
                }
            }

            UserDetailRepository.updatehistlist("we@gmail.com", hist(741.33, 40000, date),"Wee48c24a0-848a-4327-977c-280cd8a2e892")
            val data = UserDetailRepository.gethistlist("we@gmail.com","Wee48c24a0-848a-4327-977c-280cd8a2e892")
//            UserDetailRepository.bindDevice("rohan@gmail.com",themistoscale("Themisto body Scale","f"))
//            val device = UserDetailRepository.getDevices("rohan@gmail.com")
//            when(subusers){
//                is Result.Success -> {
//                    subUserList = subusers.data
//                }
//                is Result.Error ->{
//                    Log.d("UJERROR","${subusers.exception}")
//                }
//            }
            when(data){
                    is Result.Success ->{
                        hist = data.data
                    }
                is Result.Error ->{
                    Log.d("UJERROR","${data.exception}")
                }
            }
//            when(device){
//                is Result.Success ->{
//                    devices = device.data
//                }
//                is Result.Error ->{
//                    Log.d("UJERROR","${device.exception}")
//                }
//            }
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                Date()
            )
            date = currentDate
            upload = !upload

        }) {
            Text(text = "Show Date")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = date)
//        Text(text = "{$subUserList}")
        Text(text = "{$userDetails}")
        Text(text = "${hist}")
        Spacer(modifier = Modifier.height(12.dp))
//        Text(text = "$devices")
    }
}


@Composable
fun DummyScreen2(userDetailsViewModel: UserDetailsViewModel = viewModel()) {
    var date by remember { mutableStateOf("") }
    val hist by userDetailsViewModel.userHist.observeAsState()
    val devices by userDetailsViewModel.devices.observeAsState()
    val SubUserList by userDetailsViewModel.SubUserList.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {

            userDetailsViewModel.getSubUserList()

//            userDetailsViewModel.bindDevice(themistoscale("Them","adress"))

        }) {
            Text(text = "Show Date")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = date)
        Text(text = "${hist}")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "$SubUserList")
        Text(text = "$devices")

    }
}

@Composable
fun DummyScreen3(authViewModel: AuthViewModel = viewModel()) {
    val context = LocalContext.current
    val activity = context as? Activity
    val userRepository: UserRepository = UserRepository(
        FirebaseAuth.getInstance(),
        Injection.instance()
    )

    // GoogleSignInManager instance
    val googleSignInManager = GoogleSignInManager(context)

    // Remember launcher for activity result
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            Log.d("FB125", "resultCode: ${result.resultCode}")
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("FB125", "Activity Result OK")
                googleSignInManager.handleSignInResult(
                    requestCode = GoogleSignInManager.RC_SIGN_IN,
                    resultCode = result.resultCode,
                    data = result.data
                )
            } else {
                Log.d("FB125", "Not OK - resultCode: ${result.resultCode}, data: ${result.data}")
                val extras = result.data?.extras
                if (extras != null) {
                    for (key in extras.keySet()) {
                        Log.d("FB125", "Key: $key Value: ${extras.get(key)}")
                    }
                }
            }
        }
    )
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Button(onClick = {
                if (activity != null) {
                    // Launch the sign-in intent
                    val signInIntent = googleSignInManager.googleSignInClient.signInIntent
                    launcher.launch(signInIntent)
                }
            }) {
                Text("Sign in with Google")
            }
            Button(onClick = {
                if (activity != null) {
                    // Launch the sign-in intent
                    if(isUserSignedInWithGoogle()){
                        Log.d("FB125","Yes")
                        googleSignInManager.signOut(activity)
                    }
                  authViewModel.LogOut()
                }
            }) {
                Text("Log Out")
            }
        }
    }
}

@Composable
fun DummyScreen4() {
    val context = LocalContext.current
    val activity = context as? Activity
    var bltPermisson by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        while(true) {
            bltPermisson = hasBluetoothPermissions(context)
            delay(2000)
        }
    }
   Box (Modifier.fillMaxSize()){
       Column(Modifier.align(Alignment.Center)) {
           Text("${bltPermisson}")
           Text(text = "${hasLocationPermissions(context)}")
//           if(!hasBluetoothPermissions(context)){
//               if(activity.isNotNull()) {
//                   requestBluetoothPermissions(activity!!)
//               }
//           }
//           if(!hasLocationPermissions(context)){
//               if(activity.isNotNull()) {
//                   requestLocationPermissions(activity!!)
//               }
//           }

       }
       Box (
           Modifier
               .align(Alignment.TopCenter)
               .padding(top = 40.dp)){
           CustomSnackbar(
               message = "Bluetooth is disabled",
               onStartClick = { },
               showSnackBar = true
           )
       }
   }
}