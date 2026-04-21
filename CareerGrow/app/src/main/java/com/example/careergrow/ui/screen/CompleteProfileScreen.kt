package com.example.careergrow.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.careergrow.ui.viewmodel.CompleteProfileViewModel
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.AlertDialog
import java.util.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.example.careergrow.navigation.NavRoutes
import android.Manifest
import android.provider.OpenableColumns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.Button
import com.example.careergrow.data.model.CompleteProfileRequest
import android.util.Log
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true)
@Composable
fun CompleteProfileScreen(
    navController: NavHostController, mobile: String
) {
    val viewModel: CompleteProfileViewModel = viewModel()

    var firstname by remember { mutableStateOf("") }
    var middlename by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var genderexpanded by remember { mutableStateOf(false) }
    var dob by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    var temporaryaddress by remember { mutableStateOf("") }
    var permanantaddress by remember { mutableStateOf("") }
    var stateexpanded by remember { mutableStateOf(false) }
    var districtexpanded by remember { mutableStateOf(false) }
    var cityexpanded by remember { mutableStateOf(false) }
    var professionExpanded by remember { mutableStateOf(false) }
    var topExpanded by remember { mutableStateOf(false) }
    var isSameAddress by remember { mutableStateOf(true) }
    var tstateexpanded by remember { mutableStateOf(false) }
    var tdistrictexpanded by remember { mutableStateOf(false) }
    var tcityexpanded by remember { mutableStateOf(false) }
    var fieldValues by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var imageUri: Uri? by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var taluka by remember { mutableStateOf("") }
    var ttaluka by remember { mutableStateOf("") }
//    LaunchedEffect(Unit) {
//        userid = mobile // 🔥 auto fill
//    }
    // 🔥 Sync when checkbox ON
    LaunchedEffect(
        viewModel.selectedState,
        viewModel.selectedDistrict,
        viewModel.selectedCity,
        permanantaddress,
        isSameAddress
    ) {
        if (isSameAddress) {
            viewModel.tempState = viewModel.selectedState
            viewModel.tempSelectedDistrict = viewModel.selectedDistrict
            viewModel.tempSelectedCity = viewModel.selectedCity
            temporaryaddress = permanantaddress
        }
    }

    val context = LocalContext.current
    val messageState = viewModel.successMessage

    LaunchedEffect(viewModel.successMessage) {
        if (viewModel.successMessage.isNotEmpty()) {
            Toast.makeText(context, viewModel.successMessage, Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(Unit) {
        Log.d("MOBILE_CHECK", mobile)
    }


    fun getFileName(context: Context, uri: Uri): String {
        var name = "file"

        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (it.moveToFirst()) {
                name = it.getString(nameIndex)
            }
        }

        return name
    }

    val context2 = LocalContext.current

// Gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }

// Camera
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        val uri = bitmap?.let {
            MediaStore.Images.Media.insertImage(

                context2.contentResolver,
                it,
                "profile",
                null
            )
        }
        imageUri = uri?.toUri()
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(null) // ✅ OPEN CAMERA
        } else {
            Toast.makeText(context2, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {},
            title = { Text("Select Image") },
            text = {
                Column {

                    Text(
                        "Camera",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDialog = false
                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                            .padding(10.dp)
                    )

                    Text(
                        "Gallery",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDialog = false
                                galleryLauncher.launch("image/*")
                            }
                            .padding(10.dp)
                    )
                }
            }
        )
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Complete Your Profile") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF46ACFE)
            ),


            actions = {

                IconButton(onClick = { topExpanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu"
                    )
                }
                DropdownMenu(
                    expanded = topExpanded,
                    onDismissRequest = { topExpanded = false }

                ) {
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            val context = navController.context
                            val sharedPref =
                                context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

                            sharedPref.edit().clear().apply() // 🔥 MOST IMPORTANT

                            navController.navigate(NavRoutes.LoginScreen) {
                                popUpTo(0) // 🔥 clear backstack
                            }


                        }
                    )
                }
            }
        )

    }, bottomBar = {}) { innerpadding ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = 16.dp)
        ) {


            item {
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value =mobile ,
                    onValueChange = { },
                    placeholder = { Text("User ID") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Enter Your Full Name",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                //------------District taluka city Feild------------------

                Row {

                    OutlinedTextField(
                        value = firstname,
                        onValueChange = { firstname = it },
                        placeholder = { Text("First") },
                        modifier = Modifier.weight(1f),
                        singleLine = true

                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedTextField(
                        value = middlename,
                        onValueChange = { middlename = it },
                        placeholder = { Text("Middle") },
                        modifier = Modifier.weight(1f),
                        singleLine = true

                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    OutlinedTextField(
                        value = lastname,
                        onValueChange = { lastname = it },
                        placeholder = { Text("Last") },
                        modifier = Modifier.weight(1f),
                        singleLine = true

                    )
                }
                val context = LocalContext.current

                LaunchedEffect(showDatePicker) {
                    if (showDatePicker) {

                        val calendar = Calendar.getInstance()

                        DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->

                                dob = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                                showDatePicker = false
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
                }


                val GenderList = listOf(
                    "Male",
                    "Female",
                    "Other",

                    )

                Row(modifier = Modifier.fillMaxWidth()) {

                    OutlinedTextField(
                        value = gender,
                        onValueChange = {},
                        label = { Text("Gender") },
                        modifier = Modifier
                            .weight(1f)
                            .clickable { genderexpanded = true }, // click open dropdown
                        readOnly = true, // important 🔥
                        trailingIcon = {
                            IconButton(onClick = { genderexpanded = true }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = ""
                                )
                            }
                        },
                        singleLine = true

                    )

                    DropdownMenu(
                        expanded = genderexpanded,
                        onDismissRequest = { genderexpanded = false }
                    ) {
                        GenderList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    gender = item // select value
                                    genderexpanded = false
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(5.dp))


                    OutlinedTextField(
                        value = dob,
                        onValueChange = {},
                        label = { Text("DOB") },
                        modifier = Modifier
                            .weight(1f)
                            .clickable { showDatePicker = true }, // 🔥 open calendar
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = true }) {
                                Icon(Icons.Default.CalendarMonth, null)
                            }
                        },
                        readOnly = true, // 🔥 typing disable
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))


                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = "Personal Details",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                //------------state Feild------------------


                Box(modifier = Modifier.fillMaxWidth()) {

                    OutlinedTextField(
                        value = viewModel.selectedState,
                        onValueChange = {},
                        label = { Text("Choose State") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    stateexpanded = true
                                }
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = stateexpanded,
                        onDismissRequest = { stateexpanded = false }
                    ) {
                        viewModel.stateList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    viewModel.selectedState = item
                                    stateexpanded = false

                                    viewModel.getDistricts(item) // 🔥 MUST
                                }
                            )


                        }
                    }

