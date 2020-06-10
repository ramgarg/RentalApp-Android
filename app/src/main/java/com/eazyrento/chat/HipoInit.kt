
import android.app.Activity
import com.eazyrento.BuildConfig
import com.eazyrento.EazyRantoApplication
import com.eazyrento.Env
import com.eazyrento.Session
import com.fugu.CaptureUserData
import com.fugu.FuguConfig


fun userData(activity: Activity): CaptureUserData? {
   val userData = EazyRantoApplication.profileData
    return CaptureUserData.Builder()
        .userUniqueKey(Session.getInstance(activity)?.getUserID().toString())
        .fullName(userData?.full_name)
        .email(userData?.email)
        .build()
}

fun initHippoWithUserData(activity:Activity){
    // Initialize here with user data
FuguConfig.init(
        BuildConfig.VERSION_CODE,
        Env.HIPPO_APP_KEY,
        activity,
        userData(activity),
        BuildConfig.APPLICATION_ID+".provider"
    )

}