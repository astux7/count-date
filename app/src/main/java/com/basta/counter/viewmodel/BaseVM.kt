import android.app.Application

import androidx.lifecycle.AndroidViewModel
import com.basta.counter.utils.UserPrefManager

class BaseVM(userPref: UserPrefManager, application: Application) :
    AndroidViewModel(application) {
    private val userPref: UserPrefManager = userPref

   fun getDate() : Long {
       val date = userPref.readDate()

      return if (date.isNullOrBlank()) {
          0L
      } else {
          date.toLong()
      }
   }

}