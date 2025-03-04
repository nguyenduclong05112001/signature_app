package com.longhrk.app.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.longhrk.app.MainActivity
import com.longhrk.app.ui.event.NavEvent
import com.longhrk.app.ui.screen.SplashScreen
import com.longhrk.app.ui.screen.draw.DrawSignatureScreen
import com.longhrk.app.ui.screen.home.HomeScreen
import com.longhrk.app.ui.screen.setting.SettingScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(eventHandler: EventHandler, navController: NavHostController) {
    val startDestination = NavTarget.Splash.route
    val context = LocalContext.current
    val activity = context as MainActivity

    NavHost(navController, startDestination) {
        composable(NavTarget.Splash.route) {
            SplashScreen {
                eventHandler.postNavEvent(
                    event = NavEvent.ActionWithPopUp(
                        target = NavTarget.Home,
                        popupTarget = NavTarget.Splash,
                        inclusive = true
                    )
                )
            }
        }

        composable(NavTarget.Home.route) {
            HomeScreen(
                onSettingScreen = {
                    eventHandler.postNavEvent(
                        event = NavEvent.Action(
                            target = NavTarget.Setting
                        )
                    )
                },
                onDrawSignature = {
                    eventHandler.postNavEvent(
                        event = NavEvent.Action(
                            target = NavTarget.Draw,
                        )
                    )
                },
                onBackPress = {
                    activity.finish()
                }
            )
        }

        composable(NavTarget.Draw.route) {
            DrawSignatureScreen(
                onBackScreen = {
                    eventHandler.postNavEvent(
                        event = NavEvent.PopBackStack()
                    )
                }
            )
        }

        composable(NavTarget.Setting.route) {
            SettingScreen(
                onBackScreen = {
                    eventHandler.postNavEvent(
                        event = NavEvent.PopBackStack()
                    )
                }
            )
        }
    }
}