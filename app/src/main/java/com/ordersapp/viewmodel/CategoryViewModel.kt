package com.ordersapp.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordersapp.data.category.CategoryDatabase
import com.ordersapp.data.category.CategoriesRepository
import com.ordersapp.data.category.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date

class CategoryViewModel(application: Application): ViewModel() {

    private val repository: CategoriesRepository
    val all: LiveData<List<Category>>

    private val _name = mutableStateOf(NameFieldState())
    val name: State<NameFieldState> = _name
    private var currentId: Int? = null

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    var openDialog by mutableStateOf(false)

    init {
        val db = CategoryDatabase.getInstance(application)
        val dao = db.cateroryDao()
        repository = CategoriesRepository(dao)

        all = repository.all()
    }

    private fun load(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                repository.findById(id).also { category ->
                    currentId = category.id
                    _name.value = name.value.copy(
                        name = category.name
                    )
                }
            } else {
                currentId = null
                _name.value = name.value.copy(
                    name = "name"
                )
            }
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.SetText -> {
                _name.value = name.value.copy(
                    name = event.name
                )
            }
            is Event.Save -> {
                if(currentId != null){
                    repository.update(Category(currentId, name.value.name, Date()))
                }else{
                    repository.insert(Category(null, name.value.name, Date()))
                }
                openDialog = false
                coroutineScope.launch(Dispatchers.IO) {
                    _eventFlow.emit(Event.Save)
                }
            }
            is Event.OpenDialog -> {
                openDialog = true
            }
            is Event.CloseDialog -> {
                openDialog = false
            }
            is Event.Load -> {
                load(event.id)
                openDialog = true
            }
            is Event.Delete -> {
                event.id?.let { repository.delete(it) }
            }
        }
    }
}

sealed class Event {
    data class SetText(val name: String): Event()
    object OpenDialog: Event()
    object CloseDialog: Event()
    object Save: Event()
    data class Delete(val id: Int?): Event()
    data class Load(val id: Int?): Event()
}
