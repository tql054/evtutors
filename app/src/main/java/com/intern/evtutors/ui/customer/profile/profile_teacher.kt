    package com.intern.evtutors.ui.customer.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.intern.evtutors.R
import com.intern.evtutors.composes.profile.CertificateItemTeacher
import com.intern.evtutors.data.model_json.TeacherDegree
import com.intern.evtutors.screens.Screens
import com.intern.evtutors.ui.customer.profile.ui.theme.redbacground
import com.intern.evtutors.ui.customer.profile.ui.theme.whitebacground
import com.intern.evtutors.ui.customer.profile.ui.theme.yellowbacground
import com.intern.evtutors.view_models.LoginViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


    @AndroidEntryPoint
class profile_teacher : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//
            //profileTeacher()
        }
    }
}
@Composable
 fun profileTeacher( navHostController: NavHostController,loginViewModel  : LoginViewModel = hiltViewModel()){

    val context = LocalContext.current
    val scope = CoroutineScope( Job() + Dispatchers.Main)
    var teacherDegree by mutableStateOf(mutableListOf<TeacherDegree?>())

    scope.launch {
        loginViewModel.getDegreeTutor(2)
    }
    teacherDegree=loginViewModel.teacherDegree

    var checkCertifi by remember{ mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(Color(0xFFE0E7FF))){

        Box(modifier = Modifier.fillMaxWidth()
            .height(80.dp).background(Color(0xFF7392FE))){}
        Box(modifier = Modifier.fillMaxWidth()
            .height(40.dp)
            .clickable {

            }
            .padding(top= 15.dp)){
            Column(Modifier.padding(start = 20.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_back_outline), contentDescription = "",
                    modifier = Modifier.padding(end = 10.dp, top = 3.dp).height(20.dp).width(25.dp).
                    clip(RectangleShape).clickable {
                        navHostController.navigate((Screens.StudentHome.route))
                    },contentScale = ContentScale.FillBounds
                )
            }
        }
        Column(modifier = Modifier
            .padding(top = 45.dp)
            .fillMaxWidth(),
            horizontalAlignment=Alignment.CenterHorizontally
            ) {
            HeardprofileTeacher()
            Spacer(Modifier.height(12.dp))
            UIFollowerTeacher()
            Spacer(Modifier.height(15.dp))
            if (!checkCertifi){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(50.dp)
                        .shadow(elevation=15.dp,shape=RoundedCornerShape(topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp) )
                        .clip(shape = RoundedCornerShape(topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp))

                        .background(whitebacground)
                        .clickable { checkCertifi = !checkCertifi }
                        .verticalScroll(rememberScrollState()),
                    Alignment.Center
                ){
                    Row(modifier = Modifier.fillMaxWidth().padding( start =20.dp )
                    ) {
                        Row(modifier = Modifier,
                        ) {
                            Image(painter = painterResource(id = R.drawable.ic_image_icon), contentDescription = "",
                                modifier = Modifier.padding(end = 10.dp, top = 3.dp).height(15.dp).width(20.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                            )
                            Text(text = "Certificate List", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF3C5EC7) )
                        }
                        Row(modifier = Modifier.fillMaxWidth().padding(end = 10.dp),Arrangement.End
                        ) {

                            Image(painter = painterResource(id = R.drawable.ic_vector__down), contentDescription = "",
                                modifier = Modifier.padding(end = 10.dp, top = 3.dp)
                                    .height(20.dp).width(20.dp).
                                    clip(RectangleShape)
                                    .clickable {checkCertifi = !checkCertifi  }
                                ,contentScale = ContentScale.FillBounds
                            )
                        }
                    }


                }
            }
            else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                        .heightIn(min =50.dp,max=400.dp)
//
                        .clip(shape = RoundedCornerShape(topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp))
                        .background(whitebacground)
                        .clickable { checkCertifi = !checkCertifi }
                        .verticalScroll(rememberScrollState()),
                    Alignment.Center
                ){
                    Column(modifier = Modifier.fillMaxWidth()

                    ) {
                        Row(modifier = Modifier.fillMaxWidth().padding( start =20.dp,top=15.dp )
                        ) {
                            Row(modifier = Modifier,
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_image_icon), contentDescription = "",
                                    modifier = Modifier.padding(end = 10.dp, top = 3.dp).height(15.dp).width(20.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                                )
                                Text(text = "Certificate List", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF3C5EC7) )
                            }
                            Row(modifier = Modifier.fillMaxWidth().padding(end = 10.dp),Arrangement.End
                            ) {

                                Image(painter = painterResource(id = R.drawable.ic_vector__up), contentDescription = "",
                                    modifier = Modifier.padding(end = 10.dp, top = 3.dp)
                                        .height(20.dp).width(20.dp).clip(RectangleShape)
                                        .clickable { checkCertifi = !checkCertifi },contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                        if(teacherDegree.size==0){
                            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center
                            ){
                                Text(text = "Certificate not found!", color = Color(0xFF757575),fontStyle = FontStyle.Italic, fontSize = 12.sp)
                            }
                        }else{
                            Column(Modifier.fillMaxWidth().padding(5.dp)) {

                                for(i in teacherDegree){
                                    CertificateItemTeacher(i?.img1,i?.name,i?.dateOfIssue)
                                    Spacer(Modifier.height(2.dp))

                            }

                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(15.dp))
            UICommentTeacher()

        }
    }
}

