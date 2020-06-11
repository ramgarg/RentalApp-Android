
import android.app.Activity
import com.eazyrento.BuildConfig
import com.eazyrento.EazyRantoApplication
import com.eazyrento.Env
import com.eazyrento.Session
import com.fugu.CaptureUserData
import com.fugu.FuguConfig
import java.lang.Exception


fun userData(activity: Activity): CaptureUserData? {
   val userData = EazyRantoApplication.profileData
    userData?.let {
        return CaptureUserData.Builder()
            .userUniqueKey(Session.getInstance(activity)?.getUserID().toString())
            .fullName(it.full_name)
            .email(it.email)
            .build()
    }
    return null

}

fun initHippoWithUserData(activity:Activity){
    // Initialize here with user data
    try {
        FuguConfig.init(
            BuildConfig.VERSION_CODE,
            Env.HIPPO_APP_KEY,
            activity,
            userData(activity),
            BuildConfig.APPLICATION_ID+".provider"
        )
    }catch (e:Exception){
        e.printStackTrace()
    }
}

/*
  * show convertion
  * */
fun showHippoConversation(activity: Activity){
    try {
            FuguConfig.getInstance().showConversations(activity,Env.HIPPO_CHAT_TITLE)

    }catch (e:Exception){
        e.printStackTrace()
    }

}