//                       Text("State count: ${viewModel.stateList.size}")
                }
                Spacer(modifier = Modifier.height(5.dp))

                Row(modifier = Modifier.fillMaxWidth()) {


                    // 🔽 District Dropdown
                    Box(modifier = Modifier.weight(1f)) {

                        OutlinedTextField(
                            value = viewModel.selectedDistrict,
                            onValueChange = {},
                            label = { Text("District") },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = { districtexpanded = true }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = ""
                                    )
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = districtexpanded,
                            onDismissRequest = { districtexpanded = false }
                        ) {
                            viewModel.districtList.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        viewModel.selectedDistrict = item
                                        districtexpanded = false

                                        viewModel.getCities(
                                            viewModel.selectedState,
                                            item
                                        ) // 🔥 CITY API CALL
                                    }
                                )
                            }
                        }
                    }



                    Spacer(modifier = Modifier.width(5.dp))





                    Box(modifier = Modifier.weight(1f)) {

                        OutlinedTextField(
                            value = viewModel.selectedCity,
                            onValueChange = {},
                            label = { Text("City") },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = { cityexpanded = true }) {
                                    Icon(Icons.Default.ArrowDropDown, null)
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = cityexpanded,
                            onDismissRequest = { cityexpanded = false }
                        ) {
                            viewModel.cityList.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        viewModel.selectedCity = item
                                        cityexpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = taluka,
                    onValueChange = { taluka = it },
                    label = { Text("Taluka") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = permanantaddress,
                    onValueChange = { permanantaddress = it },
                    placeholder = { Text("Permanent Address") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true

                )
                Spacer(modifier = Modifier.height(5.dp))



                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly
                ) {

                    Text(
                        text = "Same as Permanant Address",
                        modifier = Modifier,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )

                    Checkbox(
                        checked = isSameAddress,
                        onCheckedChange = { isChecked ->
                            isSameAddress = isChecked
                        }
                    )

                }
/////////// --------------------temprorary address-------------

                if (!isSameAddress) {
                    Box(modifier = Modifier.fillMaxWidth()) {

                        OutlinedTextField(
                            value = viewModel.tempState,
                            onValueChange = {},
                            label = { Text("Choose State") },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        tstateexpanded = true
                                    }
                                )
                            }
                        )

                        DropdownMenu(
                            expanded = tstateexpanded,
                            onDismissRequest = { tstateexpanded = false }
                        ) {
                            viewModel.stateList.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        viewModel.tempState = item // ✅ correct
                                        tstateexpanded = false

                                        viewModel.getTempDistricts(item) // 🔥 important
                                    }
                                )


                            }
                        }

