package gaur.himanshu.stringresinviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

const val PIN_CODE_LENGTH = 6

class MainViewModel: ViewModel() {

    private val _errorChannel = Channel<GetString>()
    val errorChannel: Flow<GetString> = _errorChannel.receiveAsFlow()


    fun validatePinCode(pinCode: String): Boolean {
        return when {

            pinCode.length < 6 -> {
                viewModelScope.launch {
                    _errorChannel.send(GetString.StringResource(R.string.pin_code_length,PIN_CODE_LENGTH))
                }
                false
            }

            else -> {
                true
            }
        }
    }


}