package com.example.apimarvel.view.details

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apimarvel.remoteDataSource.MarvelServices
import com.example.apimarvel.remoteDataSource.model.DetailsModel
import com.example.apimarvel.remoteDataSource.response.details.CharacterDetailsResponse
import com.example.apimarvel.utils.showAlert
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsViewModel @ViewModelInject constructor(
    private val apiService: MarvelServices,
    @ApplicationContext context: Context?
): ViewModel() {

    private val _detailsLiveData = MutableLiveData<DetailsModel>()
    val detailsLiveData: MutableLiveData<DetailsModel> = _detailsLiveData

    val contextViewModel = context

    fun getDetailsCharacter(id: Int){
        apiService.getDetailsCharacter(id = id).enqueue(object: Callback<CharacterDetailsResponse> {
            override fun onFailure(call: Call<CharacterDetailsResponse>, t: Throwable) {
                showAlert(contextViewModel, t.message)
            }

            override fun onResponse(
                call: Call<CharacterDetailsResponse>,
                response: Response<CharacterDetailsResponse>
            ) {

                if (response.isSuccessful){

                    response.body()?.let{
                        val modelDetails = DetailsModel(
                            name = it.data.results[0].name,
                            description = it.data.results[0].description,
                            image = it.data.results[0].thumbnail.path,
                            extension = it.data.results[0].thumbnail.extension
                        )
                        _detailsLiveData.value = modelDetails
                    }
                }else {
                    showAlert(contextViewModel, "${response.code()} ${response.message()}")
                }
            }

        })
    }
}