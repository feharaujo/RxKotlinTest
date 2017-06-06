package com.example.felipet.rxkotlintest.operators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import kotlinx.android.synthetic.main.activity_map.*

import com.example.felipet.rxkotlintest.R
import com.example.felipet.rxkotlintest.model.ApiUser
import com.example.felipet.rxkotlintest.model.User
import com.example.felipet.rxkotlintest.utils.AppConstant
import com.example.felipet.rxkotlintest.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MapActivity : AppCompatActivity() {

    val TAG = "JUNO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        btn.setOnClickListener { doSomeWork() }
    }

    fun doSomeWork() {
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { Utils.convertApiUserListToUserList(it) }
                .subscribe(getObserver())
    }

    private fun getObservable(): Observable<List<ApiUser>> {
        return Observable.create {
            if(!it.isDisposed) {
                it.onNext(Utils.getApiUserList())
                it.onComplete()
            }
        }
    }

    private fun getObserver(): Observer<List<User>> {
        return object : Observer<List<User>> {
            override fun onSubscribe(d: Disposable?) {
                Log.d(TAG, " onSubscribe : " + d?.isDisposed)
            }

            override fun onNext(userList: List<User>?) {
                textView.append(" onNext")
                textView.append(AppConstant.LINE_SEPARATOR)
                userList?.stream()?.forEach {
                    textView.append(" firstname : " + it.firstname)
                    textView.append(AppConstant.LINE_SEPARATOR)
                }
                Log.d(TAG, " onNext : " + userList?.size)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onComplete")
            }

            override fun onError(e: Throwable?) {
                if(e != null) {
                    textView.append(" onError : " + e.message)
                    textView.append(AppConstant.LINE_SEPARATOR)
                    Log.d(TAG, " onError : " + e.message)
                }
            }

        }
    }

}
