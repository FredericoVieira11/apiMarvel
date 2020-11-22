package com.example.apimarvel.view.listCharacter

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apimarvel.remoteDataSource.MarvelServices
import com.example.apimarvel.remoteDataSource.model.CharacterModel
import com.example.apimarvel.remoteDataSource.response.list.CharacterListResponse
import com.example.apimarvel.utils.showAlert
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListViewModel @ViewModelInject constructor(
    private val apiService: MarvelServices,
    @ApplicationContext context: Context?
): ViewModel() {

    private val _characterLiveData = MutableLiveData<MutableList<CharacterModel>>()
    val characterLiveData: MutableLiveData<MutableList<CharacterModel>> = _characterLiveData

    val contextViewModel = context

    fun getListCharacter(){
        apiService.getListCharacter().enqueue(object: Callback<CharacterListResponse> {
            override fun onFailure(call: Call<CharacterListResponse>, t: Throwable) {
                showAlert(contextViewModel, t.message)
            }

            override fun onResponse(
                call: Call<CharacterListResponse>,
                response: Response<CharacterListResponse>
            ) {

                if (response.isSuccessful){

                    val listCharacter: MutableList<CharacterModel> = mutableListOf()

                    response.body()?.let {
                        for (results in it.data.results){
                            val listCharacterModel = CharacterModel(
                                id = results.id,
                                name = results.name
                            )
                            listCharacter.add(listCharacterModel)
                        }
                    }
                    _characterLiveData.value = listCharacter

                }else {
                    showAlert(contextViewModel, "${response.code()} ${response.message()}")
                }
            }

        })
    }
}