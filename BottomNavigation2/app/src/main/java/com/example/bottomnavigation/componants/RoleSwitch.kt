package com.example.bottomnavigation.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoleSwitch(
    selectedRole: String,
    onRoleChange: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEDEDED), RoundedCornerShape(10.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val isCustomer = selectedRole == "teachermain"

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(if (isCustomer) Color.Blue else Color.Transparent)
                .clickable { onRoleChange("teachermain") }
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Teacher Login",
                color = if (isCustomer) Color.White else Color.Black
            )
        }
        Spacer(modifier = Modifier.width(2.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(if (!isCustomer) Color.Blue else Color.Transparent)
                .clickable { onRoleChange("parentmain") }
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Parent Login",
                color = if (!isCustomer) Color.White else Color.Black
            )
        }
    }
}@Preview(showSystemUi = true)
@Composable
fun PreviewRoleSwitchs() {

    var role by remember { mutableStateOf("teachermain") }

    RoleSwitch(
        selectedRole = role,
        onRoleChange = { role = it }
    )
}