@Composable
fun HeardprofileTeacher(){
    Box(
        modifier = Modifier
            .shadow(elevation=5.dp,shape=RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp) )
            .fillMaxWidth(0.9f)
            .height(200.dp)
            .clip(shape = RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp)
            )
            .background(whitebacground)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top=15.dp, start = 15.dp)) {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(start = 5.dp, end = 15.dp)){
                Image(painter = painterResource(id = R.drawable.imagemodel), contentDescription = "",
                    modifier = Modifier.height(120.dp).width(100.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                )
                Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceBetween
                    ) {
                        Text("Nguyễn Đình Khoa", fontWeight = FontWeight.Bold, color = Color(0xFF3C5EC7), fontSize = 16.sp)
                        Image(painter = painterResource(id = R.drawable.ic_group_1662), contentDescription = "",
                            modifier = Modifier.height(15.dp).clickable {  },contentScale = ContentScale.FillBounds
                        )
                    }
                    Text("dinhkhoaute@gmail.com", color = Color(0xFF3C5EC7), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(30.dp))
                    Image(painter = painterResource(id = R.drawable.ic_group_1682), contentDescription = "",
                        modifier = Modifier.height(40.dp).width(130.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                    )
                }
            }
            Row(modifier = Modifier.fillMaxSize(),
                Arrangement.SpaceAround,
                Alignment.CenterVertically
            ){
                Column(modifier = Modifier,
                    Arrangement.Top,
                    Alignment.CenterHorizontally
                ) {
                    Text(text = "180", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF3C5EC7) )
                    Text(text = "Followers", fontSize = 12.sp, color = Color(0xFF3C5EC7))
                }
                Column(modifier = Modifier,
                    Arrangement.Top,
                    Alignment.CenterHorizontally
                ) {
                    Text(text = "3", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF3C5EC7) )
                    Text(text = "Courses", fontSize = 12.sp, color = Color(0xFF3C5EC7))
                }
                Column(modifier = Modifier,
                    Arrangement.Top,
                    Alignment.CenterHorizontally
                ) {
                    Text(text = "4.9", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF3C5EC7) )
                    Text(text = "Stars", fontSize = 12.sp, color = Color(0xFF3C5EC7))
                }
            }
        }
    }
}
@Composable
fun UIFollowerTeacher(){

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(125.dp)
            .shadow(elevation=5.dp,shape=RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp) )
            .clip(shape = RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp))

            .background(whitebacground)


    ){
        Column {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start =20.dp )
            ) {
                Row(modifier = Modifier,
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_followers_icon), contentDescription = "",
                        modifier = Modifier.padding(end = 10.dp, top = 3.dp).height(15.dp).width(20.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                    )
                    Text(text = "Followers", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF3C5EC7) )
                }
                Row(modifier = Modifier.fillMaxWidth().padding(end = 10.dp),Arrangement.End
                ) {

                    Text(text = "View more", fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color(0xFF8E8E8E) )
                }
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top=15.dp,start =20.dp)) {
                Row(modifier = Modifier.width(200.dp),
                    Arrangement.SpaceBetween){
                    Image(painter = painterResource(id = R.drawable.imagemodel), contentDescription = "",
                        modifier = Modifier.height(60.dp).width(60.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                    )
                    Image(painter = painterResource(id = R.drawable.imagemodel), contentDescription = "",
                        modifier = Modifier.height(60.dp).width(60.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                    )
                    Image(painter = painterResource(id = R.drawable.imagemodel), contentDescription = "",
                        modifier = Modifier.height(60.dp).width(60.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                    )
                }
                Row(modifier = Modifier.fillMaxSize().padding(end = 10.dp, bottom = 10.dp),Arrangement.End,
                    Alignment.CenterVertically
                ) {

                    Box(modifier = Modifier
                        .width(60.dp)
                        .height(25.dp)
                        .clip(shape = RoundedCornerShape(topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp))
                        .background(Color(0xFFA2B6F5)),
                        Alignment.Center
                    ) {
                        Text(text = "100 +", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = whitebacground )
                    }
                }
            }
        }

    }


}
@Composable
fun UICommentTeacher(){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(260.dp)
            .shadow(elevation=5.dp,shape=RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp) )
            .clip(shape = RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp))