//                       Text("State count: ${viewModel.stateList.size}")
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {


                        // 🔽 District Dropdown
                        Box(modifier = Modifier.weight(1f)) {

                            OutlinedTextField(
                                value = viewModel.tempSelectedDistrict,
                                onValueChange = {},
                                label = { Text("District") },
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                                singleLine = true,
                                trailingIcon = {
                                    IconButton(onClick = { tdistrictexpanded = true }) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            )

                            DropdownMenu(
                                expanded = tdistrictexpanded,
                                onDismissRequest = { tdistrictexpanded = false }
                            ) {
                                viewModel.tempDistrictList.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(item) },
                                        onClick = {
                                            viewModel.tempSelectedDistrict = item
                                            tdistrictexpanded = false

                                            viewModel.getTempCities(
                                                viewModel.tempState,
                                                item
                                            ) // 🔥 CITY API CALL
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(5.dp))





                        Box(modifier = Modifier.weight(1f)) {

                            OutlinedTextField(
                                value = viewModel.tempSelectedCity,
                                onValueChange = {},
                                label = { Text("City") },
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                                singleLine = true,
                                trailingIcon = {
                                    IconButton(onClick = { tcityexpanded = true }) {
                                        Icon(Icons.Default.ArrowDropDown, null)
                                    }
                                }
                            )

                            DropdownMenu(
                                expanded = tcityexpanded,
                                onDismissRequest = { tcityexpanded = false }
                            ) {
                                viewModel.tempCityList.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(item) },
                                        onClick = {
                                            viewModel.tempSelectedCity = item
                                            tcityexpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = ttaluka,
                        onValueChange = { ttaluka = it },
                        label = { Text("Taluka") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = temporaryaddress,
                        onValueChange = { temporaryaddress = it },
                        placeholder = { Text("Temporary Address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true

                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Educational Details",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                //------------profession Feild------------------


                Column {

                    OutlinedTextField(
                        value = viewModel.selectedProfession,
                        onValueChange = {},
                        label = { Text("Choose Profession") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { professionExpanded = true },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { professionExpanded = true }) {
                                Icon(Icons.Default.ArrowDropDown, null)
                            }
                        },
                        singleLine = true
                    )

                    DropdownMenu(
                        expanded = professionExpanded,
                        onDismissRequest = { professionExpanded = false }
                    ) {
                        viewModel.professionList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    viewModel.selectedProfession = item
                                    professionExpanded = false

                                    viewModel.getProfessionDetails(item) // 🔥 API call
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                viewModel.dynamicFields.forEach { field ->

                    OutlinedTextField(
                        value = fieldValues[field.under] ?: "",
                        onValueChange = { value ->
                            fieldValues = fieldValues.toMutableMap().apply {
                                put(field.under, value)
                            }
                        },
                        label = { Text(field.label) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }


                val fileName = imageUri?.let { getFileName(context2, it) } ?: "No file selected"

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Upload Profile Image",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clickable { showDialog = true }
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {

                        if (imageUri != null) {

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                Image(
                                    painter = rememberAsyncImagePainter(imageUri),
                                    contentDescription = null,
                                    modifier = Modifier.size(70.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = fileName,
                                    fontSize = 13.sp
                                )
                            }

                        } else {

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                Icon(
                                    imageVector = Icons.Default.CloudUpload,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(40.dp)
                                )

                                Text("Tap to Upload", color = Color.Gray)
                            }
                        }
                    }
                }

                Button(
                    onClick = {

                        val request = CompleteProfileRequest(
                            user_mobile = mobile,

                            user_fname = firstname,
                            user_mname = middlename,
                            user_lname = lastname,

                            user_avtar = fileName,
                            birth_date = dob,
                            gender = gender,

                            user_per_state = viewModel.selectedState,
                            user_per_dist = viewModel.selectedDistrict,
                            user_per_taluka = taluka,
                            user_per_city = viewModel.selectedCity,
                            user_per_address = permanantaddress,

                            user_comm_state = if (isSameAddress) viewModel.selectedState else viewModel.tempState,
                            user_comm_dist = if (isSameAddress) viewModel.selectedDistrict else viewModel.tempSelectedDistrict,
                            user_comm_tal = if (isSameAddress) taluka.ifEmpty { viewModel.selectedDistrict } else ttaluka,
                            user_comm_city = if (isSameAddress) viewModel.selectedCity else viewModel.tempSelectedCity,
                            user_comm_address = if (isSameAddress) permanantaddress else temporaryaddress,

                            profession = viewModel.selectedProfession,

                            std_education = fieldValues["user_qualification"] ?: "",
                            std_education_city = fieldValues["education_city"] ?: "",

                            emp_qualification = fieldValues["emp_qualification"] ?: "",
                            emp_company_name = fieldValues["emp_company_name"] ?: "",
                            emp_designation = fieldValues["emp_designation"] ?: "",
                            emp_job_city = fieldValues["emp_job_city"] ?: "",

                            business_name = fieldValues["business_name"] ?: "",
                            business_type = fieldValues["business_type"] ?: "",
                            business_city = fieldValues["business_city"] ?: "",
                            business_address = fieldValues["business_address"] ?: ""
                        )

                        // 🔥 STEP 3 LOG (IMPORTANT)
                        Log.d("FINAL_REQUEST", request.toString())

                        viewModel.updateProfile(request)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit Profile")
                }

            }

        }


    }

}