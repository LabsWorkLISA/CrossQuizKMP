package com.example.quiz.database

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.example.quiz.dialog.Setup
import com.example.quiz.quizList.QuizListItem

interface QuizSharedDatabase {

    fun observeAll(): Observable<List<QuizListItem>>

    fun add(title: String, setup: Setup, themeList: String): Completable

    fun setTitle(id: Long, title: String): Completable

//    fun setImageUrl(id: Long): Completable

        fun setThemes(id: Long, themeList: String): Completable

//        fun setCreatorName(id: Long, creatorName: String): Completable

//        fun setStartDate(id: Long, startDate: String): Completable

        fun clear(): Completable
}