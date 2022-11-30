package com.intern.evtutors.composes.notification

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.composes.teacherPostInfo.ui.UITeacherPost
import com.intern.evtutors.view_models.ProfileViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import com.miggue.mylogin01.ui.theme.LightBlue
import com.miggue.mylogin01.ui.theme.whitebacground


@Composable
fun NotificationScreen(profileViewModel: ProfileViewModel = hiltViewModel()

) {
     FatherOfAppsTheme {
          val context = LocalContext.current
          Surface() {
               Column(Modifier.fillMaxWidth(),Arrangement.Top, Alignment.CenterHorizontally
                    ) {
                    Row(Modifier.fillMaxWidth(0.4f)
                         .background(LightBlue, shape = RoundedCornerShape(20.dp))
                        .clickable {
                              var intent: Intent = Intent(context, UITeacherPost::class.java)
                              context.startActivity(intent)
                         },
                         Arrangement.Center
                    ){
                         Text(text = "ADD", color = whitebacground)
                    }



               }
          }
     }
}