//            .border(width = 1.dp, color = browbacground)
            .background(whitebacground)


        ){
        Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start =20.dp, end = 10.dp) ) {
            Row()
            {
                Row(modifier = Modifier,
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_icon_comment), contentDescription = "",
                        modifier = Modifier.padding(end = 10.dp, top = 3.dp).height(20.dp).width(20.dp).clip(RectangleShape).clickable {  },contentScale = ContentScale.FillBounds
                    )
                    Text(text = "Comments", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF3C5EC7) )
                }
                Row(modifier = Modifier.fillMaxWidth().padding(end = 10.dp),Arrangement.End
                ) {

                    Text(text = "View more", fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color(0xFF8E8E8E) )
                }
            }
            Spacer(Modifier.height(15.dp))

            Item_comment1(4,"Lê Quốc Tuấn","Khóa học rất tuyệt, thầy dạy dễ hiểu, chậm rãi,...","2w ago")
            Item_comment1(4,"Lê Quốc Tuấn","Khóa học rất tuyệt, thầy dạy dễ hiểu, chậm rãi,...","2w ago")
        }

    }
}
@Composable
fun Item_comment1(count:Int,name:String,textComment:String,time:String){
    Row(modifier = Modifier.fillMaxWidth()
        .height(85.dp)
        .padding(bottom = 3.dp)


    ){
        Box(modifier = Modifier.padding(top = 10.dp, start = 10.dp)){
            Image(painter = painterResource(id = R.drawable.onboard), contentDescription = "",
                modifier = Modifier.size(45.dp).clip(CircleShape)
                    .clickable {  }
                ,contentScale = ContentScale.FillBounds)
        }

        Column(modifier = Modifier.padding(start = 15.dp, top = 10.dp)) {
            Row(modifier = Modifier.fillMaxWidth()){
                Row(modifier = Modifier,
                ) {
                    Text(text =name , fontSize = 12.sp)
                }
                Row(modifier = Modifier.fillMaxWidth().padding(end = 10.dp),Arrangement.End
                ) {

                    Text(text = time, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color(0xFF8E8E8E) )
                }
            }

            Row(){
                for (i in 1..count){
                    Icon(
                        Icons.Sharp.Star, contentDescription = null, Modifier.size(15.dp).padding(end =4.dp),
                        tint =yellowbacground
                    )
                }
            }
            Text(text =textComment , color = Color(0xFF8D8D8D), fontSize = 10.sp)
            Row( verticalAlignment=Alignment.CenterVertically){
                Icon(
                    Icons.Sharp.Favorite, contentDescription = null, Modifier.size(10.dp),
                    tint = redbacground
                )
                Spacer( Modifier.width(5.dp))
                Text(text ="29" , color = Color(0xFF8D8D8D), fontSize = 10.sp)
            }


        }
    }
}


@Preview
@Composable
fun ComposablePreview() {
   // profileTeacher()
//    Item_comment1(4,"Lê Quốc Tuấn","Khóa học rất tuyệt, thầy dạy dễ hiểu, chậm rãi,...","2w ago")
}