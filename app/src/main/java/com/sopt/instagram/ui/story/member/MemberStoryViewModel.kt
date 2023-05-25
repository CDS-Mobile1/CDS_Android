package com.sopt.instagram.ui.story.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.instagram.domain.entity.Story
import com.sopt.instagram.ui.story.member.MemberStoryUiState.NextMember
import com.sopt.instagram.ui.story.member.MemberStoryUiState.NextStory
import com.sopt.instagram.ui.story.member.MemberStoryUiState.PreviousMember
import com.sopt.instagram.ui.story.member.MemberStoryUiState.PreviousStory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemberStoryViewModel @Inject constructor() : ViewModel() {
    private val _storyState = MutableLiveData<MemberStoryUiState>()
    val storyState: LiveData<MemberStoryUiState>
        get() = _storyState

    private val _storyList = MutableLiveData<List<Story>>()
    val storyList: List<Story>
        get() = _storyList.value ?: emptyList()

    private val _storyIndex = MutableLiveData<Int>()
    val storyIndex: Int
        get() = _storyIndex.value ?: 0

    val currentStory = MutableLiveData<Story>()

    init {
        getStory()
    }

    private fun getStory() {
        // TODO: 서버통신으로 현재 회원의 스토리 가져오기
        val list = listOf(
            Story(
                id = 1,
                image = "https://user-images.githubusercontent.com/70744494/212153469-efeab9d1-927c-4937-8778-e27bf262510b.png",
                time = "2분",
            ),
            Story(
                id = 2,
                image = "https://github.com/Keyneez/Keyneez-AOS/assets/70993562/6ad1249e-c942-469a-83b0-1d85dac165bf",
                time = "43분",
            ),
            Story(
                id = 3,
                image = "https://github.com/GO-SOPT-ANDROID/chaeyeon-jeon/assets/70993562/6873f285-6938-4622-a0df-be1e23db9a9a",
                time = "3시간",
            ),
        )
        _storyList.value = list
        _storyIndex.value = 0
        setCurrentStory()
    }

    fun setCurrentStory() {
        currentStory.value = storyList[storyIndex]
    }

    fun increaseStoryIndex() {
        if (storyIndex == storyList.size - 1) {
            _storyState.value = NextMember
            return
        }
        _storyIndex.value = _storyIndex.value?.plus(1)
        _storyState.value = NextStory
    }

    fun decreaseStoryIndex() {
        if (storyIndex == 0) {
            _storyState.value = PreviousMember
            return
        }
        _storyIndex.value = _storyIndex.value?.minus(1)
        _storyState.value = PreviousStory
    }
}
