package com.example.felipet.rxkotlintest.utils

import android.util.Log

import com.androidnetworking.error.ANError
import com.example.felipet.rxkotlintest.model.ApiUser
import com.example.felipet.rxkotlintest.model.User

import java.util.ArrayList
import java.util.stream.Collectors

/**
 * Created by amitshekhar on 27/08/16.
 */
object Utils {

    val userList: List<User>
        get() {

            val userList = ArrayList<User>()

            val userOne = User()
            userOne.firstname = "Amit"
            userOne.lastname = "Shekhar"
            userList.add(userOne)

            val userTwo = User()
            userTwo.firstname = "Manish"
            userTwo.lastname = "Kumar"
            userList.add(userTwo)

            val userThree = User()
            userThree.firstname = "Sumit"
            userThree.lastname = "Kumar"
            userList.add(userThree)

            return userList
        }

    val apiUserList: List<ApiUser>
        get() {

            val apiUserList = ArrayList<ApiUser>()

            val apiUserOne = ApiUser()
            apiUserOne.firstname = "Amit"
            apiUserOne.lastname = "Shekhar"
            apiUserList.add(apiUserOne)

            val apiUserTwo = ApiUser()
            apiUserTwo.firstname = "Manish"
            apiUserTwo.lastname = "Kumar"
            apiUserList.add(apiUserTwo)

            val apiUserThree = ApiUser()
            apiUserThree.firstname = "Sumit"
            apiUserThree.lastname = "Kumar"
            apiUserList.add(apiUserThree)

            return apiUserList
        }

    fun convertApiUserListToUserList(apiUserList: List<ApiUser>): List<User> {

        val userList = ArrayList<User>()

        for (apiUser in apiUserList) {
            val user = User()
            user.firstname = apiUser.firstname
            user.lastname = apiUser.lastname
            userList.add(user)
        }

        return userList
    }

    val userListWhoLovesCricket: List<User>
        get() {

            val userList = ArrayList<User>()

            val userOne = User()
            userOne.id = 1
            userOne.firstname = "Amit"
            userOne.lastname = "Shekhar"
            userList.add(userOne)

            val userTwo = User()
            userTwo.id = 2
            userTwo.firstname = "Manish"
            userTwo.lastname = "Kumar"
            userList.add(userTwo)

            return userList
        }


    val userListWhoLovesFootball: List<User>
        get() {

            val userList = ArrayList<User>()

            val userOne = User()
            userOne.id = 1
            userOne.firstname = "Amit"
            userOne.lastname = "Shekhar"
            userList.add(userOne)

            val userTwo = User()
            userTwo.id = 3
            userTwo.firstname = "Sumit"
            userTwo.lastname = "Kumar"
            userList.add(userTwo)

            return userList
        }


    fun filterUserWhoLovesBoth(cricketFans: List<User>, footballFans: List<User>): List<User> {
        val userWhoLovesBoth = ArrayList<User>()

        cricketFans.forEach { c ->
            footballFans
                    .filter { c.id == it.id }
                    .map { userWhoLovesBoth.add(it) }
        }

        return userWhoLovesBoth
    }

    fun logError(TAG: String, e: Throwable) {
        if (e is ANError) {
            val anError = e
            if (anError.errorCode != 0) {
                // received ANError from server
                // error.getErrorCode() - the ANError code from server
                // error.getErrorBody() - the ANError body from server
                // error.getErrorDetail() - just a ANError detail
                Log.d(TAG, "onError errorCode : " + anError.errorCode)
                Log.d(TAG, "onError errorBody : " + anError.errorBody)
                Log.d(TAG, "onError errorDetail : " + anError.errorDetail)
            } else {
                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                Log.d(TAG, "onError errorDetail : " + anError.errorDetail)
            }
        } else {
            Log.d(TAG, "onError errorMessage : " + e.message)
        }
    }

}// This class in not publicly instantiable.
